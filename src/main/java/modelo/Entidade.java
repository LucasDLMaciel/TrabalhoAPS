package modelo;

public abstract class Entidade {

    private Integer id;

    protected Entidade() {
        this.setId(0);
    }

    protected Entidade(Integer id) {
        this.setId(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

}