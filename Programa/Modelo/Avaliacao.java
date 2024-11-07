package TrabalhoAPS.Programa.Modelo;

public class Avaliacao {
    private int ID;
    private Nota notas;
    private Comentario comentario;

    public Avaliacao() {
        this.notas = null;
        this.comentario = null;
    }

    public Nota getNotas() {
        return notas;
    }

    public void setNotas(Nota notas) {
        this.notas = notas;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
