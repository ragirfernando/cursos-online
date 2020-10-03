package cadastrocursos.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resposta implements Serializable {

    private Integer IdUsuario;

    private Integer Status;

    private String token;

    private String nome;

    private List<String> urls = new ArrayList<>();



}
