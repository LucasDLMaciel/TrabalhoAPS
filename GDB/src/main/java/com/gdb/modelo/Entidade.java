package com.gdb.modelo;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

class Entidade {
    private Integer id;

    protected Entidade() {
        this.id = this.cadastrarEntidade();
    }

    private void setId(Integer id) {
        this.id = id;
    }

    protected Integer getId() {
        return id;
    }

    private Integer cadastrarEntidade() {
        Path arquivoPath = obterCaminhoArquivo();
        int linhasContadas = contarLinhasArquivo(arquivoPath);
        return linhasContadas + 1;
    }

    private Path obterCaminhoArquivo() {
        Path arquivoPath = null;
        if (this instanceof Usuario) {
            arquivoPath = Paths.get("src", "main", "resources", "data", "usuario.csv");
        } else {
            System.out.println("Erro inesperado ocorreu ao cadastrar entidade!");
            System.exit(1);
        }
        return arquivoPath;
    }

    private int contarLinhasArquivo(Path arquivoPath) {
        int linhasContadas = 0;
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            reader.readNext();  // Pula o cabeçalho
            while (reader.readNext() != null) {
                linhasContadas++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao manipular o arquivo Usuario.csv: " + e.getMessage());
            System.exit(1);
        }
        return linhasContadas + 1;
    }

    // AO DELETAR UMA ENTRADA NO BANCO, OS IDs DEVEM SER ATUALIZADOS EM ORDEM CRESCENTE

}
