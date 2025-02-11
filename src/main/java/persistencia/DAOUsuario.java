package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import modelo.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DAOUsuario extends DAO {
    private static final String FILE_PATH = "src/main/resources/data/usuarios.json";
    private static DAOUsuario instance;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Usuario> usuarios;
    private final DAOJogo daoJogo = DAOJogo.getInstance();

    private DAOUsuario() {
        super();
    }

    public static DAOUsuario getInstance() {
        if (instance == null) {
            instance = new DAOUsuario();
        }
        return instance;
    }

    public List<Usuario> getEntidades() {
        return this.usuarios;
    }

    @Override
    public void lerRegistro() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            this.usuarios = new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Usuario>>() {
            }.getType();
            this.usuarios = gson.fromJson(reader, listType);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler registros: " + e.getMessage());
        }
    }

    @Override
    public Entidade buscarPorId(Integer id) {
        if (usuarios == null)
            return null;

        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getId(), id)) {
                return usuario;
            }
        }
        return null;
    }

    public void salvar(Entidade entidade) {
        Usuario usuario = (Usuario) entidade;
        int proximoId = 1000;
        if(usuario != null) {
            for (Usuario registro : usuarios) {
                if (registro.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {
                    throw new IllegalArgumentException("Usuário já cadastrado.");
                }
            }
            proximoId = calcularProximoId();
            usuario.setId(proximoId);
        }

        usuarios.add(usuario);

        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros: " + e.getMessage());
        }
    }

    @Override
    public int calcularProximoId() {
        return usuarios.stream()
                .mapToInt(Usuario::getId)
                .max()
                .orElse(1000) + 1;
    }

    public void atualizar(Entidade entidade) {
        Usuario usuario = (Usuario) entidade;
        for (Usuario usuarioExistente : usuarios) {
            if (Objects.equals(usuarioExistente.getId(), usuario.getId())) {
                usuarioExistente.setUsuario(usuario.getUsuario());
                usuarioExistente.setSenha(usuario.getSenha());
                usuarioExistente.setDataNascimento(usuario.getDataNascimento());
                usuarioExistente.setGenerosFavoritos(usuario.getGenerosFavoritos());
                usuarioExistente.setAdministrador(usuario.isAdministrador());

                try (Writer writer = new FileWriter(FILE_PATH)) {
                    gson.toJson(usuarios, writer);
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao salvar registros após a atualização: " + e.getMessage());
                }
                return;
            }
        }

        throw new IllegalArgumentException("Usuário não encontrado.");
    }

    @Override
    public void deletar(Integer id) {

        daoJogo.lerRegistro();
        List<Jogo> jogos = daoJogo.getEntidades();

        Usuario usuarioExcluido = null;
        for (Usuario usuario : usuarios) {
            System.out.printf("Id: %d Id2: %d %n", id, usuario.getId());
            if (Objects.equals(usuario.getId(), id)) {
                usuarioExcluido = usuario;
                System.out.printf("%s %n", usuario.getUsuario());
                break;
            }
        }

        if (usuarioExcluido == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        for (Jogo jogo : jogos) {
            List<Nota> notas = jogo.getNotas();
            Iterator<Nota> iterator = notas.iterator();

            while (iterator.hasNext()) {
                Nota nota = iterator.next();
                if (nota.getIdUsuario().equals(id)) {
                    iterator.remove();
                }
            }

            daoJogo.atualizar(jogo);
        }

        boolean usuarioRemovido = usuarios.removeIf(usuario -> Objects.equals(usuario.getId(), id));

        if (!usuarioRemovido) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar registros após a exclusão: " + e.getMessage());
        }
    }

    public Integer validarLogin(String usuario, String senha) {

        for (Usuario registro : usuarios) {
            if (registro.getUsuario().equalsIgnoreCase(usuario) && registro.getSenha().equals(senha)) {
                return registro.getId();
            }
        }
        return 0;
    }
}