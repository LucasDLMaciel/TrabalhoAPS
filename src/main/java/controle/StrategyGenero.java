package controle;

import modelo.Genero;
import persistencia.DAOGenero;

public class StrategyGenero implements Strategy<Genero>{
    private DAOGenero daoGenero;

    public StrategyGenero(DAOGenero daoGenero) {
        this.daoGenero = daoGenero;
    }

    @Override
    public void salvar(Genero genero) {
        daoGenero.salvarGenero(genero);
    }

    @Override
    public void deletar(Integer id) {
        daoGenero.deletar(id);
    }

    @Override
    public void atualizar(Genero genero) {
        daoGenero.atualizar(genero);
    }

}
