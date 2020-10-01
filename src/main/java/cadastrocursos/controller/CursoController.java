package cadastrocursos.controller;

import cadastrocursos.domain.Curso;
import cadastrocursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/admins")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @GetMapping(value = "/admin/curso/cursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

    @GetMapping(value = "/admin/curso/listarCursosData/{dataInicio}/{dataFim}")
    public ResponseEntity<List<Curso>> listarCursosData(@PathVariable String dataInicio, @PathVariable String dataFim) throws ParseException {
        List<Curso> cursos = cursoService.listarCursosData(LocalDate.parse(dataInicio), LocalDate.parse(dataFim));
        return ResponseEntity.ok().body(cursos);
    }

    @PostMapping(value = "/admin/curso/inserirCurso")
    public ResponseEntity<Curso> inserirCurso(@RequestBody Curso curso) {
        curso = cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(curso);
    }

    @DeleteMapping(value = "/admin/curso/deletarCurso/{idCurso}")
    public ResponseEntity<String> deletarCurso(@PathVariable Integer idCurso) {
        String mensagem = cursoService.deletarCurso(idCurso);
        return ResponseEntity.ok().body(mensagem);
    }

    @PutMapping(value = "/admin/curso/atualizarCurso")
    public ResponseEntity<Curso> atualizarCurso(@RequestBody Curso curso) {
        curso = cursoService.atualizarCurso(curso);
        return ResponseEntity.ok().body(curso);
    }
}
