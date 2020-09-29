package cadastrocursos.service;

import cadastrocursos.domain.Perfil;
import cadastrocursos.domain.Usuario;
import cadastrocursos.exceptions.ResourceNotFoundException;
import cadastrocursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
        List<String> perfis = addListaPerfis(usuario.getPerfis());
        List<GrantedAuthority> roles = createAuthorityList(perfis);
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    private List<String> addListaPerfis(Set<Perfil> perfis) {
        List<String> listaPerfis = new ArrayList<>();
        perfis.forEach(perfil -> {
            listaPerfis.add(perfil.getNome());
        });
        return listaPerfis;
    }

    public static List<GrantedAuthority> createAuthorityList(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.size());
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
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
