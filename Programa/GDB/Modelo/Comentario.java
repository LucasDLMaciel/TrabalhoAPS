package TrabalhoAPS.GDB.GDB.Modelo;

public class Comentario extends ID{
    private Integer idUsuario;
    private Integer idJogo;
    private String comentario;
    private Double Nota;
    private Integer dataComentario;

    public Comentario() {
        this.setId(0);
        idUsuario = null;
        idJogo = null;
        comentario = null;
        Nota = null;
        dataComentario = null;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(Integer idJogo) {
        this.idJogo = idJogo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getNota() {
        return Nota;
    }

    public void setNota(Double Nota) {
        this.Nota = Nota;
    }

    public Integer getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(Integer dataComentario) {
        this.dataComentario = dataComentario;
    }
}
