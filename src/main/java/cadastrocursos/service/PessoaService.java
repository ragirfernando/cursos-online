package cadastrocursos.service;

import cadastrocursos.domain.Curso;
import cadastrocursos.domain.Pessoa;
import cadastrocursos.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarPessoas(){
        return pessoaRepository.findAll();
    }

    public Pessoa inserirPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
}
