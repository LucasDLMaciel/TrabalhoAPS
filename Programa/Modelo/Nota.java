package TrabalhoAPS.Programa.Modelo;

public class Nota {
    private double trilhaSonora;
    private double arte;
    private double jogabilidade;

    public Nota() {
        this.trilhaSonora = 0;
        this.arte = 0;
        this.jogabilidade = 0;
    }

    public double getArte() {
        return arte;
    }

    public double getJogabilidade() {
        return jogabilidade;
    }

    public double getTrilhaSonora() {
        return trilhaSonora;
    }

    public void setArte(double arte) {
        this.arte = arte;
    }

    public void setJogabilidade(double jogabilidade) {
        this.jogabilidade = jogabilidade;
    }

    public void setTrilhaSonora(double trilhaSonora) {
        this.trilhaSonora = trilhaSonora;
    }
}

