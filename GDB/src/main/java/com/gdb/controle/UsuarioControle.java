package com.gdb.controle;

import com.gdb.modelo.Usuario;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.gdb.controle.RegistroUsuario;

public class UsuarioControle {

    private static final String FILE_PATH = "src/main/resources/data/usuarios.csv";

//    public static int salvarUsuario(Usuario usuario) {
//        if(!usuarioExisteNoCSV("src/main/resources/data/usuarios.csv",usuario.getUsuario())){
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
//                writer.write(toCSV(usuario));
//                writer.newLine();
//                return 1;
//            } catch (IOException e) {
//                System.err.println("Erro ao salvar o usuário no arquivo CSV: " + e.getMessage());
//            }
//        } return 0;
//    }  RECORTADO PARA RegistroUsuario

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

//    public static List<Usuario> carregarUsuariosDoCSV(String filePath) {
//        List<Usuario> usuarios = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String linha;
//            while ((linha = reader.readLine()) != null) {
//                Usuario usuario = fromCSV(linha); // Converte a linha para um objeto Usuario
//                usuarios.add(usuario); // Adiciona o usuário à lista
//            }
//        } catch (IOException e) {
//            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
//        }
//        return usuarios; // Retorna a lista de usuários
//    }
//
//    public static boolean usuarioExisteNoCSV(String filePath, String nomeUsuario) {
//        List<Usuario> usuarios = carregarUsuariosDoCSV(filePath); // Carrega os usuários do arquivo
//        return usuarios.stream().anyMatch(usuario -> usuario.getUsuario().equals(nomeUsuario));
//    }
//
//    public static Usuario fromCSV(String csvLine) {
//        String[] values = csvLine.split(",");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dataNascimento = LocalDate.parse(values[3], formatter);
//        return new Usuario(values[0], values[1], Boolean.parseBoolean(values[2]), dataNascimento);
//    }
//
////    public static String toCSV(Usuario user) {
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////        return String.join(",",
////                user.getUsuario(),
////                user.getSenha(),
////                user.getAdministradorFlag().toString(),
////                user.getDataNascimento().format(formatter)
////        );
////    }  RECORTADO PARA RegistroUsuario
//
    public static Boolean usuarioExiste(List<Usuario> usuarios, String usuarioString) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(usuarioString)) {
                return true;
            }
        }
        return false;
    }

}
