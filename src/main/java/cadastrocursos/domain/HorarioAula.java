package cadastrocursos.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"data", "inicioAula", "inicioIntervalo", "fimIntervalo", "fimAula"})
@Entity
public class HorarioAula implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date data;

    @Temporal(TemporalType.TIME)
    private Date inicioAula;

    @Temporal(TemporalType.TIME)
    private Date inicioIntervalo;

    @Temporal(TemporalType.TIME)
    private Date fimIntervalo;

    @Temporal(TemporalType.TIME)
    private Date fimAula;


}
