package cadastrocursos.controller;

import cadastrocursos.domain.Curso;
import cadastrocursos.domain.Pessoa;
import cadastrocursos.service.CursoService;
import cadastrocursos.service.InstrutorService;
import cadastrocursos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/instrutores")
public class InstrutorController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private InstrutorService instrutorService;

    @GetMapping(value = "/instrutor/cursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = instrutorService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping(value = "/instrutor/listarCursosInstrutorMatriculado/{idPessoa}")
    public ResponseEntity<List<Curso>> listarCursosAlunoMatriculado(@PathVariable Integer idPessoa) {
        List<Curso> cursos = instrutorService.listarCursosInstrutorMatriculado(idPessoa);
        return ResponseEntity.ok().body(cursos);
    }

    @PostMapping(value = "/instrutor/inserirInstrutorCurso/{idCurso}/{idPessoa")
    public ResponseEntity<String> inserirAluno(@PathVariable Integer idCurso, @PathVariable Integer idPessoa) {
        Curso curso = cursoService.listarCurso(idCurso);
        Pessoa pessoa = pessoaService.listarPessoasId(idPessoa);
        curso.getInstrutor().add(pessoa);
        Curso cursoInserido = cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(cursoInserido.getDescricao());
    }

    @DeleteMapping(value = "/instrutor/cancelarInscricao/{idCurso}/{idInstrutor}")
    public ResponseEntity<String> cancelarInscricao(@PathVariable Integer idCurso, @PathVariable Integer idInstrutor) {
        Curso curso = cursoService.listarCurso(idCurso);
        Pessoa pessoa = pessoaService.listarPessoasId(idInstrutor);
        curso.getInstrutor().remove(pessoa);
        Curso cursoInserido = cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(cursoInserido.getDescricao());
    }
}
