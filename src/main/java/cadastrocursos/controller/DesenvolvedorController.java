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

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/desenvolvedor/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping(value = "/desenvolvedor/roles")
    public ResponseEntity<List<Role>> listarRoles(){
        List<Role> perfis = roleService.listarRoles();
        return ResponseEntity.ok().body(perfis);
    }

    @GetMapping(value = "/desenvolvedor/role/{id}")
    public ResponseEntity<Role> listarRole(@PathVariable Integer id){
        Role role = roleService.listarRole(id);
        return ResponseEntity.ok().body(role);
    }

    @PostMapping(value = "/desenvolvedor/inserirRole")
    public ResponseEntity<Role> inserirRole(@RequestBody Role role){
       Role roleInserida = roleService.inseriRole(role);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();
        return ResponseEntity.created(uri).body(roleInserida);
    }

    @PutMapping(value = "/desenvolvedor/atualizarRole")
    public ResponseEntity<Role> atualizarRole(@RequestBody Role role){
        Role roleAtualizada = roleService.inseriRole(role);
        return ResponseEntity.ok().body(roleAtualizada);
    }

    @DeleteMapping(value = "/desenvolvedor/deletarRole/{id}")
    public ResponseEntity<String> deletarRole(@PathVariable Integer id){
        String mensagemStatus = roleService.deletarRole(id);
        return ResponseEntity.ok().body(mensagemStatus);
    }

    @GetMapping(value = "/desenvolvedor/menus")
    public ResponseEntity<List<Menu>> listarMenus(){
        List<Menu> menus = menuService.listarMenus();
        return ResponseEntity.ok().body(menus);
    }

    @GetMapping(value = "/desenvolvedor/menuId/{id}")
    public ResponseEntity<Menu> listarMenuId(@PathVariable Integer id){
        Menu menu = menuService.listarMenu(id);
        return ResponseEntity.ok().body(menu);
    }

    @PostMapping(value = "/desenvolvedor/inserirMenu")
    public ResponseEntity<Menu> inserirMenu(@RequestBody Menu menu){
        Menu menuInserido = menuService.inseriMenu(menu);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(menu.getId())
                .toUri();
        return ResponseEntity.created(uri).body(menuInserido);
    }

    @PutMapping(value = "/desenvolvedor/atualizarMenu")
    public ResponseEntity<Menu> atualizarMenu(@RequestBody Menu menu){
        Menu menuAtualizado = menuService.inseriMenu(menu);
        return ResponseEntity.ok().body(menuAtualizado);
    }

    @DeleteMapping(value = "/desenvolvedor/deletarMenu/{id}")
    public ResponseEntity<String> deletarMenu(@PathVariable Integer id){
        String mensagemStatus = menuService.deletarMenu(id);
        return ResponseEntity.ok().body(mensagemStatus);
    }
}
