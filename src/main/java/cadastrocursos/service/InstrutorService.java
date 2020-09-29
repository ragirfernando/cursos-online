package cadastrocursos.service;

import cadastrocursos.domain.Curso;
import cadastrocursos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstrutorService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Curso> listarCursos() {
        List<Curso> cursos = alunoRepository.findAll();
        return cursos;
    }

    public List<Curso> listarCursosInstrutorMatriculado(Integer idPessoa) {
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
