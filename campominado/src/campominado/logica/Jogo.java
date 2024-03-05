package campominado.logica;

import java.util.Scanner;

public class Jogo{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Menu Campo Minado ===");
            System.out.println("1. Iniciar jogo");
            System.out.println("2. Ver instruções");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("Iniciando jogo...");
                        jogarCampoMinado();
                        break;
                    case 2:
                        System.out.println("Instruções do jogo...");
                        // instruções do jogo
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
                opcao = 0; // Atribuir 0 para continuar no loop do menu
            }
        } while (opcao != 3);

        scanner.close();
    }

    public static void jogarCampoMinado() {
        int linhas = 10;
        int colunas = 10;
        int minas = 10;
        Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, minas);

        Scanner scanner = new Scanner(System.in);

        while (!tabuleiro.isJogoTerminado()) {
            System.out.println("=== Campo Minado ===");
            System.out.println(tabuleiro); // Mostra o tabuleiro atual

            System.out.print("Digite a linha: ");
            int linha = scanner.nextInt();
            System.out.print("Digite a coluna: ");
            int coluna = scanner.nextInt();

            if (linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
                System.out.println("Posição inválida. Tente novamente.");
                continue;
            }

            System.out.println("1. Revelar célula");
            System.out.println("2. Marcar/Desmarcar célula");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                if (tabuleiro.revelarCelula(linha, coluna)) {
                    System.out.println("Você encontrou uma bomba! Fim de jogo!");
                    break;
                }
            } else if (opcao == 2) {
                tabuleiro.marcarCelula(linha, coluna);
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }
        }

        if (tabuleiro.isJogoTerminado()) {
            System.out.println("Parabéns! Você ganhou o jogo!");
        }

        scanner.close();
    }
}
