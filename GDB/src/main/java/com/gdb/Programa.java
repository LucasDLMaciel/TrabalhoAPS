package com.gdb;

import com.gdb.modelo.Arquivo;
import com.gdb.modelo.Usuario;

import java.time.LocalDate;

public class Programa {
    public static void main(String[] args) {
        Usuario usuario1 = Usuario.cadastrarUsuario("MasterTJ123", "12345", true, LocalDate.of(2000, 12, 13));
        usuario1.visualizarUsuario();
        Arquivo.salvarNovaLinhaArquivo(Arquivo.obterCaminhoArquivo(usuario1.getClass()), usuario1);
    }
}