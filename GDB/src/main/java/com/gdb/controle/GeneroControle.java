package com.gdb.controle;

import com.gdb.modelo.Genero;
import com.gdb.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneroControle {
    private static final String FILE_PATH = "src/main/resources/data/generos.json";
    private final Gson gson;
    private UsuarioControle usuarioControle = new UsuarioControle();

    public GeneroControle() {
        // Configura o Gson para salvar o JSON formatado
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarGenero(String genero) {
        List<Genero> generos = lerRegistros();

        // Verifica se o usuário já existe
        for (Genero registro : generos) {
            if (registro.getGenero().equalsIgnoreCase(genero)) {
                return;
            }
        }

        // Calcula o próximo ID
        int proximoId = calcularProximoId(generos);

        // Cria um novo registro com o ID gerado
        Genero novoRegistro = new Genero(proximoId, genero);
        generos.add(novoRegistro);

        // Salva os generos atualizados no arquivo
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(generos, writer); // Salva o JSON formatado
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar generos: " + e.getMessage());
        }
    }

    private int calcularProximoId(List<Genero> registros) {
        return registros.stream()
                .mapToInt(Genero::getId) // Extrai os IDs
                .max()                    // Encontra o maior ID
                .orElse(0) + 1;           // Incrementa o maior ID (ou retorna 1 se a lista estiver vazia)
    }

    public List<Genero> lerRegistros() {
        File file = new File(FILE_PATH);

        // Verifica se o arquivo existe e retorna uma lista vazia caso não exista
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // Lê os registros do arquivo
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Genero>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler registros: " + e.getMessage());
        }
    }

    public List<String> getGeneros(){
        List<Genero> generos = lerRegistros();
        List<String> generoList = new ArrayList<>();
        for(Genero registro : generos){
            generoList.add(registro.getGenero());
        }
        return generoList;
    }

    public Genero buscarGeneroPorId(int id) {
        // Exemplo de busca em uma lista simulada
        List<Genero> generos = lerRegistros();

        for (Genero genero : generos) {
            if (genero.getId() == id) {
                return genero;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public void atualizarGenero(int id, String novoGenero) {
        // Lê os registros do arquivo
        List<Genero> registros = lerRegistros();
        List<Usuario> usuarios = usuarioControle.lerRegistros();

        // Busca o gênero a ser editado
        Genero generoEditado = buscarGeneroPorId(id);
        if (generoEditado == null) {
            throw new IllegalArgumentException("Gênero não encontrado.");
        }

        // Atualiza os gêneros favoritos dos usuários
        for (Usuario usuario : usuarios) {
            boolean atualizado = false;

            // Usando um iterador para evitar problemas ao modificar a lista
            List<String> favoritos = usuario.getGenerosFavoritos();
            for (int i = 0; i < favoritos.size(); i++) {
                if (favoritos.get(i).equals(generoEditado.getGenero())) {
                    favoritos.set(i, novoGenero); // Atualiza o gênero
                    atualizado = true;
                }
            }

            // Persistir alterações apenas se o usuário teve seu gênero atualizado
            if (atualizado) {
                usuarioControle.atualizarUsuario(
                        usuario.getId(),
                        usuario.getUsuario(),
                        usuario.getSenha(),
                        usuario.getDataNascimento(),
                        favoritos,
                        usuario.isAdministrador()
                );
            }
        }

        // Atualiza o gênero na lista principal de registros
        boolean generoAtualizado = false;
        for (Genero generoExistente : registros) {
            if (generoExistente.getId().equals(id)) {
                generoExistente.setGenero(novoGenero);
                generoAtualizado = true;
                break;
            }
        }

        // Se o gênero não foi encontrado na lista, lança uma exceção
        if (!generoAtualizado) {
            throw new IllegalArgumentException("Gênero não encontrado.");
        }

        // Escreve os registros de volta no arquivo
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(registros, writer); // Salva os registros atualizados no arquivo
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros após a atualização: " + e.getMessage());
        }
    }


    public void excluirGeneroPorId(int id) {
        // Lê os gêneros do arquivo
        List<Genero> generos = lerRegistros();
        List<Usuario> usuarios = usuarioControle.lerRegistros();

        // Busca o gênero a ser apagado
        Genero generoApagado = buscarGeneroPorId(id);
        if (generoApagado == null) {
            throw new IllegalArgumentException("Gênero não encontrado.");
        }

        // Atualiza os usuários para remover o gênero apagado
        for (Usuario usuario : usuarios) {
            Iterator<String> iterator = usuario.getGenerosFavoritos().iterator();
            while (iterator.hasNext()) {
                String generosS = iterator.next();
                if (generosS.equals(generoApagado.getGenero())) {
                    iterator.remove(); // Remova o gênero diretamente
                }
            }
            // Atualiza o usuário após a modificação
            usuarioControle.atualizarUsuario(
                    usuario.getId(),
                    usuario.getUsuario(),
                    usuario.getSenha(),
                    usuario.getDataNascimento(),
                    usuario.getGenerosFavoritos(),
                    usuario.isAdministrador()
            );
        }

        // Remove o gênero da lista principal
        boolean generoRemovido = generos.removeIf(genero -> genero.getId().equals(id));

        // Se nenhum gênero foi removido, lança uma exceção
        if (!generoRemovido) {
            throw new IllegalArgumentException("Gênero não encontrado.");
        }

        // Escreve os gêneros atualizados no arquivo
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(generos, writer); // Salva os gêneros atualizados no arquivo
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar gêneros após a exclusão: " + e.getMessage());
        }
    }


    public boolean isGeneroExistente(String genero) {
        List<Genero> registros = lerRegistros();

        // Verifica se o usuário já existe
        for (Genero registro : registros) {
            if (registro.getGenero().equalsIgnoreCase(genero)) {
                return true;
            }
        }

        return false;
    }

}
