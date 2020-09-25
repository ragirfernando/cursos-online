package cadastrocursos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@ToString
@Getter
@Setter
@EqualsAndHashCode(exclude={"username", "password", "admin"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private boolean admin;


}
