// TODO FAZER O LOGIN DO USUÁRIO (NA CAMADA DE CONTROLE)
// TODO FAZER O READ DO USUÁRIO (NA CAMADA DE CONTROLE)

/*
package com.gdb.controle;

import com.gdb.modelo.Usuario;

public class ControladorDeLogin {

    public boolean autenticarUsuario(String usuario, String senha) {
        // busca o user atraves do nome
        Usuario usuarioEncontrado = buscarUsuarioPorUsername(usuario);

        return usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(senha);
        //pedro: irá retornar true se o usuário existir e a senha dele estiver correta,
        // ou falso, caso o usuario nem exista, ou a senha do usuario existente estiver incorreta
    }

    private Usuario buscarUsuarioPorUsername(String usuario) {
        // pedro: em questão precisamos vincular o CSV aqui, para que o controlador de login possa procurar os usuarios.

        // Simulação: Se o nome de usuário for "admin", retorna um usuário simulado
        if (usuario.equals("admin")) {
            return Usuario.cadastrarUsuario("admin", "admin123", true, null);  // Retorna um usuário com senha "admin123"
        }

        return null;  // user nao encontrado
    }
}
*/