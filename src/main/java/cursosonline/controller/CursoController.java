package cursosonline.controller;

import cursosonline.domain.Curso;
import cursosonline.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(value = "cursos")
    public ResponseEntity<List<Curso>> listarCursos(){
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }
}
