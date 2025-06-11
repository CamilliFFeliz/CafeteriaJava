package view;

/**
 * Interface para padronizar todas as classes de "View" que exibem um menu.
 * Garante que toda classe que representa uma tela de menu no console
 * tenha um método de entrada padrão para ser chamado.
 */
public interface MenuView {

    /**
     * Exibe o menu de opções para o usuário e processa a entrada.
     * Este é o ponto de entrada para a interação do usuário com uma seção do programa.
     */
    void exibirMenu();
}