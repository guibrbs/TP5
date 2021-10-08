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
    private static JButton funcionarioButton = new JButton("Funcion�rios");
    private static JButton produtoButton = new JButton("Produtos");

    /**
     * Tela Principal, onde cont�m os componentes que fazem a intera��o com o usu�rio
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
     * Come�o do projeto, com a adi��o dos bot�es
     * @param args, essencial para a main.
     */
    public static void main(String[] args) {
        TelaPrincipal menu = new TelaPrincipal();

        pedidoButton.addActionListener(menu);
        funcionarioButton.addActionListener(menu);
        produtoButton.addActionListener(menu);
    }

    /**
     * M�todo que realiza a op��o desejada pelo usuario
     * (1) Se clicar no bot�o de pedido, abrir� um novo m�todo contido na classe TelaPedido
     * (2) Se clicar no bot�o de funcion�rio, abrir� um novo m�todo contido na classe TelaFunc
     * (3) Se clicar no bot�o de produto, abrir� um novo m�todo contido na classe TelaProd
     * @param e Evento que ir� fazer a sele��o do usu�rio
     */
    public void actionPerformed (ActionEvent e){
        Object choice = e.getSource();
        //De acordo com a op��o do usu�rio, ele ir� abrir uma tela diferente
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