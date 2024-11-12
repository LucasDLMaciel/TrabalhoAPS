package com.gdb.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

    public String getUsuario() {
        return usuario;
    } // !!!!!!!!!!!!!!!!!!!!!!! PRIVATE

    private void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    } // !!!!!!!!!!!!!!!!!!!!!! PRIVATE

    private void setAdministradorFlag(Boolean administradorFlag) {
        this.administradorFlag = administradorFlag;
    }

    public Boolean getAdministradorFlag() {
        return administradorFlag;
    }  // !!!!!!!!!!! PRIVATE

    private void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    } // !!!!!!!!!! PRIVATE

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