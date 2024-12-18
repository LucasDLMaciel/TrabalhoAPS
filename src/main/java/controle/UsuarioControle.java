package controle;

<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/controle/UsuarioControle.java
import com.gdb.modelo.Usuario;
=======
=======
>>>>>>> e528347 (finalizar)
import modelo.Genero;
import modelo.Jogo;
import modelo.Nota;
import modelo.Usuario;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

<<<<<<< HEAD
>>>>>>> e528347 (finalizar):src/main/java/controle/UsuarioControle.java
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioControle {

    private static final String FILE_PATH = "usuarios.csv";

    public static void salvarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(usuario.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                usuarios.add(Usuario.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

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

=======
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioControle {
    private static final String FILE_PATH = "src/main/resources/data/usuarios.json";
    private final Gson gson;
    JogoControle jogoControle = new JogoControle();

    public UsuarioControle() {
        // Configura o Gson para salvar o JSON formatado
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarRegistro(String usuario, String senha, String dataNascimento, List<Genero> generos, boolean isAdmin) {
        List<Usuario> registros = lerRegistros();
        int proximoId = 1000;
        // Verifica se o usuário já existe
        if(registros != null) {
            for (Usuario registro : registros) {
                if (registro.getUsuario().equalsIgnoreCase(usuario)) {
                    throw new IllegalArgumentException("Usuário já cadastrado.");
                }
            }
            // Calcula o próximo ID
            proximoId = calcularProximoId(registros);
        }

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
                .orElse(1000) + 1;           // Incrementa o maior ID (ou retorna 1 se a lista estiver vazia)
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
        if(usuarios == null)
            return null;

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public void atualizarUsuario(int id, String usuario, String senha, String dataNascimento, List<Genero> generos, boolean isAdmin) {
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
        // Lê os registros dos usuários e jogos
        List<Usuario> registros = lerRegistros();
        List<Jogo> jogos = jogoControle.carregarJogos();

        // Encontra o usuário a ser excluído
        Usuario usuarioExcluido = null;
        for (Usuario usuario : registros) {
            if (usuario.getId() == id) {
                usuarioExcluido = usuario;
                break;
            }
        }

        // Se o usuário não for encontrado, lança uma exceção
        if (usuarioExcluido == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        // Remover as notas associadas ao usuário nos jogos
        for (Jogo jogo : jogos) {
            // Verifica as notas do jogo
            List<Nota> notas = jogo.getNotas(); // Supondo que cada jogo tem uma lista de notas
            Iterator<Nota> iterator = notas.iterator();

            while (iterator.hasNext()) {
                Nota nota = iterator.next();
                // Se a nota pertence ao usuário que está sendo excluído
                if (nota.getIdUsuario().equals(id)) {
                    // Remove a nota associada ao usuário
                    iterator.remove();
                }
            }

            // Atualiza os jogos após remoção das notas
            jogoControle.atualizarJogo(
                    jogo.getId(),
                    jogo.getTitulo(),
                    jogo.getDescricao(),
                    jogo.getClassificacaoEtaria(),
                    jogo.getDataLancamento(),
                    jogo.getGeneros(),
                    jogo.getNotas() // Lista de notas já atualizada
            );
        }

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



>>>>>>> e528347 (finalizar)
}
