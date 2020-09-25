package cadastrocursos.service;

import cadastrocursos.exceptions.ResourceNotFoundException;
import cadastrocursos.repository.CursoRepository;
import cadastrocursos.domain.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos;
    }

    public List<Curso> listarCursosData(LocalDate dataInicial, LocalDate dataFim) {
        List<Curso> pesquisa = new ArrayList<>();
        List<Curso> cursos = new ArrayList<>();
        pesquisa.clear();
        cursos.clear();
        pesquisa = cursoRepository.findAll();
        pesquisa.stream().filter(curso -> curso.getDataInicio().isAfter(dataInicial.minusDays(1)) &&
                curso.getDataInicio().isBefore(dataFim.plusDays(1)))
                .forEach(curso -> {
                    cursos.add(curso);
                });
        return cursos;
    }

    public Curso inserirCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso listarCurso(Integer id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.orElseThrow(() -> new ResourceNotFoundException("Esse Curso não esta cadastrado"));
    }

    public String deletarCurso(Integer id) {
        try {
            cursoRepository.deleteById(id);
            return "Curso excluido";
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new ResourceNotFoundException("O curso não pode ser excluido");
        }
    }

}
