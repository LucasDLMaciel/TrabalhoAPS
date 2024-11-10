package com.gdb;
import com.gdb.modelo.Usuario;
import java.time.LocalDate;

public class Programa {
    public static void main(String[] args) {
        Usuario usuario = Usuario.cadastrarUsuario("MasterTJ123", "12345", true, LocalDate.of(2020, 12, 13));
        System.out.println("Deu tudo certo!");
    }
}