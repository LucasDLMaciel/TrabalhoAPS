package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import modelo.Jogo;
import modelo.Nota;
import modelo.Entidade;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DAOJogo extends DAO {
    private static final String ARQUIVO_JOGOS = "src/main/resources/data/jogos.json";
    private static DAOJogo instance;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Jogo> jogos;

    private DAOJogo() {
        super();
    }

    public static DAOJogo getInstance() {
        if (instance == null) {
            instance = new DAOJogo();
        }
        return instance;
    }

    @Override
    public void lerRegistro() {
        File file = new File(ARQUIVO_JOGOS);

        if (!file.exists()) {
            this.jogos = new ArrayList<>();
        }

        try (Reader reader = new FileReader(ARQUIVO_JOGOS)) {
            Type listType = new TypeToken<List<Jogo>>() {}.getType();
            this.jogos = gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler registros: " + e.getMessage());
        }
    }

    @Override
    public Jogo buscarPorId(Integer id) {
        if(jogos == null)
            return null;

        for (Jogo existente : jogos) {
            if (Objects.equals(existente.getId(), id)) {
                return existente;
            }
        }
        return null;
    }

    public Jogo buscarJogoPorTitulo(String titulo) {
        if(jogos == null)
            return null;

        for (Jogo jogo : jogos) {
            if (Objects.equals(jogo.getTitulo(), titulo)) {
                return jogo;
            }
        }
        return null; // Retorna null se não encontrar
    }

    @Override
    public void salvar(Entidade entidade) {
        Jogo jogo = (Jogo) entidade;
        for (Jogo existente : jogos) {
            if (existente.getTitulo().trim().equalsIgnoreCase(jogo.getTitulo().trim())) {
                throw new IllegalArgumentException("Já existe um jogo com o título: " + jogo.getTitulo());
            }
        }
        int proximoId = calcularProximoId();
        jogo.setId(proximoId);
        System.out.println("ID atribuído ao jogo: " + proximoId);
        jogos.add(jogo);

        for(Nota nota : jogo.getNotas()) {
            if (nota.getId() == 0){
                proximoId = calcularProximoIdNota();
                nota.setId(proximoId);
            }
        }


        try (Writer writer = new FileWriter(ARQUIVO_JOGOS)) {
            gson.toJson(jogos, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os jogos no arquivo: " + e.getMessage());
        }
    }

    public void deletar(Integer id){
        boolean jogoRemovido;
        jogoRemovido = jogos.removeIf(jogo -> Objects.equals(jogo.getId(), id));

        if (!jogoRemovido) {
            throw new IllegalArgumentException("Jogo não encontrado.");
        }

        try (Writer writer = new FileWriter(ARQUIVO_JOGOS)) {
            gson.toJson(jogos, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros após a exclusão: " + e.getMessage());
        }
    }

    public void atualizar(Entidade entidade) {
        Integer proximoId;
        Jogo jogo = (Jogo) entidade;
        for (Jogo jogoExistente : jogos) {
            if (Objects.equals(jogoExistente.getId(), jogo.getId())) {
                jogoExistente.setTitulo(jogo.getTitulo());
                jogoExistente.setDescricao(jogo.getDescricao());
                jogoExistente.setClassificacaoEtaria(jogo.getClassificacaoEtaria());
                jogoExistente.setDataLancamento(jogo.getDataLancamento());
                jogoExistente.setGeneros(jogo.getGeneros());
                jogoExistente.setNotas(jogo.getNotas());

                for(Nota nota : jogo.getNotas()) {
                    if (nota.getId() == 0){
                        proximoId = calcularProximoIdNota();
                        nota.setId(proximoId);
                    }
                }


                try (Writer writer = new FileWriter(ARQUIVO_JOGOS)) {
                    gson.toJson(jogos, writer);
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao salvar registros após a atualização: " + e.getMessage());
                }
                return;
            }
        }

        throw new IllegalArgumentException("Jogo não encontrado.");
    }

    @Override
    public int calcularProximoId() {
        return jogos.stream()
                .mapToInt(Jogo::getId)
                .max()
                .orElse(3000) + 1;
    }


    private int calcularProximoIdNota() {
        int maiorIdNota = 0;
        for (Jogo jogo : this.jogos) {
            for (Nota nota : jogo.getNotas()) {
                maiorIdNota = Math.max(maiorIdNota, nota.getId());
            }
        }
        return maiorIdNota + 1;
    }

    public List<Jogo> getEntidades() {
        return this.jogos;
    }

    public List<Jogo> filtrarJogos(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return jogos;
        }

        return jogos.stream().filter(jogo -> jogo.getTitulo().toLowerCase().contains(filtro.toLowerCase())).collect(Collectors.toList());
    }

}