package cadastrocursos.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode(exclude={"nome", "menus"})
@NoArgsConstructor
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_perfil",
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<Menu> menus = new ArrayList<>();

    public Role(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
