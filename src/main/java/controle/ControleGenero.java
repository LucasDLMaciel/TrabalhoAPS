package controle;

import modelo.Genero;

import java.util.List;

public class ControleGenero extends Controle{

    public ControleGenero() {
        super();
        this.strategy = new StrategyGenero();
    }

    public List<Genero> getEntidades(){
        return this.strategy.getEntidades();
    }
}
