package cursosonline.service;

import cursosonline.domain.Curso;
import cursosonline.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos(){
        List<Curso> cursos = cursoRepository.findAll();
        return cursos;
    }
}
