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
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping(value = "/inserirCurso")
    public ResponseEntity<Curso> inserirCurso(@RequestBody Curso curso){
        curso =  cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(curso);
    }

    @PostMapping(value = "/inserirAluno/{idCurso}")
    public ResponseEntity<Pessoa> inserirAluno(@RequestBody Pessoa pessoa, @PathVariable Integer idCurso){
        Curso curso = cursoService.listarCurso(idCurso);
        pessoa =  pessoaService.inserirPessoa(pessoa);
        curso.getAlunos().add(pessoa);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping(value = "/cursos")
    public ResponseEntity<List<Curso>> listarCursos(){
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

}
