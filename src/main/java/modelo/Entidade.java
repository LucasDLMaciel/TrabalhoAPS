<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/modelo/Entidade.java
package com.gdb.modelo;

public abstract class Entidade {

    private Integer id;

    /**
     * Construtor de uma instância que não existe no arquivo CSV.
     */
    protected Entidade() {
        this.setId(descobrirNovoId());
    }

    /**
     * Construtor de uma instância que já existe no arquivo CSV.
     *
     * @param id O id do usuário.
     */
    protected Entidade(Integer id) {
        this.setId(id);
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    /**
     * Calcula o próximo id disponível para uma nova entidade.
     *
     * @return O próximo id disponível para a nova entidade.
     */
    protected abstract Integer descobrirNovoId();

=======
=======
>>>>>>> e528347 (finalizar)
package modelo;

public abstract class Entidade {

    private Integer id;

    /**
     * Construtor de uma instância que não existe no arquivo CSV.
     */
    protected Entidade() {
        this.setId(0);
    }

    /**
     * Construtor de uma instância que já existe no arquivo CSV.
     *
     * @param id O id do usuário.
     */
    protected Entidade(Integer id) {
        this.setId(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    /**
     * Calcula o próximo id disponível para uma nova entidade.
     *
     * @return O próximo id disponível para a nova entidade.
     */
    protected Integer descobrirNovoId(){
        return this.getId() + 1;
    }

<<<<<<< HEAD
>>>>>>> e528347 (finalizar):src/main/java/modelo/Entidade.java
=======
>>>>>>> e528347 (finalizar)
}