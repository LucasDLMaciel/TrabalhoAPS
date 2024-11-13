package com.gdb.persistencia;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class UsuarioArquivo {

    private static final Path caminho = Paths.get("src", "main", "resources", "data", "usuario.csv");
    private static final String[] cabecalho = {"id", "usuario", "senha", "administradorFlag", "dataNascimento"};

    // Classe não instanciável
    private UsuarioArquivo() {
    }

    public static Path getCaminho() {
        return UsuarioArquivo.caminho;
    }

    public static String[] getCabecalho() {
        return UsuarioArquivo.cabecalho;
    }

    public static List<String[]> ler() {
        return Arquivo.ler(UsuarioArquivo.getCaminho());
    }

    public static void substituir(List<String[]> elementos) {
        Arquivo.substituir(UsuarioArquivo.getCaminho(), UsuarioArquivo.getCabecalho(), elementos);
    }

}
