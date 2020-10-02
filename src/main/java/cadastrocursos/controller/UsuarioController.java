package cadastrocursos.controller;

import cadastrocursos.domain.Usuario;
import cadastrocursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "usuario/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping(value = "/usuario/listarUsuarioId/{id}")
    public ResponseEntity<Usuario> listarUsuarioId(@PathVariable Integer id){
        Usuario usuario = usuarioService.listarUsuaioId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping(value = "/usuario/listarUsuarioCPF/{cpf}")
    public ResponseEntity<Usuario> listarUsuarioCPF(@PathVariable String cpf){
        Usuario usuario = usuarioService.listarUsuariosCPF(cpf);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping(value = "/usuario/inserirUsuario")
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario usuario){
        usuario = usuarioService.inserirUsuario(usuario);
        return  ResponseEntity.ok().body(usuario);
    }

    @PutMapping(value = "/usuario/atualizarUsuario")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario){
        usuario = usuarioService.atualizarUsuario(usuario);
        return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping(value = "/usuario/deletarUsuarioId/{id}")
    public ResponseEntity<Void> deletarUsuarioId(@PathVariable Integer id){
        usuarioService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
