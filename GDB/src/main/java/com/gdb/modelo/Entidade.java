package com.gdb.modelo;

import java.nio.file.Path;

class Entidade {

    private Integer id;

    /**
     * Construtor de uma instância que não existe no arquivo CSV.
     */
    protected Entidade() {
        this.setId(this.descobrirNovoId());
    }

    /**
     * Construtor de uma instância que já existe no arquivo CSV.
     */
    protected Entidade(Integer id) {
        this.setId(id);
    }

    void setId(Integer id) {
        this.id = id;
    }

    protected Integer getId() {
        return id;
    }

    /**
     * Obtém o próximo id disponível para cadastro de uma nova entidade no arquivo CSV.
     *
     * @return O próximo id disponível, o qual é a quantidade total de linhas no arquivo mais um, excluindo o cabeçalho.
     */
    private Integer descobrirNovoId() {
        Path arquivoPath = Arquivo.obterCaminhoArquivo(this.getClass());
        int linhasContadas = Arquivo.contarLinhasArquivo(arquivoPath);
        return linhasContadas + 1;
    }

}