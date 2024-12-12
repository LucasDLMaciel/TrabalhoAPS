package com.gdb.controle;

import com.gdb.modelo.Jogo;
import com.gdb.modelo.Nota;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NotaControle {
    private static final String ARQUIVO_NOTAS = "src/main/resources/data/notas.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Salvar uma única nota
    public void salvarNota(Nota nota) {
        List<Nota> notas = carregarNotas();

        // Calcular o próximo ID
        int proximoId = calcularProximoId(notas);
        nota.setId(proximoId);

        notas.add(nota); // Adiciona a nova nota à lista existente
        salvarNotas(notas); // Salva a lista atualizada
    }

    // Salvar todas as notas (interno, usado para persistência)
    private void salvarNotas(List<Nota> notas) {
        try (Writer writer = new FileWriter(ARQUIVO_NOTAS)) {
            gson.toJson(notas, writer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar as notas no arquivo.");
        }
    }

    // Carregar todas as notas
    public List<Nota> carregarNotas() {
        File arquivo = new File(ARQUIVO_NOTAS);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_NOTAS)) {
            Type listType = new TypeToken<List<Nota>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar as notas do arquivo.");
        }
    }

    // Calcular o próximo ID com base no maior ID existente
    private int calcularProximoId(List<Nota> notas) {
        return notas.stream()
                .mapToInt(Nota::getId) // Extrai os IDs
                .max()                 // Encontra o maior ID
                .orElse(0) + 1;        // Incrementa o maior ID (ou retorna 1 se a lista estiver vazia)
    }
}
