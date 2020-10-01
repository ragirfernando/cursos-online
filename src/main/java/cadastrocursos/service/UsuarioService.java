package cadastrocursos.service;

import cadastrocursos.domain.Usuario;
import cadastrocursos.exceptions.DatabaseException;
import cadastrocursos.exceptions.ResourceNotFoundException;
import cadastrocursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService  {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario listarUsuaioId(Integer id) {
        Optional<Usuario> pessoa = usuarioRepository.findById(id);
        return pessoa.orElseThrow(() -> new ResourceNotFoundException("Não esta cadastrado no banco de dados"));
    }



    public Usuario listarUsuariosCPF(String cpf) {
        Optional<Usuario> pessoa = Optional.ofNullable(usuarioRepository.findByCpf(cpf));
        return pessoa.orElseThrow(() -> new ResourceNotFoundException("Não esta cadastrado no banco de dados"));
    }

    public Usuario inserirUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletarPessoa(Integer id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new ResourceNotFoundException("Não esta cadastrado no banco de dados");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DatabaseException("Não pode ser deletada, esta vinculada a um curso");
        }
    }
}
