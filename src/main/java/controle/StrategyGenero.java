package controle;

import modelo.Entidade;
import modelo.Genero;
import persistencia.DAOGenero;

import java.util.List;

public class StrategyGenero implements Strategy{
    private DAOGenero daoGenero;

    public StrategyGenero() {
        this.daoGenero = DAOGenero.getInstance();
        daoGenero.lerRegistro();
    }

    @Override
    public void salvar(Entidade entidade) {
        daoGenero.salvar(entidade);
    }

    @Override
    public void deletar(Integer id) {
        daoGenero.deletar(id);
    }

    @Override
    public void atualizar(Entidade entidade) {
        daoGenero.atualizar(entidade);
    }

    @Override
    public Entidade buscarPorId(Integer id) {
        return daoGenero.buscarPorId(id);
    }

    @Override
    public List<Genero> getEntidades(){
        return daoGenero.getEntidades();
    }

}
