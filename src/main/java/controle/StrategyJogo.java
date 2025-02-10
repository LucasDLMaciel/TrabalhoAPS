package controle;

import modelo.Entidade;
import modelo.Jogo;
import persistencia.DAOJogo;
import java.util.List;

public class StrategyJogo implements Strategy{
    private DAOJogo daoJogo;

    public StrategyJogo() {
        this.daoJogo = DAOJogo.getInstance();
        daoJogo.lerRegistro();
    }

    @Override
    public void salvar(Entidade entidade) {
        daoJogo.salvar(entidade);
    }

    @Override
    public void deletar(Integer id) {
        daoJogo.deletar(id);
    }

    @Override
    public void atualizar(Entidade entidade) {
        daoJogo.atualizar(entidade);
    }

    @Override
    public Entidade buscarPorId(Integer id) {
        return daoJogo.buscarPorId(id);
    }

    public Entidade buscarPorTitulo(String titulo) {
        return daoJogo.buscarJogoPorTitulo(titulo);
    }

    @Override
    public List<Jogo> getEntidades(){
        return daoJogo.getEntidades();
    }
}
