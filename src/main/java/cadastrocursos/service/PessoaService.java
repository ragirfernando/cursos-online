package cadastrocursos.service;

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

    public Pessoa atualizarPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Integer id){
        try {
            pessoaRepository.deleteById(id);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
