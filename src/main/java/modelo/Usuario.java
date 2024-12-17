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