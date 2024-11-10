package com.gdb.modelo;
import com.opencsv.CSVReader;
import java.io.FileReader;

class Entidade {
    private Integer id;

    protected Entidade() {
        try {
            Integer id = this.cadastrarEntidade();
        } catch (Exception e) {
            System.out.println("Erro ao manipular o arquivo Usuario.csv: " + e.getMessage());
            System.exit(1);
        }
    }

    private void setId(Integer id) {
        this.id = id;
    }

    protected Integer getId() {
        return id;
    }

    private Integer cadastrarEntidade() throws Exception {
        String arquivoPath = "../../resources/data/usuario.csv";
        CSVReader reader = new CSVReader(new FileReader(arquivoPath));
        reader.readNext();  // Pula o cabeçalho
        int linhasContadas = 0;
        String[] linha;
        while ((linha = reader.readNext()) != null) {
            linhasContadas++;
        }
        return linhasContadas + 1;
    }

    // AO DELETAR UMA ENTRADA NO BANCO, OS IDs DEVEM SER ATUALIZADOS EM ORDEM CRESCENTE
}
