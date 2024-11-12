package com.gdb.controle;

import com.gdb.modelo.Usuario;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioControle {

    private static final String FILE_PATH = "usuarios.csv";

    public static void salvarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(usuario.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                usuarios.add(Usuario.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static List<Usuario> ListaUsuariosForAdmin() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Cada linha será separada por vírgula, e adicionaremos os dados ao usuário
                String[] dados = linha.split(",");

                // Garantir que a linha tenha o número correto de dados
                if (dados.length == 4) {
                    String usuario = dados[0]; // Nome do usuário
                    String senha = dados[1];   // Senha do usuário
                    boolean isAdmin = Boolean.parseBoolean(dados[2]); // Administrador (true/false)
                    String dataNascimento = dados[3]; // Data de nascimento do usuário

                    // Criar o objeto Usuario e adicionar à lista
                    Usuario user = Usuario.cadastrarUsuario(usuario, senha, isAdmin, LocalDate.parse(dataNascimento));
                    usuarios.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios; // Retorna a lista de usuários
    }

    public static boolean validarLogin(String usuario, String senha) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            int linhaIndex = 0;

            while ((linha = br.readLine()) != null) {    // vai percorrer o arquivo
                String[] dados = linha.split(",");

                // Garantir que a linha tenha o número correto de dados
                if (dados.length >= 2) {
                    // compara sempre o que o user digitou com o primeiro item da linha do arquivo (usuario)
                    if (dados[0].equals(usuario)) {
                        // se o nome de usuario existe, testa a senha
                        if (dados[1].equals(senha)) {
                            return true; // valida o login
                        }
                        break; // a busca encerra, pois a senha nao bate (cabivel definir uma saida "Sua senha esta incorreta" dps desse break)
                    }
                }
                linhaIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Login falhou
    }

}
