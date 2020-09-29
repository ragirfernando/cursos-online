package cadastrocursos.service;

import cadastrocursos.domain.Perfil;
import cadastrocursos.domain.Usuario;
import cadastrocursos.exceptions.ResourceNotFoundException;
import cadastrocursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = Optional.ofNullable(usuarioRepository.findByUsername(username)).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        String[] perfis = listarPerfisUsuario(usuario.getPerfis());

        List<GrantedAuthority> roles = createAuthorityList(perfis);

        /*List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList();
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList(user);*/
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    private String[] listarPerfisUsuario(Set<Perfil> perfis) {
        String[] roles = (String[]) perfis.toArray();
        return roles;
    }

    public static List<GrantedAuthority> createAuthorityList(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.length);
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = Optional.ofNullable(usuarioRepository.findByUsername(username)).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList(user, admin);
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList(user);
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isAdmin() ? authorityListAdmin : authorityListUser);
    }*/
}
