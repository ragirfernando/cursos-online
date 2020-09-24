package cadastrocursos.service;

import cadastrocursos.domain.Pessoa;
import cadastrocursos.exceptions.DatabaseException;
import cadastrocursos.exceptions.ResourceNotFoundException;
import cadastrocursos.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa listarPessoasId(Integer id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElseThrow(() -> new ResourceNotFoundException("Não esta cadastrado no banco de dados"));
    }

    public Pessoa listarPessoasCPF(String cpf) {
        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaRepository.findByCpf(cpf));
        return pessoa.orElseThrow(() -> new ResourceNotFoundException("Não esta cadastrado no banco de dados"));
    }

    public Pessoa inserirPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Integer id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new ResourceNotFoundException("Não esta cadastrado no banco de dados");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DatabaseException("Não pode ser deletada, esta vinculada a um curso");
        }
    }
}
