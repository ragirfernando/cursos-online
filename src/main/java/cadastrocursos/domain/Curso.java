package cadastrocursos.domain;


import com.sun.istack.NotNull;
import cadastrocursos.enums.StatusCurso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@ToString
@NoArgsConstructor
@Entity
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @NotNull
    @Getter
    @Setter
    private String descricao;

    @NotNull
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @NotNull
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @NotNull
    @Getter
    @Setter
    private Double valor;

    @NotNull
    @Getter
    @Setter
    private Integer quantidadeHoras;

    @NotNull
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private StatusCurso statusCurso;

    @Getter
    @ManyToMany
    @JoinTable(name = "curso_aluno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<Pessoa> alunos = new HashSet<>();

    @Getter
    @ManyToMany
    @JoinTable(name = "curso_instrutor",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "instrutor_id"))
    private Set<Pessoa> instrutor = new HashSet<>();

    @Getter
    @OneToMany
    private Set<HorarioAula> horarioAulas = new HashSet<>();


    public Curso(Integer id, String descricao, Date dataInicio, Date dataFim, Double valor, Integer quantidadeHoras, StatusCurso statusCurso) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.quantidadeHoras = quantidadeHoras;
        this.statusCurso = statusCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curso curso = (Curso) o;

        return id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
