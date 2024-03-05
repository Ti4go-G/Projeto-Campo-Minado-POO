package campominado.logica;

public abstract class Tabuleiro {
    protected int minas;
    protected int linhas;
    protected int colunas;
    protected Celula[][] matriz;

    public abstract void inicializarTabuleiro();

    public abstract boolean revelarCelula(int row, int col);

    public abstract void marcarCelula(int row, int col);

    public abstract boolean isJogoTerminado();
}
