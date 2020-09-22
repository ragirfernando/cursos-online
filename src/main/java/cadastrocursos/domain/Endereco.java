package cadastrocursos.domain;


import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@ToString
@Getter
@Setter
@EqualsAndHashCode(exclude={"cep", "logradouro", "complemento", "bairro", "localidade", "uf" , "numero"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String cep;

    @NotNull
    private String logradouro;

    @NotNull
    private String complemento;

    @NotNull
    private Integer numero;

    @NotNull
    private String bairro;

    @NotNull
    private String localidade;

    @NotNull
    private String uf;

}
