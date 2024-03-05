package campominado.logica;

import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {
        int opcao = 0; // Inicializa a variável opcao com um valor padrão
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.println("=== Menu Campo Minado ===");
                System.out.println("1. Iniciar jogo (Fácil)");
                System.out.println("2. Iniciar jogo (Médio)");
                System.out.println("3. Iniciar jogo (Difícil)");
                System.out.println("4. Ver instruções");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = scanner.nextInt();
                    switch (opcao) {
                        case 1:
                            System.out.println("Iniciando jogo fácil...");
                            jogarCampoMinado(10, 10, 10);
                            break;
                        case 2:
                            System.out.println("Iniciando jogo médio...");
                            jogarCampoMinado(16, 16, 40);
                            break;
                        case 3:
                            System.out.println("Iniciando jogo difícil...");
                            jogarCampoMinado(20, 20, 80);
                            break;
                        case 4:
                            System.out.println("Instruções do jogo...");
                            // instruções do jogo
                            break;
                        case 5:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                            break;
                    }
                } catch (ValorAtributoInvalidoException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, insira um número válido.");
                    scanner.nextLine(); // Limpar o buffer do scanner
                    opcao = 0; // Atribuir 0 para continuar no loop do menu
                }
            } while (opcao != 5);
        }
    }

    public static void jogarCampoMinado(int linhas, int colunas, int minas) throws ValorAtributoInvalidoException {
        TabuleiroCampoMinado tabuleiro = new TabuleiroCampoMinado(linhas, colunas, minas);

        try (Scanner scanner = new Scanner(System.in)) {
            while (!tabuleiro.isJogoTerminado()) {
                System.out.println("=== Campo Minado ===");
                System.out.println(tabuleiro); // Mostra o tabuleiro atual

                System.out.print("Digite a linha: ");
                int linha = scanner.nextInt();
                if (linha < 0 || linha >= linhas) {
                    throw new ValorAtributoInvalidoException("Linha inválida. Fora dos limites do tabuleiro.");
                }

                System.out.print("Digite a coluna: ");
                int coluna = scanner.nextInt();
                if (coluna < 0 || coluna >= colunas) {
                    throw new ValorAtributoInvalidoException("Coluna inválida. Fora dos limites do tabuleiro.");
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
        }
    }
}
