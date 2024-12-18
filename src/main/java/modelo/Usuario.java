<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/modelo/Usuario.java
package com.gdb.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public final class Usuario extends Entidade {

    private String usuario;
    private String senha;
    private Boolean administradorFlag;
    private LocalDate dataNascimento;

    /**
     * Construtor de uma instância que não existe no arquivo CSV.
     */
    private Usuario(String usuario, String senha, Boolean administradorFlag, LocalDate dataNascimento) {
        super();
        this.setUsuario(usuario);
        this.setSenha(senha);
        this.setAdministradorFlag(administradorFlag);
        this.setDataNascimento(dataNascimento);
    }

    /**
     * Construtor de uma instância que já existe no arquivo CSV.
     */
    Usuario(Integer id, String usuario, String senha, Boolean administradorFlag, LocalDate dataNascimento) {
        super(id);
        this.setUsuario(usuario);
        this.setSenha(senha);
        this.setAdministradorFlag(administradorFlag);
        this.setDataNascimento(dataNascimento);
    }

    private void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return this.senha;
    }

    private void setAdministradorFlag(Boolean administradorFlag) {
        this.administradorFlag = administradorFlag;
    }

    public Boolean getAdministradorFlag() {
        return this.administradorFlag;
    }

    private void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    /**
     * Cria um novo usuário.
     */
    public static Usuario cadastrarUsuario(String usuario, String senha, Boolean administradorFlag, LocalDate dataNascimento) {
        List<Usuario> usuarios = Global.getUsuarios();
        // Verifica se o usuário já existe na lista de usuários
        // TODO ENTENDER COMO SE CONECTA COM O FRONT E TRATAR CORRETAMENTE
        if (Usuario.usuarioExiste(usuarios, usuario)) {
            System.out.println("Erro! O usuário já existe!");
            System.exit(1);
        }
        // Cria o novo usuário
        Usuario usuarioInstancia = new Usuario(usuario, senha, administradorFlag, dataNascimento);
        // Atualiza a lista de usuários
        usuarios.add(usuarioInstancia);
        return usuarioInstancia;
    }

    /**
     * Instancia os usuários que já existem no arquivo CSV.
     */
    static List<Usuario> instanciarUsuarios(List<String[]> usuariosListaString) {
        List<Usuario> usuarios = new ArrayList<>();
        for (String[] usuario : usuariosListaString) {
            usuario = usuario[0].split(";");
            usuarios.add(new Usuario(
                    Integer.parseInt(usuario[0]),
                    usuario[1],
                    usuario[2],
                    Boolean.parseBoolean(usuario[3]),
                    LocalDate.parse(usuario[4])
            ));
        }
        return usuarios;
    }

    /**
     * Remove um usuário de {@link Global#usuarios}, atualiza seus índices e
     * cria um novo arquivo CSV com os elementos atuais de {@link Global#usuarios}.
     */
    public void deletarUsuario() {
        // Retira o usuário indesejado da lista de usuários e atualiza os ids seguintes
        List<Usuario> usuarios = Global.getUsuarios();
        Integer id = this.getId();
        usuarios.remove(id - 1);
        for (int i = id - 1; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            usuario.setId(usuario.getId() - 1);
        }
    }

    /**
     * Verifica se um usuário já existe na lista de usuários.
     *
     * @param usuarios      A lista de usuários.
     * @param usuarioString O usuário que será verificado.
     * @return Um Boolean que indica se o valor existe na lista ou não.
     */
    private static Boolean usuarioExiste(List<Usuario> usuarios, String usuarioString) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(usuarioString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Realiza o login do usuário.
     *
     * @param usuarioString O usuário.
     * @param senha         A senha.
     * @return O usuário logado.
     */
    public static Usuario fazerLogin(String usuarioString, String senha) throws Exception {
        List<Usuario> usuarios = Global.getUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(usuarioString) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        throw new Exception("Erro! O usuário não existe!");
    }

    /**
     * Calcula o próximo id disponível para um novo usuário.
     *
     * @return O próximo id disponível para o novo usuário.
     */
    @Override
    protected Integer descobrirNovoId() {
        return Global.getUsuarios().size() + 1;
    }

    // TODO FAZER O UPDATE DO USUÁRIO (CASO O MATHEUS PEÇA)
    public static Usuario fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento = LocalDate.parse(values[3], formatter);
        return new Usuario(values[0], values[1], Boolean.parseBoolean(values[2]), dataNascimento);
    }

    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.join(",",
                usuario,
                senha,
                administradorFlag.toString(),
                dataNascimento.format(formatter)
        );
    }

    // AO DELETAR UMA ENTRADA NO BANCO, OS IDs DEVEM SER ATUALIZADOS EM ORDEM CRESCENTE
}
=======
=======
>>>>>>> e528347 (finalizar)
package modelo;

import java.util.List;

public class Usuario extends Entidade {
    private String usuario;
    private String senha;
    private String dataNascimento;
    private List<Genero> generosFavoritos;
    private boolean administrador;

    public Usuario(Integer id, String usuario, String senha, String dataNascimento, List<Genero> generosFavoritos, boolean administrador) {
        setId(id);
        this.usuario = usuario;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.generosFavoritos = generosFavoritos;
        this.administrador = administrador;
    }

    public void adicionarGenero(Genero genero) {
        generosFavoritos.add(genero);
    }

    public void removerGenero(Integer id) {
        for(Genero genero : generosFavoritos) {
            if(genero.getId() == id) {
                generosFavoritos.remove(genero);
            }
        }
    }

    // Getters e Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Genero> getGenerosFavoritos() {
        return generosFavoritos;
    }

    public void setGenerosFavoritos(List<Genero> generosFavoritos) {
        this.generosFavoritos = generosFavoritos;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
}
<<<<<<< HEAD
>>>>>>> e528347 (finalizar):src/main/java/modelo/Usuario.java
=======
>>>>>>> e528347 (finalizar)
