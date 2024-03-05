package campominado.logica;

public class CelulaBomba extends Celula {
    private boolean minada;

    public CelulaBomba() {
        super();
        this.minada = true;
    }

    public boolean isMinada() {
        return minada;
    }
}