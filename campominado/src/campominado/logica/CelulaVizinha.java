package campominado.logica;
public class CelulaVizinha extends Celula {
    private int minasProximas;

    public CelulaVizinha() {
        super();
        this.minasProximas = 0;
    }

    public int getMinasProximas() {
        return minasProximas;
    }

    public void setMinasProximas(int minasProximas) {
        this.minasProximas = minasProximas;
    }
}