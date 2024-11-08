package TrabalhoAPS.Programa.GDB.Modelo;

import java.time.LocalDate;
import java.util.List;

public class Jogo extends Entidade {
    private String nome;
    private LocalDate dataLancamento;
    private String desenvolvedor;
    private String descricao;
    private String classificacaoEtaria;
    private List<Nota> notas;

    public Jogo(){
        this.setId(0);
        nome = null;
        dataLancamento = null;
        desenvolvedor = null;
        descricao = null;
        classificacaoEtaria = null;
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
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
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(String classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public List<Nota> getNotas() {
        return notas;
    }



}


