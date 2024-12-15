package com.gdb.controle;

import com.gdb.modelo.Genero;
import com.gdb.modelo.Jogo;
import com.gdb.modelo.Nota;
import com.gdb.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JogoControle {
    private static final String ARQUIVO_JOGOS = "src/main/resources/data/jogos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Formata o JSON

    public void salvarJogo(String titulo, String descricao, String classificacaoEtaria, String dataLancamento, List<Genero> generos, List<Nota> notas) {
        List<Jogo> jogos = carregarJogos();
        Jogo jogo = new Jogo(titulo, dataLancamento, descricao, classificacaoEtaria, notas, generos);
        System.out.println("Jogos carregados antes de salvar: " + jogos);

        // Verificar se já existe um jogo com o mesmo título (removendo espaços extras)
        for (Jogo existente : jogos) {
            if (existente.getTitulo().trim().equalsIgnoreCase(jogo.getTitulo().trim())) {
                throw new IllegalArgumentException("Já existe um jogo com o título: " + jogo.getTitulo());
            }
        }

        // Calcular o próximo ID
        int proximoId = calcularProximoIdJogo(jogos);
        jogo.setId(proximoId);
        System.out.println("ID atribuído ao jogo: " + proximoId);

        jogos.add(jogo);

        try (Writer writer = new FileWriter(ARQUIVO_JOGOS)) {
            gson.toJson(jogos, writer); // Serializa toda a lista de jogos com indentação
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar os jogos no arquivo.");
        }
        System.out.println("Jogo salvo com sucesso: " + jogo);
    }


    // Calcular o próximo ID com base no maior ID existente
    private int calcularProximoIdJogo(List<Jogo> jogos) {
        return jogos.stream()
                .mapToInt(Jogo::getId) // Extrai os IDs
                .max()                 // Encontra o maior ID
                .orElse(3000) + 1;        // Incrementa o maior ID (ou retorna 1 se a lista estiver vazia)
    }

    private int calcularProximoIdNota(List<Jogo> jogos) {
        // Inicializa o maior ID de nota com 0
        int maiorIdNota = 0;

        // Percorre todos os jogos
        for (Jogo jogo : jogos) {
            // Para cada jogo, percorre suas notas
            for (Nota nota : jogo.getNotas()) {
                // Atualiza o maior ID de nota, caso o ID da nota atual seja maior
                maiorIdNota = Math.max(maiorIdNota, nota.getId());
            }
        }

        // Retorna o próximo ID, incrementando o maior ID encontrado
        return maiorIdNota + 1;
    }



    public void excluirJogoPorId(int id) {
        // Lê os registros do arquivo
        List<Jogo> registros = carregarJogos();

        // Filtra a lista para remover o usuário com o ID fornecido
        boolean jogoRemovido;
        jogoRemovido = registros.removeIf(jogo -> jogo.getId() == id);

        // Se nenhum jogo foi removido, lança uma exceção
        if (!jogoRemovido) {
            throw new IllegalArgumentException("Jogo não encontrado.");
        }

        // Escreve os registros atualizados no arquivo
        try (Writer writer = new FileWriter(ARQUIVO_JOGOS)) {
            gson.toJson(registros, writer); // Salva os registros atualizados no arquivo
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros após a exclusão: " + e.getMessage());
        }
    }

    public void atualizarJogo(int id, String titulo, String descricao, String classificacaoEtaria, String dataLancamento, List<Genero> generos, List<Nota> notas) {
        // Lê os registros do arquivo
        List<Jogo> jogos = carregarJogos();

        // Encontra o usuário pelo ID
        for (Jogo jogoExistente : jogos) {
            if (jogoExistente.getId() == id) {
                // Atualiza as informações do usuário
                jogoExistente.setTitulo(titulo);
                jogoExistente.setDescricao(descricao);
                jogoExistente.setClassificacaoEtaria(classificacaoEtaria);
                jogoExistente.setDataLancamento(dataLancamento);
                jogoExistente.setGeneros(generos);
                jogoExistente.setNotas(notas);

                // Escreve os jogos de volta no arquivo
                try (Writer writer = new FileWriter(ARQUIVO_JOGOS)) {
                    gson.toJson(jogos, writer); // Salva os registros atualizados no arquivo
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao salvar registros após a atualização: " + e.getMessage());
                }
                return; // Finaliza a função após a atualização
            }
        }

        // Caso o usuário não tenha sido encontrado
        throw new IllegalArgumentException("Jogo não encontrado.");
    }

    // Salvar uma única nota
    public void salvarNota(Integer id, Nota nota) {
        Jogo jogo = buscarJogoPorId(id);
        List<Nota> notas = jogo.getNotas();

        // Calcular o próximo ID
        int proximoId = calcularProximoIdNota(carregarJogos());
        nota.setId(proximoId);

        notas.add(nota); // Adiciona a nova nota à lista existente
        atualizarJogo(jogo.getId(), jogo.getTitulo(), jogo.getDescricao(), jogo.getClassificacaoEtaria(), jogo.getDataLancamento(), jogo.getGeneros(), notas);
    }

    public void editarNota(int idJogo, int idNota, Integer novaNotaTrilhaSonora, Integer novaNotaGraficos, Integer novaNotaHistoria, Integer novaNotaJogabilidade, String novoComentario) {
        Jogo jogo = buscarJogoPorId(idJogo);

        if (jogo == null) {
            throw new IllegalArgumentException("Jogo não encontrado.");
        }

        List<Nota> notas = jogo.getNotas();
        for (Nota nota : notas) {
            if (nota.getId() == idNota) {
                if (novaNotaTrilhaSonora != null) nota.setNotaTrilhaSonora(novaNotaTrilhaSonora);
                if (novaNotaGraficos != null) nota.setNotaGraficos(novaNotaGraficos);
                if (novaNotaHistoria != null) nota.setNotaHistoria(novaNotaHistoria);
                if (novaNotaJogabilidade != null) nota.setNotaJogabilidade(novaNotaJogabilidade);
                if (novoComentario != null && !novoComentario.isBlank()) nota.setComentario(novoComentario);

                // Atualiza o jogo no arquivo
                atualizarJogo(jogo.getId(), jogo.getTitulo(), jogo.getDescricao(), jogo.getClassificacaoEtaria(), jogo.getDataLancamento(), jogo.getGeneros(), notas);
                return;
            }
        }
        throw new IllegalArgumentException("Nota não encontrada.");
    }


    public Jogo buscarJogoPorId(int id) {
        // Exemplo de busca em uma lista simulada
        List<Jogo> jogos = carregarJogos();
        if(jogos == null)
            return null;

        for (Jogo jogo : jogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public Jogo buscarJogoPorTitulo(String titulo) {
        // Exemplo de busca em uma lista simulada
        List<Jogo> jogos = carregarJogos();
        if(jogos == null)
            return null;

        for (Jogo jogo : jogos) {
            if (Objects.equals(jogo.getTitulo(), titulo)) {
                return jogo;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public List<Jogo> filtrarJogos(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return carregarJogos(); // Retorna todos os jogos se o filtro estiver vazio
        }

        return carregarJogos().stream()
                .filter(jogo -> jogo.getTitulo().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());
    }


    // Carregar todos os jogos
    public List<Jogo> carregarJogos() {
        File file = new File(ARQUIVO_JOGOS);

        // Verifica se o arquivo existe e retorna uma lista vazia caso não exista
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // Lê os registros do arquivo
        try (Reader reader = new FileReader(ARQUIVO_JOGOS)) {
            Type listType = new TypeToken<List<Jogo>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler registros: " + e.getMessage());
        }
    }
}
