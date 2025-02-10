package controle;

import modelo.Entidade;
import modelo.Jogo;
import modelo.Usuario;
import persistencia.DAOJogo;
import persistencia.DAOUsuario;

import java.util.List;

public class StrategyUsuario implements Strategy{
    private DAOUsuario daoUsuario;

    public StrategyUsuario() {
        this.daoUsuario = DAOUsuario.getInstance();
        daoUsuario.lerRegistro();
    }

    @Override
    public void salvar(Entidade entidade) {
        daoUsuario.salvar(entidade);
    }

    @Override
    public void deletar(Integer id) {
        daoUsuario.deletar(id);
    }

    @Override
    public void atualizar(Entidade entidade) {
        daoUsuario.atualizar(entidade);
    }

    @Override
    public Entidade buscarPorId(Integer id){
        return daoUsuario.buscarPorId(id);
    }

    @Override
    public List<Usuario> getEntidades(){
        return daoUsuario.getEntidades();
    }

    public Integer validarLogin(String login, String senha){
        return daoUsuario.validarLogin(login, senha);
    }

}
