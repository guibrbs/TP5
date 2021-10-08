package viewCRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Tela principal que conecta as demais telas do programa
 * @author Guilherme Barbosa Ferreira
 * @version 1.0 (Out 2021)
 */
public class TelaPrincipal implements ActionListener {
    private static JFrame janela = new JFrame("Controle Pastelaria");
    private static JLabel tituloLabel = new JLabel("Menu");
    private static JButton pedidoButton = new JButton("Pedidos");
    private static JButton funcionarioButton = new JButton("Funcionários");
    private static JButton produtoButton = new JButton("Produtos");

    /**
     * Tela Principal, onde contém os componentes que fazem a interação com o usuário
     */
    public TelaPrincipal(){
        //Adiciona as Labels e Buttons da Tela principal
        tituloLabel.setFont(new Font ("Arial", Font.BOLD, 20));
        tituloLabel.setBounds(170, 10, 150, 30);
        pedidoButton.setBounds(140, 100, 120, 30);
        funcionarioButton.setBounds(140, 150, 120, 30);
        produtoButton.setBounds(140, 50, 120, 30);

        janela.add(tituloLabel);
        janela.add(pedidoButton);
        janela.add(funcionarioButton);
        janela.add(produtoButton);

        janela.setLayout(null);
        janela.setSize(400,250);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
    }

    /**
     * Começo do projeto, com a adição dos botões
     * @param args, essencial para a main.
     */
    public static void main(String[] args) {
        TelaPrincipal menu = new TelaPrincipal();

        pedidoButton.addActionListener(menu);
        funcionarioButton.addActionListener(menu);
        produtoButton.addActionListener(menu);
    }

    /**
     * Método que realiza a opção desejada pelo usuario
     * (1) Se clicar no botão de pedido, abrirá um novo método contido na classe TelaPedido
     * (2) Se clicar no botão de funcionário, abrirá um novo método contido na classe TelaFunc
     * (3) Se clicar no botão de produto, abrirá um novo método contido na classe TelaProd
     * @param e Evento que irá fazer a seleção do usuário
     */
    public void actionPerformed (ActionEvent e){
        Object choice = e.getSource();
        //De acordo com a opção do usuário, ele irá abrir uma tela diferente
        if (choice == pedidoButton) {
            new TelaPedido().telaOpcao();
        }
        if (choice == funcionarioButton){
            new TelaFunc().inserirEditar();
        }
        if (choice == produtoButton)
            new TelaProd().inserirEditar();
    }

}