package com.gdb.persistencia;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class Arquivo {

    // Classe não instanciável
    private Arquivo() {
    }

    /**
     * Lê as linhas de um arquivo CSV, ignorando o cabeçalho.
     *
     * @param caminho O caminho do arquivo CSV.
     * @return Um List&lt;String[]&gt; contendo as linhas lidas, excluindo o cabeçalho.
     */
    static List<String[]> ler(Path caminho) {
        List<String[]> linhas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(caminho.toString()))) {
            reader.skip(1);  // Pula o cabeçalho
            linhas = reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return linhas;
    }

    /**
     * Cria um novo arquivo com os dados atualizados.
     *
     * @param caminho   O caminho do arquivo CSV.
     * @param cabecalho O cabeçalho do arquivo CSV.
     * @param elementos Lista de elementos que será escrita no arquivo CSV.
     */
    static void substituir(Path caminho, String[] cabecalho, List<String[]> elementos) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(caminho.toString(), false),
                ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                "\n")) {
            writer.writeNext(cabecalho);
            writer.writeAll(elementos);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}