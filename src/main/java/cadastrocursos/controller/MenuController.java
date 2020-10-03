package cadastrocursos.controller;

import cadastrocursos.domain.Menu;
import cadastrocursos.domain.Role;
import cadastrocursos.service.MenuService;
import cadastrocursos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/menus")
public class MenuController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/menu/menus")
    public ResponseEntity<List<Menu>> listarMenus() {
        List<Menu> menus = menuService.listarMenus();
        return ResponseEntity.ok().body(menus);
    }

    @GetMapping(value = "/menu/menuId/{id}")
    public ResponseEntity<Menu> listarMenuId(@PathVariable Integer id) {
        Menu menu = menuService.listarMenu(id);
        return ResponseEntity.ok().body(menu);
    }

    @PostMapping(value = "/menu/inserirMenu/{idRole}")
    public ResponseEntity<Menu> inserirMenu(@RequestBody Menu menu, @PathVariable Integer idRole) {
        Menu menuInserido = menuService.inseriMenu(menu);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(menu.getId())
                .toUri();
        Role role = roleService.listarRole(idRole);
        role.getMenus().add(menu);
        roleService.inseriRole(role);
        return ResponseEntity.created(uri).body(menuInserido);
    }

    @PutMapping(value = "/menu/atualizarMenu/{idRoleAntigo}/{idRoleNovo}")
    public ResponseEntity<Menu> atualizarMenu(@RequestBody Menu menu, @PathVariable Integer idRoleAntigo, @PathVariable Integer idRoleNovo) {
        if (idRoleAntigo != idRoleNovo) {
            Role roleIdRoleAntigo = roleService.listarRole(idRoleAntigo);
            Role roleIdRoleNovo = roleService.listarRole(idRoleNovo);
            roleIdRoleAntigo.getMenus().remove(menu);
            roleIdRoleNovo.getMenus().add(menu);
            roleService.inseriRole(roleIdRoleAntigo);
        }
        Menu menuAtualizado = menuService.inseriMenu(menu);
        return ResponseEntity.ok().body(menuAtualizado);
    }

    @DeleteMapping(value = "/menu/deletarMenu/{id}")
    public ResponseEntity<String> deletarMenu(@PathVariable Integer id) {
        String mensagemStatus = menuService.deletarMenu(id);
        return ResponseEntity.ok().body(mensagemStatus);
    }
}
