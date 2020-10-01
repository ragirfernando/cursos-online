package cadastrocursos.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"url"})
@Entity
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String url;


}
