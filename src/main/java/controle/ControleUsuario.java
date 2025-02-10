package controle;

import modelo.Usuario;

import java.util.List;

public class ControleUsuario extends Controle{
    public ControleUsuario(){
        super();
        this.strategy = new StrategyUsuario();
    }

    public List<Usuario> getEntidades(){
        return this.strategy.getEntidades();
    }

    public Integer validarLogin(String login, String senha){
        return ((StrategyUsuario) this.strategy).validarLogin(login, senha);
    }
}
