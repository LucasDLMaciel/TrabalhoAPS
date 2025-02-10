package controle;

import modelo.Entidade;
import persistencia.DAOGenero;
import persistencia.DAOJogo;
import persistencia.DAOUsuario;

import java.util.List;

abstract class Controle {
    Strategy strategy;

    public void salvar(Entidade entidade) {
        strategy.salvar(entidade);
    }

    public void deletar(Integer id) {
        strategy.deletar(id);
    }

    public void atualizar(Entidade entidade) {
        strategy.atualizar(entidade);
    }

    public Entidade buscarPorId(Integer id){
        return strategy.buscarPorId(id);
    }

    abstract  <T extends Entidade> List<T> getEntidades();

}