package cadastrocursos.repository;

import cadastrocursos.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolelRepository extends JpaRepository<Role, Integer> {

}
