package cadastrocursos.controller;

import cadastrocursos.domain.Usuario;
import cadastrocursos.service.UsuarioService;
import cadastrocursos.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuario/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping(value = "/usuario/login/{username}/{password}")
    public ResponseEntity<Usuario> login(@PathVariable String username, @PathVariable String password) {
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        String pa = passwordEncoder.cri(password);
        Usuario usuarios = usuarioService.listarUsuario(username,  pa);
        return ResponseEntity.ok().body(usuarios);
    }

    /*@PostMapping("/register")
    public String doRegister(@ModelAttribute UserDto userDto) {
        String encodedPassword  = passwordEncoder.encode(userDto.getPassword1());

        User user = new User();
        user.setEnabled(Boolean.TRUE);
        user.setPassword(encodedPassword);
        user.setUsername(userDto.getUsername());

        UserAuthority boardAuthority = new UserAuthority();
        boardAuthority.setAuthority("BOARD");
        boardAuthority.setUser(user);
        user.getAuthorities().add(boardAuthority);
        userRepository.save(user);

        return "register-success";
    }*/

}
