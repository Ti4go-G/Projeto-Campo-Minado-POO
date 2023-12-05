package campominado.logica;
public class Tabuleiro {
    public int minas;
    public int cols;
    public int lines;

    public Celula[][] matriz;

    public Tabuleiro(){
        matriz = new Celula[lines][cols];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                matriz[i][j] = new Celula();
            }
        }
    
    
}
}
