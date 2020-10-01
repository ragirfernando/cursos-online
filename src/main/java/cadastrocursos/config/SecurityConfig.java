package cadastrocursos.config;

import cadastrocursos.service.UsuarioConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import static cadastrocursos.config.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioConfigService usuarioConfigService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .configurationSource(request -> new CorsConfiguration()
                        .applyPermitDefaultValues())
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
                .antMatchers("/alunos/aluno/**").hasRole("ALUNO")
                .antMatchers("/instrutores/instrutor/**").hasRole("INSTRUTOR")
                .antMatchers("/usuarios/usuario/**").hasAnyRole("ADMIN", "INSTRUTOR", "ALUNO")
                .antMatchers("/admins/admin/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), usuarioConfigService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), usuarioConfigService));

       /* httpSecurity.cors().disable().csrf().disable()
                .authorizeRequests().antMatchers("/v1/login").permitAll().
        anyRequest().authenticated()
                .and().
        exceptionHandling().
                and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);*/

//        http.authorizeRequests()
//                .antMatchers("/*/aluno/**").hasRole("ALUNO")
//                .antMatchers("/*/instrutor/**").hasRole("INSTRUTOR")
//                .antMatchers("/*/pessoa/**").hasAnyRole("ADMIN", "INSTRUTOR", "ALUNO")
//                .antMatchers("/*/admin/**").hasRole("ADMIN")
//                .and()
//                .httpBasic()
//                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioConfigService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
