package cadastrocursos.enums;

public enum StatusCurso {

    INSCRICOES_ABERTAS("Inscrições Abertas"),
    INSCRICOES_ENCERRADAS("Inscrições Encerradas"),
    CURSO_EM_ANDAMENTO("Curso em Andamento"),
    CURSO_ENCERRADO("Curso encerrado");

    private final String status;

    StatusCurso(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
