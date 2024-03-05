package campominado.logica;

import java.util.ArrayList;

public class Celula {
    private boolean minada;
    private boolean revelada;
    private boolean marcada;
    private boolean clicada;
    private int minasProximas;
    private ArrayList<Celula> vizinhos;

    public Celula(){
        this.clicada = false;
        this.marcada = false;
        this.revelada = false;
        this.minada = false;
        this.vizinhos = new ArrayList<>();
    }

    public boolean minar(){
        if(!this.minada){
            this.minada = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean isMinada(){
        return minada;
    }

    public int getMinasProximas() {
        return minasProximas;
    }

    public void setMinasProximas(int minasProximas) {
        this.minasProximas = minasProximas;
    }

    public boolean isRevelada() {
        return revelada;
    }

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    public boolean isClicada() {
        return clicada;
    }

    public void setClicada(boolean clicada) {
        this.clicada = clicada;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }

    public ArrayList<Celula> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(ArrayList<Celula> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public boolean marcar(){
        this.marcada = !this.marcada;
        return this.marcada;
    }
}

