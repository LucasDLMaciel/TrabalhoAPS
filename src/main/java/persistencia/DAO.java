package persistencia;

import modelo.Entidade;

import java.util.List;

abstract class DAO {
    abstract void lerRegistro();
    abstract Entidade buscarPorId(Integer id);
    abstract void deletar(Integer id);
    abstract int calcularProximoId();
    abstract void atualizar(Entidade entidade);
    abstract void salvar(Entidade entidade);
    abstract <T extends Entidade> List<T> getEntidades();
}
