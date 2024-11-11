package com.gdb.modelo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    /**
     * Obtém o caminho do arquivo CSV associado à classe fornecida.
     *
     * @param classe A classe usada para determinar o caminho do arquivo CSV.
     * @return O caminho do arquivo CSV correspondente à classe fornecida.
     */
    protected static Path obterCaminhoArquivo(Class<?> classe) {
        Path caminhoArquivo = null;
        if (classe.equals(Usuario.class)) {
            return Paths.get("src", "main", "resources", "data", "usuario.csv");
        } else {
            System.out.println("Erro ao obter o caminho do arquivo da classe " + classe + " !");
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
    protected static int contarLinhasArquivo(Path arquivoPath) {
        int linhasContadas = 0;
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            reader.readNext();  // Pula o cabeçalho
            while (reader.readNext() != null) {
                linhasContadas++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao contar as linhas do arquivo "
                    + arquivoPath.getFileName().toString()
                    + ": "
                    + e.getMessage());
            System.exit(1);
        }
        return linhasContadas;
    }

    /**
     * Lê as linhas de um arquivo CSV, ignorando o cabeçalho.
     *
     * @param arquivoPath O caminho do arquivo CSV a ser manipulado.
     * @return Um List<String[]> contendo as linhas lidas, excluindo o cabeçalho.
     */
    protected static List<String[]> lerArquivo(Path arquivoPath) {
        List<String[]> linhas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            reader.readNext();  // Pula o cabeçalho
            String[] linha;
            while ((linha = reader.readNext()) != null) {
                linhas.add(linha);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo "
                    + arquivoPath.getFileName().toString()
                    + ": "
                    + e.getMessage());
            System.exit(1);
        }
        return linhas;
    }

    /**
     * Realiza o append dos dados da classe no arquivo CSV correspondente.
     *
     * @param arquivoPath O caminho do arquivo CSV a ser manipulado.
     * @param classe      A instância da classe cujos dados serão salvos.
     */
    protected static void escreverArquivo(Path arquivoPath, Object classe) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoPath.toString(), true),
                ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                '\\',
                "\n")) {
            String[] atributos = Arquivo.obterAtributosClasse(classe);
            writer.writeNext(atributos);
        } catch (Exception e) {
            System.out.println("Erro ao escrever os dados da classe "
                    + classe.getClass().getSimpleName()
                    + " no arquivo "
                    + arquivoPath.getFileName()
                    + ": "
                    + e.getMessage());
            System.exit(1);
        }
    }

    protected static void deletarArquivo(Path arquivoPath, Integer id) {
        // TODO PEGAR CABEÇALHO
        String[] cabecalho = { "id", "nome", "idade" };
        // Pega as linhas atuais, remove a linha desejada e atualiza o id das seguintes
        List<String[]> linhas = Arquivo.lerArquivo(arquivoPath);
        for (int i = id; i < linhas.size(); i++) {
            String[] linha = linhas.get(i);
        }
        // Cria um novo arquivo sem a linha correspondente ao id
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoPath.toString(), false),
                ';',
                CSVWriter.NO_QUOTE_CHARACTER,
                '\\',
                "\n")) {
            writer.writeNext(cabecalho);
            for (String[] linha : linhas) {
                writer.writeNext(linha);
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar o objeto no arquivo "
                    + arquivoPath.getFileName()
                    + ": "
                    + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Obtém os atributos da classe, tenha ele uma classe mãe ou não.
     *
     * @param classe A instância da classe cujos atributos serão retornados.
     * @return Um String[] contendo os atributos da classe.
     */
    protected static String[] obterAtributosClasse(Object classe) throws IllegalAccessException {
        // Pega os atributos da classe
        Field[] camposClasse = classe.getClass().getDeclaredFields();
        String[] atributosClasseArrayString = new String[camposClasse.length];
        for (int i = 0; i < camposClasse.length; i++) {
            camposClasse[i].setAccessible(true);  // Garante acesso aos campos privados
            atributosClasseArrayString[i] = String.valueOf(camposClasse[i].get(classe));  // Obtém o valor do campo
        }
        // Concatena e retorna os atributos da classe mãe e filha, se a classe mãe existir
        Class<?> superClasse = classe.getClass().getSuperclass();
        if (superClasse != null) {
            Field[] camposSuperClasse = superClasse.getDeclaredFields();
            String[] atributosSuperClasseArrayString = new String[camposSuperClasse.length];
            for (int i = 0; i < camposSuperClasse.length; i++) {
                camposSuperClasse[i].setAccessible(true);  // Garante acesso aos campos privados
                atributosSuperClasseArrayString[i] = String.valueOf(camposSuperClasse[i].get(classe));  // Obtém o valor do campo
            }
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

    /**
     * Verifica se um valor já existe no arquivo.
     *
     * @param arquivoPath O caminho do arquivo CSV a ser manipulado.
     * @param coluna      A coluna onde o valor comparado.
     * @param valor       O valor que será comparado.
     * @return Um Boolean que indica se o valor existe no arquivo ou não.
     */
    protected static Boolean valorExisteArquivo(Path arquivoPath, String coluna, String valor) {
        try (CSVReader reader = new CSVReader(new FileReader(arquivoPath.toString()))) {
            // Descobre o índice da coluna
            String[] cabecalho = reader.readNext();
            cabecalho = cabecalho[0].split(";");
            int colunaIndice = 0;
            for (int i = 0; i < cabecalho.length; i++) {
                if (cabecalho[i].equals(coluna)) {
                    colunaIndice = i;
                    break;
                }
            }
            // Verifica se o valor existe na coluna
            String[] linha;
            while ((linha = reader.readNext()) != null) {
                String[] linhaArray = linha[0].split(";");
                if (linhaArray[colunaIndice].equals(valor)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se o valor '"
                    + valor
                    + "' existe no arquivo "
                    + arquivoPath.getFileName().toString()
                    + ": "
                    + e.getMessage());
            System.exit(1);
        }
        return false;
    }

}