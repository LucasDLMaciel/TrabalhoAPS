package com.gdb.modelo;

import java.util.ArrayList;
import java.util.List;

public class Genero extends Entidade{
    private String genero;

    public Genero(Integer id, String genero) {
        setId(id);
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
