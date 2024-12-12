package com.gdb.controle;

import com.gdb.modelo.Jogo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JogoControle {
    private static final String ARQUIVO_JOGOS = "src/main/resources/data/jogos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Formata o JSON

    // Salvar um único jogo, verificando duplicidade e atribuindo um novo ID
    public void salvarJogo(Jogo jogo) {
        List<Jogo> jogos = carregarJogos();

        // Verificar se já existe um jogo com o mesmo título
        for (Jogo existente : jogos) {
            if (existente.getTitulo().equalsIgnoreCase(jogo.getTitulo())) {
                throw new IllegalArgumentException("Já existe um jogo com o título: " + jogo.getTitulo());
            }
        }

        // Calcular o próximo ID
        int proximoId = calcularProximoId(jogos);
        jogo.setId(proximoId);

        jogos.add(jogo); // Adiciona o novo jogo à lista
        salvarJogos(jogos); // Salva a lista atualizada
    }

    // Calcular o próximo ID com base no maior ID existente
    private int calcularProximoId(List<Jogo> jogos) {
        return jogos.stream()
                .mapToInt(Jogo::getId) // Extrai os IDs
                .max()                 // Encontra o maior ID
                .orElse(0) + 1;        // Incrementa o maior ID (ou retorna 1 se a lista estiver vazia)
    }

    // Salvar todos os jogos formatados em um único arquivo JSON
    private void salvarJogos(List<Jogo> jogos) {
        try (Writer writer = new FileWriter(ARQUIVO_JOGOS)) {
            gson.toJson(jogos, writer); // Serializa toda a lista de jogos com indentação
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar os jogos no arquivo.");
        }
    }

    // Carregar todos os jogos
    public List<Jogo> carregarJogos() {
        File arquivo = new File(ARQUIVO_JOGOS);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_JOGOS)) {
            Jogo[] jogosArray = gson.fromJson(reader, Jogo[].class); // Deserializa como um array de Jogos
            if (jogosArray != null) {
                return List.of(jogosArray); // Converte o array em uma lista
            }
            return new ArrayList<>(); // Retorna lista vazia se o array for nulo
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar os jogos do arquivo.");
        }
    }
}
