package com.gdb.modelo;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Usuario extends Entidade {

    private String usuario;
    private String senha;
    private Boolean administradorFlag;
    private LocalDate dataNascimento;

    private Usuario(String usuario, String senha, Boolean administradorFlag, LocalDate dataNascimento) {
        super();
        this.setUsuario(usuario);
        this.setSenha(senha);
        this.setAdministradorFlag(administradorFlag);
        this.setDataNascimento(dataNascimento);
    }

    private void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private String getUsuario() {
        return usuario;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

    private String getSenha() {
        return senha;
    }

    private void setAdministradorFlag(Boolean administradorFlag) {
        this.administradorFlag = administradorFlag;
    }

    private Boolean getAdministradorFlag() {
        return administradorFlag;
    }

    private void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    private LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public static Usuario cadastrarUsuario(String usuario, String senha, Boolean administradorFlag, LocalDate dataNascimento) {
        Path arquivoPath = Arquivo.obterCaminhoArquivo(Usuario.class);
        // Verifica se o nome de usuário já existe no arquivo
        if (Arquivo.valorExisteArquivo(arquivoPath, "usuario", usuario)) {
            System.out.println("Erro! O usuário já existe!");
            System.exit(1);
        }
        // Cria e salva o usuário no arquivo
        Usuario usuarioInstancia = new Usuario(usuario, senha, administradorFlag, dataNascimento);
        Arquivo.escreverArquivo(arquivoPath, usuarioInstancia);
        return usuarioInstancia;
    }

    public static List<String[]> lerArquivoUsuarios() {
        Path arquivoPath = Arquivo.obterCaminhoArquivo(Usuario.class);
        return Arquivo.lerArquivo(arquivoPath);
    }

    public void deletarUsuario() {
        Path arquivoPath = Arquivo.obterCaminhoArquivo(Usuario.class);
        Arquivo.deletarArquivo(arquivoPath, super.getId());
    }

    // TODO LOGIN DO USUÁRIO
    // TODO FAZER O UPDATE DO USUÁRIO
    // AO DELETAR UMA ENTRADA NO BANCO, OS IDs DEVEM SER ATUALIZADOS EM ORDEM CRESCENTE
}