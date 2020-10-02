package cadastrocursos.config;

import cadastrocursos.domain.Perfil;
import cadastrocursos.domain.Resposta;
import cadastrocursos.domain.Usuario;
import cadastrocursos.service.UsuarioConfigService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cadastrocursos.config.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    Gson gson = new Gson();

    private AuthenticationManager authenticationManager;
    private Usuario usuario;
    private UsuarioConfigService usuarioConfigService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UsuarioConfigService usuarioConfigService) {
        this.authenticationManager = authenticationManager;
        this.usuarioConfigService = usuarioConfigService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario buscarUsuarioLogado(String username) {
        return usuarioConfigService.listarUsuaioUsername(username);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        String token = Jwts
                .builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        String bearerToken = TOKEN_PREFIX + token;
        Usuario usuarioLogado = buscarUsuarioLogado(usuario.getUsername());
        List<String> urls = addUrls(usuarioLogado.getPerfis());
        Resposta resposta = new Resposta(usuarioLogado.getId(), response.getStatus(), bearerToken, usuarioLogado.getNome(), urls);
        response.getWriter().write(gson.toJson(resposta));
        response.addHeader(HEADER_STRING, bearerToken);
    }

    private List<String> addUrls(List<Perfil> perfis) {
        List<String> listaUrls = new ArrayList<>();
        perfis.forEach(perfil -> {
            perfil.getMenus().forEach(menu -> {
                listaUrls.add(menu.getUrl());
            });
        });
        return listaUrls;
    }
}

    /*private Integer IdUsuario;

    private Integer Status;

    private String token;

    private String nome;

    private List<String> urls = new ArrayList<>();*/