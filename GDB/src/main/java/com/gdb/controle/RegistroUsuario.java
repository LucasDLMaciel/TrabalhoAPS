package com.gdb.controle;

import com.gdb.controle.UsuarioControle;
import com.gdb.modelo.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//import com.gdb.modelo.Usuario;
//import java.io.*;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;

public class RegistroUsuario {

    private static final String FILE_PATH = "src/main/resources/data/usuarios.csv";

    public static int salvarUsuario(Usuario usuario) {
        if(!usuarioExisteNoCSV("src/main/resources/data/usuarios.csv",usuario.getUsuario())){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(escreverNoCSV(usuario));
                writer.newLine();
                return 1;
            } catch (IOException e) {
                System.err.println("Erro ao salvar o usuário no arquivo CSV: " + e.getMessage());
            }
        } return 0;
    }

    private static String escreverNoCSV(Usuario user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.join(",",
                user.getUsuario(),
                user.getSenha(),
                user.getAdministradorFlag().toString(),
                user.getDataNascimento().format(formatter)
        );
    }

    public static boolean usuarioExisteNoCSV(String filePath, String nomeUsuario) {
        List<Usuario> usuarios = carregarUsuariosDoCSV(filePath); // Carrega os usuários do arquivo
        return usuarios.stream().anyMatch(usuario -> usuario.getUsuario().equals(nomeUsuario));
    }

    public static Boolean usuarioExiste(List<Usuario> usuarios, String usuarioString) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(usuarioString)) {
                return true;
            }
        }
        return false;
    }

    public static Usuario fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(values[3], formatter);
        return new Usuario(values[0], values[1], Boolean.parseBoolean(values[2]), dataNascimento);
    }

    public static List<Usuario> carregarUsuariosDoCSV(String filePath) {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Usuario usuario = fromCSV(linha); // Converte a linha para um objeto Usuario
                usuarios.add(usuario); // Adiciona o usuário à lista
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        return usuarios; // Retorna a lista de usuários
    }

    public static void imprimirDEBUG(int x){
        System.out.println("DEBUG" + (x+1));
    }
}
