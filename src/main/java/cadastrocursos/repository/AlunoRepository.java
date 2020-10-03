package cadastrocursos.repository;

import cadastrocursos.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlunoRepository extends JpaRepository<Curso, Integer> {

}
