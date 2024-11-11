package com.gdb;

import com.gdb.modelo.Usuario;

import java.time.LocalDate;
import java.util.List;

public class Programa {
    public static void main(String[] args) {
        Usuario usuario = Usuario.cadastrarUsuario("Wiltz95", "12345", true, LocalDate.of(2000, 12, 13));
        List<String[]> usuarios = Usuario.lerArquivoUsuarios();
        // TODO ALGUMA CLASSE EM USUARIO PRECISA INSTANCIAR TODAS AS STRINGS DE LER ARQUIVO
        System.out.println("TESTE_DEBUG");
    }
}