package cadastrocursos.controller;

import cadastrocursos.domain.Pessoa;
import cadastrocursos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "pessoa/pessoas")
    public ResponseEntity<List<Pessoa>> listarPessoas(){
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok().body(pessoas);
    }

    @GetMapping(value = "/pessoa/listarPessoasId/{id}")
    public ResponseEntity<Pessoa> listarPessoasId(@PathVariable Integer id){
        Pessoa pessoa = pessoaService.listarPessoasId(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping(value = "/pessoa/listarPessoasCPF/{cpf}")
    public ResponseEntity<Pessoa> listarPessoasCPF(@PathVariable String cpf){
        Pessoa pessoa = pessoaService.listarPessoasCPF(cpf);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping(value = "/pessoa/inserirPessoa")
    public ResponseEntity<Pessoa> inserirPessoa(@RequestBody Pessoa pessoa){
        pessoa = pessoaService.inserirPessoa(pessoa);
        return  ResponseEntity.ok().body(pessoa);
    }

    @PutMapping(value = "/pessoa/atualizarPessoa")
    public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody Pessoa pessoa){
        pessoa = pessoaService.atualizarPessoa(pessoa);
        return ResponseEntity.ok().body(pessoa);
    }


    @DeleteMapping(value = "/pessoa/deletarPessoaId/{id}")
    public ResponseEntity<Void>  deletarPessoa(@PathVariable Integer id){
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
