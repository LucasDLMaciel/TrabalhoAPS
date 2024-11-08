package TrabalhoAPS.GDB.GDB.Modelo;

import java.util.List;

public class Jogo extends ID{
    private String nome;
    private String dataLancamento;
    private String desenvolvedor;
    private String descricao;
    private String faixaEtaria;
    private List<Nota> notas;

    public Jogo(){
        this.setId(0);
        nome = null;
        dataLancamento = null;
        desenvolvedor = null;
        descricao = null;
        faixaEtaria = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNotas(List<Nota> notas) {
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

    public List<Nota> getNotas() {
        return notas;
    }



}


