package cadastrocursos.controller;

import cadastrocursos.domain.Menu;
import cadastrocursos.domain.Role;
import cadastrocursos.domain.Usuario;
import cadastrocursos.service.MenuService;
import cadastrocursos.service.RoleService;
import cadastrocursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/desenvolvedores")
public class DesenvolvedorController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/desenvolvedor/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
}
