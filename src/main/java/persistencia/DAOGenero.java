package persistencia;

import modelo.Entidade;
import modelo.Genero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import modelo.Jogo;
import modelo.Usuario;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DAOGenero extends DAO{
    private static final String FILE_PATH = "src/main/resources/data/generos.json";
    private static DAOGenero instance;
    private static DAOJogo daoJogo = DAOJogo.getInstance();
    private static DAOUsuario daoUsuario = DAOUsuario.getInstance();
    private List<Genero> generos;
    private final Gson gson;

    private DAOGenero() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static DAOGenero getInstance() {
        if (instance == null) {
            instance = new DAOGenero();
        }
        return instance;
    }

    @Override
    public Entidade buscarPorId(Integer id) {
        for (Genero genero : generos) {
            if (Objects.equals(genero.getId(), id)) {
                return genero;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public List<Genero> getEntidades() {
        return generos;
    }

    @Override
    public int calcularProximoId() {
        return this.generos.stream()
                .mapToInt(Genero::getId) // Extrai os IDs
                .max()                    // Encontra o maior ID
                .orElse(2000) + 1;           // Incrementa o maior ID (ou retorna 1 se a lista estiver vazia)
    }

    @Override
    public void salvar(Entidade entidade) {
        Genero genero = (Genero) entidade;
        for (Genero registro : generos) {
            if (registro.getGenero().equalsIgnoreCase(genero.getGenero())) {
                return;
            }
        }

        // Calcula o próximo ID
        int proximoId = calcularProximoId();

        // Cria um novo registro com o ID gerado
        Genero novoRegistro = new Genero(proximoId, genero.getGenero());
        generos.add(novoRegistro);

        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(generos, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar gêneros: " + e.getMessage());
        }
    }

    @Override
    public void lerRegistro() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            this.generos = new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Genero>>() {}.getType();
            this.generos = gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler gêneros: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Entidade entidade) {
        Genero genero = (Genero) entidade;
        // Lê os registros do arquivo
        List<Genero> generosUsuario;
        List<Genero> generosJogos;
        boolean atualizado = true;

        // Busca o gênero a ser editado
        Genero generoEditado = (Genero) buscarPorId(genero.getId());
        if (generoEditado == null) {
            atualizado = false;
        }
        assert generoEditado != null;
        generoEditado.setGenero(genero.getGenero());

        // Atualiza os gêneros favoritos dos usuários
        for (Usuario usuario : daoUsuario.getEntidades()) {
            generosUsuario = usuario.getGenerosFavoritos();
            for (Genero generoExistente : generosUsuario) {
                if (generoExistente.getId().equals(genero.getId())) {
                    generoExistente.setGenero(genero.getGenero());
                    atualizado = true;
                    break;
                }
            }

            // Persistir alterações apenas se o usuário teve seu gênero atualizado
            if (atualizado) {
                daoUsuario.atualizar(usuario);
            }
        }

        for (Jogo jogo : daoJogo.getEntidades()) {
            generosJogos = jogo.getGeneros();
            for (Genero generoExistente : generosJogos) {
                if (generoExistente.getId().equals(genero.getId())) {
                    generoExistente.setGenero(genero.getGenero());
                    atualizado = true;
                    break;
                }
            }

            // Persistir alterações apenas se o usuário teve seu gênero atualizado
            if (atualizado) {
                daoJogo.atualizar(jogo);
            }
        }

        // Atualiza o gênero na lista principal de registros
        boolean generoAtualizado = false;
        for (Genero generoExistente : generos) {
            if (generoExistente.getId().equals(genero.getId())) {
                generoExistente.setGenero(genero.getGenero());
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
            gson.toJson(generos, writer); // Salva os registros atualizados no arquivo
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros após a atualização: " + e.getMessage());
        }
    }

    @Override
    public void deletar(Integer id) {
        // Busca o gênero a ser apagado
        Genero generoApagado = (Genero) buscarPorId(id);
        if (generoApagado == null) {
            throw new IllegalArgumentException("Gênero não encontrado.");
        }

        // Atualiza os usuários para remover o gênero apagado
        for (Usuario usuario : daoUsuario.getEntidades()) {
            Iterator<Genero> iterator = usuario.getGenerosFavoritos().iterator();
            while (iterator.hasNext()) {
                Genero generoS = iterator.next();
                if (Objects.equals(generoS.getId(), id)) { // Compara pelo ID, para garantir que estamos removendo o gênero correto
                    iterator.remove();
                }
            }
            // Atualiza o usuário após a modificação
            daoUsuario.atualizar(usuario);
        }
        // Atualiza os jogos para remover o gênero apagado
        for (Jogo jogo : daoJogo.getEntidades()) {
            Iterator<Genero> iterator = jogo.getGeneros().iterator();
            while (iterator.hasNext()) {
                Genero generoS = iterator.next();
                if (Objects.equals(generoS.getId(), id)) {
                    iterator.remove();
                }
            }
            // Atualiza o jogo após a modificação
            daoJogo.atualizar(jogo);
        }

        boolean generoRemovido = generos.removeIf(genero -> Objects.equals(genero.getId(), id));

        if (!generoRemovido) {
            throw new IllegalArgumentException("Gênero não encontrado.");
        }

        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(generos, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar gêneros após a exclusão: " + e.getMessage());
        }
    }
}