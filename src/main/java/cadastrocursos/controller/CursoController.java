package cadastrocursos.controller;

import cadastrocursos.domain.Curso;
import cadastrocursos.domain.Pessoa;
import cadastrocursos.service.CursoService;
import cadastrocursos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private PessoaService pessoaService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/cursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping(value = "/listarCursosData/{dataInicio}/{dataFim}")
    public ResponseEntity<List<Curso>> listarCursosData(@PathVariable String dataInicio, @PathVariable String dataFim) throws ParseException {
        List<Curso> cursos = cursoService.listarCursosData(LocalDate.parse(dataInicio), LocalDate.parse(dataFim));
        return ResponseEntity.ok().body(cursos);
    }

    @PostMapping(value = "/inserirCurso")
    public ResponseEntity<Curso> inserirCurso(@RequestBody Curso curso) {
        curso = cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(curso);
    }

    @PostMapping(value = "/inserirAluno/{idCurso}/{idAluno}")
    public ResponseEntity<String> inserirAluno(@PathVariable Integer idCurso, @PathVariable Integer idAluno) {
        Curso curso = cursoService.listarCurso(idCurso);
        Pessoa pessoa = pessoaService.listarPessoasId(idAluno);
        curso.getAlunos().add(pessoa);
        inserirCurso(curso);
        return ResponseEntity.ok().body(curso.getDescricao());
    }

    @PostMapping(value = "/inserirInstrutor/{idCurso}/{idInstrutor}")
    public ResponseEntity<String> inserirInstrutor(@PathVariable Integer idCurso, @PathVariable Integer idInstrutor) {
        Curso curso = cursoService.listarCurso(idCurso);
        Pessoa pessoa = pessoaService.listarPessoasId(idInstrutor);
        curso.getInstrutor().add(pessoa);
        inserirCurso(curso);
        return ResponseEntity.ok().body(curso.getDescricao());
    }

    @DeleteMapping(value = "/deletarCurso/{idCurso}")
    public ResponseEntity<String> deletarCurso(@PathVariable Integer idCurso) {
        String mensagem = cursoService.deletarCurso(idCurso);
        return ResponseEntity.ok().body(mensagem);
    }

}
