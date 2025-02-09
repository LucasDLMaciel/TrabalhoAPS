package controle;

import modelo.Jogo;
import persistencia.DAOJogo;

public class StrategyJogo implements Strategy<Jogo>{
    private DAOJogo daoJogo;

    public StrategyJogo(DAOJogo daoJogo) {
        this.daoJogo = daoJogo;
    }

    @Override
    public void salvar(Jogo jogo) {
        daoJogo.salvarJogo(jogo);
    }

    @Override
    public void deletar(Integer id) {
        daoJogo.deletar(id);
    }

    @Override
    public void atualizar(Jogo jogo) {
        daoJogo.atualizar(jogo);
    }

}
