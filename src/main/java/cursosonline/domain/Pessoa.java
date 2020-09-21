package cursosonline.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@Getter
@Setter
@EqualsAndHashCode(exclude={"nome", "matricula", "cpf", "celular", "rg", "dataNascimento", "orgaoEmissor", "endereco"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String matricula;

    private String cpf;

    private String celular;

    private String rg;

    private Date dataNascimento;

    private String orgaoEmissor;

    @OneToOne(cascade = {CascadeType.ALL} ,  orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

}
