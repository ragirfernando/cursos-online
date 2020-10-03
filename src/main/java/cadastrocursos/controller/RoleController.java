package cadastrocursos.controller;

import cadastrocursos.domain.Role;
import cadastrocursos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/role/roles")
    public ResponseEntity<List<Role>> listarRoles() {
        List<Role> perfis = roleService.listarRoles();
        return ResponseEntity.ok().body(perfis);
    }

    @GetMapping(value = "/role/role/{id}")
    public ResponseEntity<Role> listarRole(@PathVariable Integer id) {
        Role role = roleService.listarRole(id);
        return ResponseEntity.ok().body(role);
    }

    @PostMapping(value = "/role/inserirRole")
    public ResponseEntity<Role> inserirRole(@RequestBody Role role) {
        Role roleInserida = roleService.inseriRole(role);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();
        return ResponseEntity.created(uri).body(roleInserida);
    }

    @PutMapping(value = "/role/atualizarRole")
    public ResponseEntity<Role> atualizarRole(@RequestBody Role role) {
        Role roleAtualizada = roleService.inseriRole(role);
        return ResponseEntity.ok().body(roleAtualizada);
    }

    @DeleteMapping(value = "/role/deletarRole/{id}")
    public ResponseEntity<String> deletarRole(@PathVariable Integer id) {
        String mensagemStatus = roleService.deletarRole(id);
        return ResponseEntity.ok().body(mensagemStatus);
    }
}
