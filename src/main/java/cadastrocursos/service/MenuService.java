package cadastrocursos.service;

import cadastrocursos.domain.Menu;
import cadastrocursos.exceptions.DatabaseException;
import cadastrocursos.exceptions.ResourceNotFoundException;
import cadastrocursos.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listarMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus;
    }

    public Menu listarMenu(Integer id) {
        try {
            Optional<Menu> menu = menuRepository.findById(id);
            return menu.get();
        }catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Menu não esta salvo na base de dados");
        }
    }

    public Menu inseriMenu(Menu menu) {
        if (menu.getId() != null) {
            listarMenu(menu.getId());
        }
        return  menuRepository.save(menu);
    }

    public String deletarMenu(Integer id) {
        try {
            menuRepository.deleteById(id);
            return "Menu excluída com sucesso!";
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro ao tentar excluir o menu!");
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new ResourceNotFoundException("Menu não esta salvo na base de dados");
        }
    }
}
