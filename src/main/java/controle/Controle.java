package controle;

import modelo.Entidade;
import persistencia.DAOGenero;
import persistencia.DAOJogo;
import persistencia.DAOUsuario;

public class Controle {
    public static DAOJogo daoJogo;
    public static DAOUsuario daoUsuario;
    public static DAOGenero daoGenero;

    public Controle() {
        daoUsuario = DAOUsuario.getInstance();
        daoJogo = DAOJogo.getInstance();
        daoGenero = DAOGenero.getInstance();

        daoJogo.lerRegistro();
        daoUsuario.lerRegistro();
        daoGenero.lerRegistro();
    }

    public <T extends Entidade> void salvar(String strategyTipo, T entidade) {
        Strategy<T> strategy = getStrategy(strategyTipo);
        if (strategy != null) {
            strategy.salvar(entidade);
        } else {
            throw new IllegalArgumentException("Estratégia não encontrada para o tipo: " + strategyTipo);
        }
    }

    public <T extends Entidade> void deletar(String strategyTipo, Integer id) {
        Strategy<T> strategy = getStrategy(strategyTipo);
        if (strategy != null) {
            strategy.deletar(id);
        } else {
            throw new IllegalArgumentException("Estratégia não encontrada para o tipo: " + strategyTipo);
        }
    }

    public <T extends Entidade> void atualizar(String strategyTipo, T entidade) {
        Strategy<T> strategy = getStrategy(strategyTipo);
        if (strategy != null) {
            strategy.atualizar(entidade);
        } else {
            throw new IllegalArgumentException("Estratégia não encontrada para o tipo: " + strategyTipo);
        }
    }


    private <T extends Entidade> Strategy<T> getStrategy(String strategyTipo) {
        switch (strategyTipo.toLowerCase()) {
            case "jogo":
                return (Strategy<T>) new StrategyJogo(daoJogo);
            case "usuario":
                return (Strategy<T>) new StrategyUsuario(daoUsuario);
            case "genero":
                return (Strategy<T>) new StrategyGenero(daoGenero);
            default:
                return null;
        }
    }
}