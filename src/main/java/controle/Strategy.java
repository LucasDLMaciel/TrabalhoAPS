package controle;

import modelo.Jogo;

public interface Strategy<T> {
    void salvar(T entidade);
    void deletar(Integer id);
    void atualizar(T entidade);
}