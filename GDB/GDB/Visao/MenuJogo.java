package TrabalhoAPS.Programa.Modelo;

public class Jogo {
    private Integer ID;
    private String nome;
    private String dataLancamento;
    private String desenvolvedor;
    private String descricao;
    private String faixaEtaria;
    private Nota notas;

    public Jogo(){
        ID = null;
        nome = null;
        dataLancamento = null;
        desenvolvedor = null;
        descricao = null;
        faixaEtaria = null;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNotas(Nota notas) {
        this.notas = notas;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFaxaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public Nota getNotas() {
        return notas;
    }



}


