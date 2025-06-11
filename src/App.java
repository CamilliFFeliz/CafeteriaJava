import view.ClienteView;
import view.MenuView; // Importe a nova interface
// Importe as outras views quando forem criadas
// import view.ProdutoView;
// import view.PedidoView;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Em vez de declarar como ClienteView, podemos usar a interface!
        MenuView menuAtual;
        int opcao;

        do {
            System.out.println("\n==== MENU PRINCIPAL DA CAFETERIA ====");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Produtos"); // Futura implementação
            System.out.println("3. Gerenciar Pedidos");  // Futura implementação
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1:
                    // A variável do tipo da INTERFACE recebe um objeto da CLASSE
                    menuAtual = new ClienteView();
                    menuAtual.exibirMenu(); // Chamada padronizada!
                    break;
                case 2:
                    // Quando ProdutoView for criada, o padrão será o mesmo:
                    // menuAtual = new ProdutoView();
                    // menuAtual.exibirMenu();
                    System.out.println("Funcionalidade de Produtos ainda não implementada.");
                    break;
                case 3:
                     // Quando PedidoView for criada, o padrão será o mesmo:
                    // menuAtual = new PedidoView();
                    // menuAtual.exibirMenu();
                    System.out.println("Funcionalidade de Pedidos ainda não implementada.");
                    break;
                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}