package cadastrocursos.service;

import cadastrocursos.repository.CursoRepository;
import cadastrocursos.domain.Curso;
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

    public Curso inserirCurso(Curso curso){
        return cursoRepository.save(curso);
    }

    public Curso listarCurso(Integer id){
        return cursoRepository.getOne(id);
    }


}
