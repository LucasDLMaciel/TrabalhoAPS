package com.gdb;

import com.gdb.modelo.Usuario;
import com.gdb.modelo.Global;

import java.time.LocalDate;
import java.util.List;

public final class Programa {
    public static void main(String[] args) {
        List<Usuario> usuarios = Global.getUsuarios();  // Deve sempre ser chamado ao início do programa
        Usuario usuario = Usuario.cadastrarUsuario("DigoOP5", "12345", true, LocalDate.of(2000, 12, 13));
        try {
            Usuario usuario_logado = Usuario.fazerLogin("DigoOP1", "12345");
        } catch (Exception e) {
            System.out.println("Erro! O usuário não existe!");
            System.exit(1);
            // TODO OS ARQUIVOS DEVEM SER SALVOS APÓS QUALQUER EXCEÇÃO QUE FINALIZA O PROGRAMA
        }
        usuario.deletarUsuario();
        Global.salvarUsuarios();  // Deve sempre ser chamado ao final do programa
        System.out.println("TESTE_DEBUG");
    }
}