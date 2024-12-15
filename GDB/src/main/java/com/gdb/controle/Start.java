package com.gdb.controle;

import com.gdb.modelo.Usuario;
import com.gdb.visao.login_registro.TestLoginRegistro;

import java.time.LocalDate;

public class Start {
    public static void main(String[] args){
        System.out.println("Programa iniciado.");
        String nomeTeste = "TESTEMAIN";
        String senhaTeste = "1200";
        boolean admFlagTeste = true;
        LocalDate dataTeste = LocalDate.of(2025, 7, 15);

        Usuario usuario = new Usuario(nomeTeste,senhaTeste,admFlagTeste,dataTeste);

        System.out.println(usuario.getUsuario());
        System.out.println(usuario.getSenha());
        System.out.println(usuario.getAdministradorFlag());


        /* Abrir menu principal (caso exista uma tela que tenha que aparecer antes do login)
         */

        //Abrir painel de login ou registro
        System.out.println("Abrindo painel de login ou registro");
        TestLoginRegistro.main(new String[]{});




    }
}
