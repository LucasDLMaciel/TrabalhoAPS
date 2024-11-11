package com.gdb.modelo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Arquivo {

    /**
     * Lê as linhas de um arquivo CSV, ignorando o cabeçalho.
     *
     * @param arquivoPath O caminho do arquivo CSV a ser manipulado.
     * @return Um List&lt;String&gt; contendo as linhas lidas, excluindo o cabeçalho.
     */
    static List<String[]> lerArquivo(Path arquivoPath) {
        List<String[]> linhas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            reader.readNext();  // Pula o cabeçalho
            String[] linha;
            while ((linha = reader.readNext()) != null) {
                linhas.add(linha);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo '"
                    + arquivoPath.getFileName().toString()
                    + "': "
                    + e.getMessage());
            System.exit(1);
        }
        return linhas;
    }

    /**
     * Cria um novo arquivo com os dados atualizados.
     *
     * @param arquivoPath O caminho do arquivo CSV a ser manipulado.
     * @param elementos Lista de elementos que será escrita no arquivo.
     */
    static void substituirArquivo(Path arquivoPath, List<?> elementos) {
        String[] cabecalho = Arquivo.pegarCabecalhoArquivo(arquivoPath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoPath.toString(), false),
                ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                '\\',
                "\n")) {
            writer.writeNext(cabecalho);
            for (Object elemento : elementos) {
                writer.writeNext(Global.obterAtributosClasse(elemento));
            }
        } catch (Exception e) {
            System.out.println("Erro ao substituir o arquivo '"
                    + arquivoPath.getFileName()
                    + "': "
                    + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Obtém o caminho do arquivo CSV associado à classe fornecida.
     *
     * @param classe A classe usada para determinar o caminho do arquivo CSV.
     * @return O caminho do arquivo CSV correspondente à classe fornecida.
     */
    static Path obterCaminhoArquivo(Class<?> classe) {
        Path caminhoArquivo = null;
        if (classe.equals(Usuario.class)) {
            return Paths.get("src", "main", "resources", "data", "usuario.csv");
        } else {
            System.out.println("Erro ao obter o caminho do arquivo da classe '" + classe + "'!");
            System.exit(1);
        }
        return caminhoArquivo;
    }

    /**
     * Conta a quantidade de linhas de um arquivo CSV, ignorando o cabeçalho.
     *
     * @param arquivoPath O caminho do arquivo CSV a ser manipulado.
     * @return A quantidade de linhas do arquivo, excluindo o cabeçalho.
     */
    static int contarLinhasArquivo(Path arquivoPath) {
        int linhasContadas = 0;
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            reader.readNext();  // Pula o cabeçalho
            while (reader.readNext() != null) {
                linhasContadas++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao contar as linhas do arquivo '"
                    + arquivoPath.getFileName().toString()
                    + "': "
                    + e.getMessage());
            System.exit(1);
        }
        return linhasContadas;
    }

    static String[] pegarCabecalhoArquivo(Path arquivoPath) {
        String[] cabecalho = null;
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            cabecalho = reader.readNext();
        } catch (Exception e) {
            System.out.println("Erro ao ler o cabeçalho do arquivo '"
                    + arquivoPath.getFileName().toString()
                    + "': "
                    + e.getMessage());
            System.exit(1);
        }
        return cabecalho;
    }

}