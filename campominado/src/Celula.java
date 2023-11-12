import java.util.ArrayList;

public class Celula {
    private boolean minada;
    private boolean revelada;
    private boolean marcada;
    private boolean clicada;
   

//inicia a celula com todos os atributos "falsos"
    public Celula(){
        this.clicada=false;
        this.marcada=false;
        this.revelada=false;
    }

    public boolean minar(){
        if(!this.minada){
            this.minada = true;
            return true;
        }else{
            return false;
        }
    }
    //clique retorna -1 se a celula estiver minada, se nao estiver chama o metodo minasProximas
    public int clicar(){
        if(this.minada){
            return -1;
        }else{
            return minasProximas();//criar o metodo minasProximas
        }

    }

    
}