package modelo;

public class Nota extends Entidade{
    private Integer idUsuario;
    private Integer notaTrilhaSonora;
    private Integer notaGraficos;
    private Integer notaHistoria;
    private Integer notaJogabilidade;
    private String comentario;

    public Nota(Integer idUsuario, Integer notaTrilhaSonora, Integer notaGraficos, Integer notaHistoria, Integer notaJogabilidade, String comentario) {
        this.notaTrilhaSonora = notaTrilhaSonora;
        this.notaGraficos = notaGraficos;
        this.notaHistoria = notaHistoria;
        this.notaJogabilidade = notaJogabilidade;
        this.comentario = comentario;
        this.idUsuario = idUsuario;
    }

    public Integer getNotaTrilhaSonora() {
        return notaTrilhaSonora;
    }

    public Integer getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario){
        this.idUsuario = idUsuario;
    }

    public Integer getNotaGraficos() {
        return notaGraficos;
    }

    public Integer getNotaHistoria() {
        return notaHistoria;
    }

    public Integer getNotaJogabilidade() {
        return notaJogabilidade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setNotaTrilhaSonora(Integer notaTrilhaSonora) {
        this.notaTrilhaSonora = notaTrilhaSonora;
    }

    public void setNotaGraficos(Integer notaGraficos) {
        this.notaGraficos = notaGraficos;
    }

    public void setNotaHistoria(Integer notaHistoria) {
        this.notaHistoria = notaHistoria;
    }

    public void setNotaJogabilidade(Integer notaJogabilidade) {
        this.notaJogabilidade = notaJogabilidade;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}


