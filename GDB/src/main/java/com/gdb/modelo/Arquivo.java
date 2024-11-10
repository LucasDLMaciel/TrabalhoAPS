package com.gdb.modelo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.lang.reflect.Field;

import java.util.Arrays;

public class Arquivo {

    /**
     * Obtém o caminho do arquivo CSV associado à classe fornecida.
     *
     * @param classe A classe usada para determinar o caminho do arquivo CSV.
     * @return O caminho do arquivo CSV correspondente à classe fornecida.
     */
    public static Path obterCaminhoArquivo(Class<?> classe) {
        Path caminhoArquivo = null;
        if (classe.equals(Usuario.class)) {
            return Paths.get("src", "main", "resources", "data", "usuario.csv");
        } else {
            System.out.println("Erro inesperado ocorreu ao obter o caminho do arquivo da classe " + classe + " !");
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
    public static int contarLinhasArquivo(Path arquivoPath) {
        int linhasContadas = 0;
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            reader.readNext();  // Pula o cabeçalho
            while (reader.readNext() != null) {
                linhasContadas++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao manipular o arquivo " + arquivoPath.getFileName().toString() + ": " + e.getMessage());
            System.exit(1);
        }
        return linhasContadas;
    }

    public static void salvarNovaLinhaArquivo(Path arquivoPath, Object classe) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoPath.toString(), true),
                ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                '\\',
                "\n")) {
            String[] atributos = Arquivo.obterAtributosClasse(classe);
            writer.writeNext(atributos);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o usuário em Usuario.csv: " + e.getMessage());
            System.exit(1);
        }
    }

    public static String[] obterAtributosClasse(Object classe) throws IllegalAccessException {
        String[] atributosSuperClasseArrayString = null;

        Class<?> superClasse = classe.getClass().getSuperclass();
        if (superClasse != null) {
            Field[] camposSuperClasse = superClasse.getDeclaredFields();
            atributosSuperClasseArrayString = new String[camposSuperClasse.length];
            for (int i = 0; i < camposSuperClasse.length; i++) {
                camposSuperClasse[i].setAccessible(true);  // Garante acesso aos campos privados
                atributosSuperClasseArrayString[i] = String.valueOf(camposSuperClasse[i].get(classe));  // Obtém o valor do campo
            }
        }

        Field[] camposClasse = classe.getClass().getDeclaredFields();
        String[] atributosClasseArrayString = new String[camposClasse.length];
        for (int i = 0; i < camposClasse.length; i++) {
            camposClasse[i].setAccessible(true);  // Garante acesso aos campos privados
            atributosClasseArrayString[i] = String.valueOf(camposClasse[i].get(classe));  // Obtém o valor do campo
        }

        if (atributosSuperClasseArrayString != null) {
            String[] atributos = Arrays.copyOf(atributosSuperClasseArrayString,
                    atributosSuperClasseArrayString.length + atributosClasseArrayString.length);
            System.arraycopy(atributosClasseArrayString,
                    0,
                    atributos,
                    atributosSuperClasseArrayString.length,
                    atributosClasseArrayString.length);
            return atributos;
        }

        return atributosClasseArrayString;
    }

}