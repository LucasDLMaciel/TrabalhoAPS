package com.gdb.controle;

import com.gdb.modelo.Usuario;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioControle {
    private static final String FILE_PATH = "src/main/resources/data/usuarios.json";
    private final Gson gson;

    public UsuarioControle() {
        // Configura o Gson para salvar o JSON formatado
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarRegistro(String usuario, String senha, String dataNascimento, List<String> generos, boolean isAdmin) {
        List<Usuario> registros = lerRegistros();

        // Verifica se o usuário já existe
        for (Usuario registro : registros) {
            if (registro.getUsuario().equalsIgnoreCase(usuario)) {
                throw new IllegalArgumentException("Usuário já cadastrado.");
            }
        }

        // Calcula o próximo ID
        int proximoId = calcularProximoId(registros);

        // Cria um novo registro com o ID gerado
        Usuario novoRegistro = new Usuario(proximoId, usuario, senha, dataNascimento, generos, isAdmin);
        registros.add(novoRegistro);

        // Salva os registros atualizados no arquivo
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(registros, writer); // Salva o JSON formatado
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros: " + e.getMessage());
        }
    }

    private int calcularProximoId(List<Usuario> registros) {
        return registros.stream()
                .mapToInt(Usuario::getId) // Extrai os IDs
                .max()                    // Encontra o maior ID
                .orElse(0) + 1;           // Incrementa o maior ID (ou retorna 1 se a lista estiver vazia)
    }

    public List<Usuario> lerRegistros() {
        File file = new File(FILE_PATH);

        // Verifica se o arquivo existe e retorna uma lista vazia caso não exista
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // Lê os registros do arquivo
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Usuario>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler registros: " + e.getMessage());
        }
    }

    public Integer validarLogin(String usuario, String senha) {
        List<Usuario> registros = lerRegistros();

        // Percorre os registros para verificar o login
        for (Usuario registro : registros) {
            if (registro.getUsuario().equalsIgnoreCase(usuario) && registro.getSenha().equals(senha)) {
                return registro.getId(); // Retorna verdadeiro se o login for válido
            }
        }
        return 0; // Retorna falso se nenhum registro corresponder
    }

    public Usuario buscarUsuarioPorId(int id) {
        // Exemplo de busca em uma lista simulada
        List<Usuario> usuarios = lerRegistros();

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public void atualizarUsuario(int id, String usuario, String senha, String dataNascimento, List<String> generos, boolean isAdmin) {
        // Lê os registros do arquivo
        List<Usuario> registros = lerRegistros();

        // Encontra o usuário pelo ID
        for (Usuario usuarioExistente : registros) {
            if (usuarioExistente.getId() == id) {
                // Atualiza as informações do usuário
                usuarioExistente.setUsuario(usuario);
                usuarioExistente.setSenha(senha);
                usuarioExistente.setDataNascimento(dataNascimento);
                usuarioExistente.setGenerosFavoritos(generos);
                usuarioExistente.setAdministrador(isAdmin);

                // Escreve os registros de volta no arquivo
                try (Writer writer = new FileWriter(FILE_PATH)) {
                    gson.toJson(registros, writer); // Salva os registros atualizados no arquivo
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao salvar registros após a atualização: " + e.getMessage());
                }
                return; // Finaliza a função após a atualização
            }
        }

        // Caso o usuário não tenha sido encontrado
        throw new IllegalArgumentException("Usuário não encontrado.");
    }

    public void excluirUsuarioPorId(int id) {
        // Lê os registros do arquivo
        List<Usuario> registros = lerRegistros();

        // Filtra a lista para remover o usuário com o ID fornecido
        boolean usuarioRemovido = registros.removeIf(usuario -> usuario.getId() == id);

        // Se nenhum usuário foi removido, lança uma exceção
        if (!usuarioRemovido) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        // Escreve os registros atualizados no arquivo
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(registros, writer); // Salva os registros atualizados no arquivo
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros após a exclusão: " + e.getMessage());
        }
    }

    public boolean isUsuarioExistente(String usuario, Integer idUsuario) {
        List<Usuario> registros = lerRegistros();

        // Verifica se o usuário já existe
        for (Usuario registro : registros) {
            if (registro.getUsuario().equalsIgnoreCase(usuario)) {
                return true;
            }
        }

        return false;
    }



}
