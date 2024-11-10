package com.gdb.modelo;

import java.time.LocalDate;

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
        // TODO Verificar se já existe usuário com esse nome de usuário
        return new Usuario(usuario, senha, administradorFlag, dataNascimento);
    }

    public void visualizarUsuario() {
        System.out.println(this.getId());
        System.out.println(this.getUsuario());
        System.out.println(this.getSenha());
        System.out.println(this.getAdministradorFlag());
        System.out.println(this.getDataNascimento());
    }

    // AO DELETAR UMA ENTRADA NO BANCO, OS IDs DEVEM SER ATUALIZADOS EM ORDEM CRESCENTE
}