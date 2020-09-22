package cadastrocursos.controller;

import cadastrocursos.domain.Curso;
import cadastrocursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping(value = "/inserirCurso")
    public ResponseEntity<Curso> inserirCurso(@RequestBody Curso curso){
        System.out.println(curso);
        curso =  cursoService.inserirCurso(curso);
        return ResponseEntity.ok().body(curso);
    }

    @GetMapping(value = "/cursos")
    public ResponseEntity<List<Curso>> listarCursos(){
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok().body(cursos);
    }

}
