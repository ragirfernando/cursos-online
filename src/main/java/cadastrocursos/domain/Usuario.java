package cadastrocursos.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@ToString
@NoArgsConstructor
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private String celular;

    @Getter
    @Setter
    private String rg;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private LocalDate dataNascimento;

    @Getter
    @Setter
    private String orgaoEmissor;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.ALL} ,  orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Role> perfis = new ArrayList<>();


    public Usuario(Integer id, String username, String password, String nome, String cpf, String celular, String rg, String email, LocalDate dataNascimento, String orgaoEmissor) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.rg = rg;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.orgaoEmissor = orgaoEmissor;
    }
}
