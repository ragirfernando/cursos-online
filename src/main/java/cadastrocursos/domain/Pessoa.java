package cadastrocursos.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cpf;

    private String celular;

    private String rg;

    private String email;

    private LocalDate dataNascimento;

    private String orgaoEmissor;

    @OneToOne(cascade = {CascadeType.ALL} ,  orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;


    public Pessoa(Integer id, String nome, String cpf, String celular, String rg, String email, LocalDate dataNascimento, String orgaoEmissor) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.rg = rg;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.orgaoEmissor = orgaoEmissor;
    }


}
