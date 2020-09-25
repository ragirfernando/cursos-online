package cadastrocursos.controller;

import cadastrocursos.domain.Usuario;
import cadastrocursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

}
