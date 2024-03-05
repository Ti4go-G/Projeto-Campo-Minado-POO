package campominado.logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabuleiroCampoMinado extends Tabuleiro {

    public TabuleiroCampoMinado(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        matriz = new Celula[linhas][colunas];
        inicializarTabuleiro();
    }

    @Override
    public void inicializarTabuleiro() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = new CelulaVazia();
            }
        }
        posicionarMinas();
        calcularVizinhos();
    }

    private void posicionarMinas() {
        List<Integer> positions = new ArrayList<>(); //lista dinamica
        for (int i = 0; i < linhas * colunas; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions); //embaralha as posições da lista
        for (int i = 0; i < minas; i++) {
            int position = positions.get(i);
            //converter uma posição na lista em coordenadas de linha e coluna em uma matriz 
            int row = position / colunas;
            int col = position % colunas;
            matriz[row][col] = new CelulaBomba();
        }
    }

    private void calcularVizinhos() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
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
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) { //contém as direções em que as células vizinhas podem estar, usando um foreach pra percorrer
            int newRow = row + dir[0]; //somando a coordenada atual da linha (row) com a primeira componente do vetor de direção (dir[0])
            int newCol = col + dir[1];
            //verifica se a nova posição está dentro dos limites do tabuleiro e se tem uma bomba
            if (newRow >= 0 && newRow < linhas && newCol >= 0 && newCol < colunas &&
                    matriz[newRow][newCol] instanceof CelulaBomba) {
                minasProximas++;
            }
        }
        return minasProximas;
    }

    @Override
    public boolean revelarCelula(int row, int col) {
        if (row < 0 || row >= linhas || col < 0 || col >= colunas || matriz[row][col].isClicada()) {
            return false; // Célula inválida ou já clicada
        }

        matriz[row][col].setClicada(true);

        if (matriz[row][col] instanceof CelulaBomba) {
            return true; // Game over
        }

        if (matriz[row][col] instanceof CelulaBomba && matriz[row][col].getMinasProximas() == 0) {
            revelarCelulasVizinhas(row, col);
        }

        return false;
    }

    private void revelarCelulasVizinhas(int row, int col) {
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) { //contém as direções em que as células vizinhas podem estar
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < linhas && newCol >= 0 && newCol < colunas &&
                    !matriz[newRow][newCol].isMinada() && !matriz[newRow][newCol].isClicada()) {
                matriz[newRow][newCol].setClicada(true);
                if (matriz[newRow][newCol].getMinasProximas() == 0) {
                    revelarCelulasVizinhas(newRow, newCol);
                }
            }
        }
    }

    @Override
    public void marcarCelula(int row, int col) {
        if (row >= 0 && row < linhas && col >= 0 && col < colunas) {
            matriz[row][col].setMarcada(!matriz[row][col].isMarcada());
        }
    }

    @Override
    public boolean isJogoTerminado() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (!matriz[i][j].isClicada() && !(matriz[i][j] instanceof CelulaBomba)) {
                    return false; // Ainda há células não reveladas que não são bombas
                }
            }
        }
        return true; // Todas as células não minadas foram reveladas
    }
}
