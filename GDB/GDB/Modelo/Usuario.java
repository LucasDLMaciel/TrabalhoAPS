package TrabalhoAPS.Programa.Modelo;

public class Usuario {
    private Integer ID;
    private String nome;
    private String senha;
    private boolean administradorFlag;
    private String dataNascimento;

    public Usuario(){
        ID = null;
        nome = null;
        senha = null;
        administradorFlag = false;
        dataNascimento = null;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministradorFlag() {
        return administradorFlag;
    }

    public void setAdministradorFlag(boolean administradorFlag) {
        this.administradorFlag = administradorFlag;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

