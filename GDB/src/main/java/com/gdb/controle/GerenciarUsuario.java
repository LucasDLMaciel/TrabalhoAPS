/*
package com.gdb.controle;

import com.gdb.modelo.Usuario;
import java.time.LocalDate;

public class GerenciarUsuario {

    public boolean cadastrarUsuario(String usuario, String senha, Boolean administradorFlag, LocalDate dataNascimento) {
        //pedro: se usuario estiver vazio retorna falso
        if (usuario == null || usuario.isEmpty()) {
            return false;
        }

        // pedro: cadastra um novo usuario usando o metodo de Usuario.java
        Usuario novoUsuario = Usuario.cadastrarUsuario(usuario, senha, administradorFlag, dataNascimento);

        // salvar o novo user em um arquivo
        boolean usuarioSalvo = salvarUsuario(novoUsuario);

        return usuarioSalvo;
    }

    private boolean salvarUsuario(Usuario usuario) {
        // aqui deve ser implementado um codigo para salvar as info coletadas
        // ate entao só é um modelo do salvamento no arquivo csv
        return true;
    }
}
*/
