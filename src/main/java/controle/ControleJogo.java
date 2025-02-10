package controle;

import modelo.Entidade;
import modelo.Jogo;
import persistencia.DAOJogo;

import javax.swing.*;
import java.util.List;

public class ControleJogo extends Controle {
    public ControleJogo() {
        super();
        this.strategy = new StrategyJogo();
    }

    public List<Jogo> getEntidades(){
        return this.strategy.getEntidades();
    }

    public Entidade buscarPorTitulo(String titulo){
        return ((StrategyJogo) this.strategy).buscarPorTitulo(titulo);
    }


}
