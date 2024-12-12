package com.gdb.modelo;

import java.util.List;

public class Jogo extends Entidade {
    private String titulo;
    private String descricao;
    private String classificacaoEtaria;
    private String dataLancamento;
    private List<Genero> generos;
    private List<Nota> notas;

    public Jogo(String titulo, String dataLancamento, String descricao, String classificacaoEtaria, List<Nota> notas, List<String> generos) {
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.classificacaoEtaria = classificacaoEtaria;
        this.notas = notas;
        this.generos = generos;
    }

    public void adicionarNota(Nota nota) {
        notas.add(nota);
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public void setClassificacaoEtaria(String classificacaoEtaria) {
        this.classificacaoEtaria = classificacaoEtaria;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
