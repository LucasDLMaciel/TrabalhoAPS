package controle;

import modelo.Jogo;
import modelo.Usuario;
import persistencia.DAOJogo;
import persistencia.DAOUsuario;

public class StrategyUsuario implements Strategy<Usuario>{
    private DAOUsuario daoUsuario;

    public StrategyUsuario(DAOUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    @Override
    public void salvar(Usuario usuario) {
        daoUsuario.salvarUsuario(usuario);
    }

    @Override
    public void deletar(Integer id) {
        daoUsuario.deletar(id);
    }

    @Override
    public void atualizar(Usuario usuario) {
        daoUsuario.atualizar(usuario);
    }

}
