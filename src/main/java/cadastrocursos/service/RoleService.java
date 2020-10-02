package cadastrocursos.service;

import cadastrocursos.domain.Role;
import cadastrocursos.exceptions.DatabaseException;
import cadastrocursos.exceptions.ResourceNotFoundException;
import cadastrocursos.repository.RolelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class RoleService {

    @Autowired
    private RolelRepository rolelRepository;

    public List<Role> listarRoles() {
        List<Role> roles = rolelRepository.findAll();
        roles.forEach(perfil -> {
            perfil.getMenus().clear();
        });
        return roles;
    }

    public Role listarRole(Integer id) {
        try {
            Optional<Role> role = rolelRepository.findById(id);
            if (role != null) {
                role.get().getMenus().clear();
            }
            return role.get();
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Role não esta salvo na base de dados");
        }
    }

    public Role inseriRole(Role role) {
        if (role.getId() != null) {
            listarRole(role.getId());
        }
        return  rolelRepository.save(role);
    }

    public String deletarRole(Integer id) {
        try {
            rolelRepository.deleteById(id);
            return "Role excluída com sucesso!";
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro ao tentar excluir role!");
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new ResourceNotFoundException("Role não esta salvo na base de dados");
        }
    }
}
