package campominado.logica;
import java.util.ArrayList;


public class Celula {
    private boolean minada;
    private boolean revelada;
    public boolean marcada;
    public boolean clicada;
    ArrayList<Celula> vizinhos;
   

//inicia a celula com todos os atributos "falsos"
    public Celula(){
        this.clicada=false;
        this.marcada=false;
        this.revelada=false;
        this.minada = false;
        this.vizinhos= new ArrayList<>();
    }
        public boolean minar(){
        if(!this.minada){
            this.minada = true;
            return true;
        }else{
            return false;
        }
    }


    public boolean getMinada(){
        return minada;
    }
    //clique retorna -1 se a celula estiver minada, se nao estiver chama o metodo minasProximas
    public int clicar(){
        if(this.minada && !this.marcada){
            return -1;
        }else{
            return minasProximas();//criar o metodo minasProximas
        }

    }
    public int minasProximas(){
        int n = 0;
        for (Celula vizinho : this.vizinhos) {
            if(vizinho.minada) n++;
        }
        return n;
    }
    public boolean marcar(){
        this.marcada = !this.marcada;
        return this.marcada;
    }

    
}




