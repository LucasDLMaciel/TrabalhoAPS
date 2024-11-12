package com.gdb.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}