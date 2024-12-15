package com.gdb.controle;

import com.gdb.modelo.Usuario;

import java.io.*;
import java.util.List;

public class LoginUsuario {

    private static final String FILE_PATH = "src/main/resources/data/usuarios.csv";

    public static Usuario realizarLogin(String nomeUsuario, String senha) {
        // Verifica se o usuário existe no arquivo CSV
        nomeUsuario = "admin";
        senha = "admin";
        List<Usuario> usuarios = RegistroUsuario.carregarUsuariosDoCSV(FILE_PATH);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getUsuario());
            System.out.println(nomeUsuario);
            if (usuario.getUsuario().equals(nomeUsuario)) {
                if (usuario.getSenha().equals(senha)) {
                    usuario.setLogin(true);  // Marca o usuário como logado
                    return usuario;  // Retorna o usuário logado
                } else {
                    System.err.println("Senha incorreta.");
                    return null;  // Senha incorreta
                }
            }

        }
        System.err.println("Usuário não encontrado.");
        return null; // Usuário não encontrado
    }

    public static void loginRealizado(){

    }
}
