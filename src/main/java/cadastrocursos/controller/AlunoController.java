package cadastrocursos.controller;

import cadastrocursos.domain.Curso;
import cadastrocursos.domain.Usuario;
import cadastrocursos.service.AlunoService;
import cadastrocursos.service.CursoService;
import cadastrocursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/aluno/cursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = alunoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping(value = "/aluno/listarCursosAlunoMatriculado/{idPessoa}")
    public ResponseEntity<List<Curso>> listarCursosAlunoMatriculado(@PathVariable Integer idPessoa) {
        List<Curso> cursos = alunoService.listarCursosAlunoMatriculado(idPessoa);
        return ResponseEntity.ok().body(cursos);
    }

    @PostMapping(value = "/aluno/inserirAlunoCurso/{idCurso}/{idAluno}")
    public ResponseEntity<String> inserirAluno(@PathVariable Integer idCurso, @PathVariable Integer idAluno) {
        Curso curso = cursoService.listarCurso(idCurso);
        Usuario usuario = usuarioService.listarUsuaioId(idAluno);
        curso.getAlunos().add(usuario);
        Curso cursoInserido = cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(cursoInserido.getDescricao());
    }

    @DeleteMapping(value = "/aluno/cancelarInscricao/{idCurso}/{idPessoa}")
    public ResponseEntity<String> cancelarInscricao(@PathVariable Integer idCurso, @PathVariable Integer idPessoa) {
        Curso curso = cursoService.listarCurso(idCurso);
        Usuario usuario = usuarioService.listarUsuaioId(idPessoa);
        curso.getAlunos().remove(usuario);
        Curso cursoInserido = cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(cursoInserido.getDescricao());
    }
}
