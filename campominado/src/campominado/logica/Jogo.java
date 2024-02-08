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
                        //lógica para iniciar o jogo
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
}
