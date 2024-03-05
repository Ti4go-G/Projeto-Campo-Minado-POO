package campominado.logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabuleiro {
    private int minas;
    private int cols;
    private int lines;
    private Celula[][] matriz;

    public Tabuleiro(int lines, int cols, int minas) {
        this.lines = lines;
        this.cols = cols;
        this.minas = minas;
        matriz = new Celula[lines][cols];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                matriz[i][j] = new CelulaVazia(); // Inicializa todas as células como vazias
            }
        }
        posicionarMinas();
        calcularVizinhos();
    }

    private void posicionarMinas() {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < lines * cols; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions); //coloca bombas em posições aleatórias
        for (int i = 0; i < minas; i++) {
            int position = positions.get(i);
            int row = position / cols;
            int col = position % cols;
            matriz[row][col] = new CelulaBomba(); // Substitui a célula vazia por uma bomba
        }
    }

    private void calcularVizinhos() {
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                if (!(matriz[i][j] instanceof CelulaBomba)) {
                    int minasProximas = calcularMinasProximas(i, j);
                    ((CelulaVizinha) matriz[i][j]).setMinasProximas(minasProximas);
                }
            }
        }
    }

    private int calcularMinasProximas(int row, int col) {
        int minasProximas = 0;
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1}, //Método para calcular o número de minas vizinhas para uma célula específica
                {0, -1},           {0, 1}, 
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0]; //Define as direções em que as células vizinhas podem estar.
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < lines && newCol >= 0 && newCol < cols && //Calcula a posição da célula vizinha.
                    matriz[newRow][newCol] instanceof CelulaBomba) {
                minasProximas++;
            }
        }
        return minasProximas;
    }

    public boolean revelarCelula(int row, int col) {
        if (row < 0 || row >= lines || col < 0 || col >= cols || matriz[row][col].isClicada()) {
            return false; // Célula inválida ou já clicada
        }

        matriz[row][col].setClicada(true);

        if (matriz[row][col] instanceof CelulaBomba) {
            return true; // Game over
        }

        if (matriz[row][col] instanceof CelulaVazia && matriz[row][col].getMinasProximas() == 0) {
            revelarCelulasVizinhas(row, col);
        }

        return false;
    }

    private void revelarCelulasVizinhas(int row, int col) {
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1}, //Método para revelar vizinhas para uma célula vazia
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) { //define as direções em que as células vizinhas podem estar
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < lines && newCol >= 0 && newCol < cols &&
                    !matriz[newRow][newCol].isMinada() && !matriz[newRow][newCol].isClicada()) {
                matriz[newRow][newCol].setClicada(true);
                if (matriz[newRow][newCol].getMinasProximas() == 0) {
                    revelarCelulasVizinhas(newRow, newCol);
                }
            }
        }
    }

    public void marcarCelula(int row, int col) {
        if (row >= 0 && row < lines && col >= 0 && col < cols) {
            matriz[row][col].setMarcada(!matriz[row][col].isMarcada()); // marca se a celula nao estiver marcada ainda
        }
    }

    public boolean isJogoTerminado() {
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < cols; j++) {
                if (!matriz[i][j].isClicada() && !(matriz[i][j] instanceof CelulaBomba)) {
                    return false; // Ainda há células não reveladas que não são bombas
                }
            }
        }
        return true; // Todas as células não minadas foram reveladas
    }
}
