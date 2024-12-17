package com.gdb.modelo;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public final class Global {

    private static List<Usuario> usuarios;

    private Global() {
    }

    public static List<Usuario> getUsuarios() {
        if (Global.usuarios == null) {
            Global.usuarios = carregarUsuarios();
        }
        return Global.usuarios;
    }

    /**
     * Carrega todos os usuários existentes do arquivo CSV.
     *
     * @return Um List&lt;Usuario&gt; com todos os usuários existentes no arquivo CSV.
     */
    private static List<Usuario> carregarUsuarios() {
        Path arquivoPath = Arquivo.obterCaminhoArquivo(Usuario.class);
        List<String[]> usuariosListaString = Arquivo.lerArquivo(arquivoPath);
        return Usuario.instanciarUsuarios(usuariosListaString);
    }

    /**
     * Salva todos os usuários existentes no arquivo CSV.
     */
    public static void salvarUsuarios() {
        Path arquivoPath = Arquivo.obterCaminhoArquivo(Usuario.class);
        Arquivo.substituirArquivo(arquivoPath, Global.usuarios);
    }

    /**
     * Obtém os atributos da classe, tenha ela uma classe mãe ou não.
     *
     * @param classe A instância da classe cujos atributos serão retornados.
     * @return Um String[] contendo os atributos da classe.
     */
    public static String[] obterAtributosClasse(Object classe) throws IllegalAccessException {
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

}