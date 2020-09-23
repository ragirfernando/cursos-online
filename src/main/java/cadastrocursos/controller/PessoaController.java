package cadastrocursos.controller;

import cadastrocursos.domain.Curso;
import cadastrocursos.domain.Pessoa;
import cadastrocursos.service.CursoService;
import cadastrocursos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/pessoas")
    public ResponseEntity<List<Pessoa>> listarPessoas(){
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok().body(pessoas);
    }

    @PostMapping(value = "/inserirPessoa")
    public ResponseEntity<Pessoa> inserirPessoa(@RequestBody Pessoa pessoa){
        pessoa = pessoaService.inserirPessoa(pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @PutMapping(value = "/atualizarPessoa")
    public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody Pessoa pessoa){
        pessoa = pessoaService.atualizarPessoa(pessoa);
        return ResponseEntity.ok().body(pessoa);
    }


    @DeleteMapping(value = "/deletarPessoaId/{id}")
    public ResponseEntity<Void>  deletarPessoa(@PathVariable Integer id){
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
