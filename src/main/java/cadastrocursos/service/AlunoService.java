package cadastrocursos.service;

import cadastrocursos.domain.Curso;
import cadastrocursos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Curso> listarCursos() {
        List<Curso> cursos = alunoRepository.findAll();
        cursos.forEach(curso -> {
            curso.getInstrutor().clear();
        });
        return cursos;
    }

    public List<Curso> listarCursosAlunoMatriculado(Integer idPessoa) {
        List<Curso> cursos = alunoRepository.findAll();
        List<Curso> cursosAlunoMatriculado = new ArrayList<>();
        cursos.forEach(curso -> {
            curso.getAlunos().stream().filter(pessoa -> pessoa.getId() == idPessoa).forEach(pessoa -> {
                cursosAlunoMatriculado.add(curso);
            });
        });
        return cursosAlunoMatriculado;
    }


}
