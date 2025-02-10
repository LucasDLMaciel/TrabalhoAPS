package controle;

import modelo.Entidade;
import modelo.Jogo;

import java.util.List;

public interface Strategy{
    void salvar(Entidade entidade);
    void deletar(Integer id);
    void atualizar(Entidade entidade);
    Entidade buscarPorId(Integer id);
    <T extends Entidade> List<T> getEntidades();
}