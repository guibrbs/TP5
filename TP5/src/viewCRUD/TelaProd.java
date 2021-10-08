package viewCRUD;

import modelo.Bebida;
import modelo.Estoque;
import modelo.Pastel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Essa classe tem como objetivo realizar o CRUD de pastel e bebida, através da interface swing. Foi dividido da seguinte maneira:
 * Uma tela geral, onde encontra se opções de cadastrar, editar, deletar e atualizar a página, além de uma lista no meio, onde conforme
 for cadastrado os produtos, ao atualizar a página, eles serão incluídos na lista automaticamente. Essa lista mostra o nome do produto e sua quantidade.
 * @author Guilherme Barbosa Ferreira
 * @version 1.0 (Out 2021)
 */
public class TelaProd implements ActionListener {
    private JFrame janela = new JFrame("Janela Produtos");
    private JPanel jpanel = new JPanel();
    private JButton cadastrarPrd = new JButton("Cadastrar");
    private JButton editarPrd = new JButton("Editar");
    private JButton deletarPrd = new JButton("Deletar");
    private JButton refreshPrd = new JButton("Atualizar");
    private JButton salvarPrd = new JButton("Registrar");
    private JButton salvarEdicaoPrd = new JButton("Salvar");
    private JTextField quantProduto = new JTextField("0");
    private JRadioButton radioButtonPastel = new JRadioButton("Pastel");
    private JRadioButton radioButtonBebida = new JRadioButton("Bebida");
    private JComboBox<String> nomeProdutoPastelCombobox = new JComboBox<>();
    private JComboBox<String> nomeProdutoBebidaCombobox = new JComboBox<>();
    private JTextField valorProduto = new JTextField("0.00");
    private static ArrayList<Estoque> estoque = new ArrayList<>();
    private String[] exibeProdutos = new String[50];
    private JList<String> produtosCadastrados = new JList<>(exibeProdutos);

    /**
     * Esse método realiza a inserção da interface swing para o usuário 
     * Os botões de editar e deletar serão ativados somente se for cadastrado algum produto
     */
    public void inserirEditar(){
    	cadastrarProdutoAleatorio();
        janela.setSize(500,400);

        jpanel = new JPanel();
        jpanel.setSize(300,300);
        //Loop de repetição para inserir os dados da Arraylist na JList
        for (int i = 0; i<estoque.size(); i++) {
            exibeProdutos[i] = estoque.get(i).getNomeProduto() + " - Quantidade: " + estoque.get(i).getQuantProduto();
        }
        //Mudança de fonte por questão estética
        produtosCadastrados.setFont(new Font("Arial",Font.PLAIN, 16));
        produtosCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jpanel.add(produtosCadastrados);
        cadastrarPrd.setBounds(28,300,100,30);
        editarPrd.setBounds(138, 300, 100, 30);
        deletarPrd.setBounds(248, 300,100,30);
        refreshPrd.setBounds(358,300,100,30);

        janela.add(cadastrarPrd);
        janela.add(editarPrd);
        janela.add(deletarPrd);
        janela.add(refreshPrd);
        janela.add(jpanel);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);

        cadastrarPrd.addActionListener(this);
        editarPrd.addActionListener(this);
        deletarPrd.addActionListener(this);
        refreshPrd.addActionListener(this);

        if (estoque.size()>0){
            editarPrd.setEnabled(true);
            deletarPrd.setEnabled(true);
        }else{
            editarPrd.setEnabled(false);
            deletarPrd.setEnabled(false);
        }
    }
    /**
     * Cria uma nova janela, e com a chamada do método placeComponentsCadastroProduto, insere a interface para o usuário
     */
    public void cadastrarProduto(){
        JFrame janelaCadastroProduto = new JFrame();
        JPanel painelCadastroProduto = new JPanel();
        //SetBounds do Botão de salvar
        salvarPrd.setBounds(100,200,90,30);
        painelCadastroProduto.add(salvarPrd);
        janelaCadastroProduto.add(painelCadastroProduto);
        //Chamada de método que insere JLabel e JTextField
        placeComponentsCadastroProduto(painelCadastroProduto);

        janelaCadastroProduto.setSize(300,300);
        janelaCadastroProduto.setLocationRelativeTo(null);
        janelaCadastroProduto.setVisible(true);

        salvarPrd.addActionListener(this::salvarPrdActionPerformed);
    }
    /**
     * Complemento do método cadastrarProduto, esse método insere a interface de cadastro de produtos para o usuário.
     * A tela de cadastro está definida da seguinte maneira
     * (1) Terá duas opções de escolha entre cadastrar os produtos referentes ao pastel, e os produtos referentes a bebida, divididos em JRadioButton
     * (2) Caso o usuário escolha o pastel, aparecerá um campo para ele escolher o que deseja cadastrar referente ao pastel
     * (3) Caso escolha bebida, aparecerá um novo campo no lugar do anterior, para que ele escolha o que deseja cadastrar referente a bebida
     * @param painelCadastroProduto, parãmetro passado para adicionar o método junto com a jpanel do método anterior
     */
    public void placeComponentsCadastroProduto(JPanel painelCadastroProduto) {
        ButtonGroup bg = new ButtonGroup();
        painelCadastroProduto.setLayout(null);
        
        JLabel tituloLabel = new JLabel("Cadastro de Produtos");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 17));
        tituloLabel.setBounds(55, 5, 190, 35);
        painelCadastroProduto.add(tituloLabel);

        radioButtonPastel.setBounds(10, 40, 80, 30);
        bg.add(radioButtonPastel);
        painelCadastroProduto.add(radioButtonPastel);

        radioButtonBebida.setBounds(100, 40, 80, 30);
        bg.add(radioButtonBebida);
        painelCadastroProduto.add(radioButtonBebida);

        JLabel nomeProdutoLabel = new JLabel("Nome: ");
        nomeProdutoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nomeProdutoLabel.setBounds(10, 75, 160, 35);
        painelCadastroProduto.add(nomeProdutoLabel);

        JLabel quantProdutoLabel = new JLabel("Quantidade: ");
        quantProdutoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantProdutoLabel.setBounds(10, 110, 160, 35);
        painelCadastroProduto.add(quantProdutoLabel);

        JLabel valorProdutoLabel = new JLabel("Valor: ");
        valorProdutoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        valorProdutoLabel.setBounds(10, 145, 160, 35);
        painelCadastroProduto.add(valorProdutoLabel);

        String[] opcoesComboboxPastel = {"Massa Pastel - Pequeno", "Massa Pastel - Médio", "Massa Pastel - Grande", "Recheio - Calabresa",
                "Recheio - Carne", "Recheio - Carne com Queijo", "Recheio - Queijo", "Recheio - Frango com Catupiry", "Recheio - Palmito"};
        nomeProdutoPastelCombobox = new JComboBox<>(opcoesComboboxPastel);
        nomeProdutoPastelCombobox.setForeground(Color.GRAY);
        nomeProdutoPastelCombobox.setBounds(95, 75, 170, 25);
        nomeProdutoPastelCombobox.setMaximumRowCount(5);
        nomeProdutoPastelCombobox.setVisible(false);
        painelCadastroProduto.add(nomeProdutoPastelCombobox);

        String[] opcoesComboboxBebida = {"Refrigerante - Pequeno", "Refrigerante - Médio", "Refrigerante - Grande", "Caldo de Cana - Pequeno",
                "Caldo de Cana - Médio","Caldo de Cana - Grande", "Água - Pequeno","Água - Médio","Água - Grande", 
                "Suco - Pequeno", "Suco - Médio","Suco - Grande"};
        nomeProdutoBebidaCombobox = new JComboBox<>(opcoesComboboxBebida);
        nomeProdutoBebidaCombobox.setForeground(Color.GRAY);
        nomeProdutoBebidaCombobox.setBounds(95, 75, 170, 25);
        nomeProdutoBebidaCombobox.setMaximumRowCount(5);
        nomeProdutoBebidaCombobox.setVisible(false);
        painelCadastroProduto.add(nomeProdutoBebidaCombobox);

        quantProduto.setBounds(95, 110, 170, 25);
        quantProduto.setForeground(Color.GRAY);
        painelCadastroProduto.add(quantProduto);

        valorProduto.setBounds(95, 145, 170, 25);
        valorProduto.setForeground(Color.GRAY);
        painelCadastroProduto.add(valorProduto);

        radioButtonPastel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(radioButtonPastel.isSelected())
                    nomeProdutoPastelCombobox.setVisible(true);
                else
                    nomeProdutoPastelCombobox.setVisible(false);
            }
        });

        radioButtonBebida.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (radioButtonBebida.isSelected())
                    nomeProdutoBebidaCombobox.setVisible(true);
                else
                    nomeProdutoBebidaCombobox.setVisible(false);
            }
        });
    }
    /**
     * Insere uma nova janela, onde reaproveita o método placeComponentsCadastroProduto
     * Essa tela é a mesma que a tela de cadastro, porém com as informações ja previamente cadastradas pelo usuário.
     */
    public void editarProduto(){
        JFrame telaEditarProduto = new JFrame();
        JPanel panelEditarProduto = new JPanel();
        //Insere os novos dados dos produtos
        nomeProdutoPastelCombobox.setSelectedItem(estoque.get(produtosCadastrados.getSelectedIndex()).getNomeProduto());
        quantProduto.setText(Integer.toString(estoque.get(produtosCadastrados.getSelectedIndex()).getQuantProduto()));
        valorProduto.setText(Float.toString(estoque.get(produtosCadastrados.getSelectedIndex()).getValorProduto()));
        placeComponentsCadastroProduto(panelEditarProduto);

        salvarEdicaoPrd.setBounds(100,170,90,30);

        telaEditarProduto.add(salvarEdicaoPrd);
        telaEditarProduto.add(panelEditarProduto);
        telaEditarProduto.setSize(300,270);
        telaEditarProduto.setLocationRelativeTo(null);
        telaEditarProduto.setVisible(true);

        salvarEdicaoPrd.addActionListener(this::salvarEdicaoPrdActionPerformed);
    }

    /**
     * Esse actionPerformed realiza as ações referentes aos botões de cadastrar, editar, deletar, e atualizar.
     * (1) Ao clicar em cadastrar, ele criará uma nova instancia do método cadastrarProduto
     * (2) Ao clicar em editar, ele criará uma nova instância do método editarProduto
     * (3) Ao clicar em deletar, será acessado o valor do índice em que o objeto em questão será deletado através da arrayList, posteriormente através do 
     .remove, será deletado todas as informações sobre aquele produto cadastrado.
     * (4) Ao clicar em atualizar ele primeiramente irá verificar se algum item cadastrado foi utilizado no pedido, através dos métodos criados na classe
     TelaPedido anteriormente. Se foi utilizado, ele descontará automaticamente da quantidade cadastrada. Posteriormente encerrará a janela, e abre para 
     atualizar os dados.
     * (5) Os itens cadastrados foram pensados justamente para terem o atributo de serem por unidade, ou seja, se um usuário cadastra um Pastel de Calabresa pequeno,
     tem se em mente que irá usar uma porção da massa de pastel pequena cadastrada.
     */
    public void actionPerformed(ActionEvent e) {
        Object escolha = e.getSource();
        //Ações dos botões de cadastrar, editar, deletar e atualizar
        if (escolha==cadastrarPrd){
            new TelaProd().cadastrarProduto();
        }
        if (escolha==editarPrd)
            this.editarProduto();

        if(escolha==deletarPrd){
            estoque.remove(produtosCadastrados.getSelectedIndex());
        }
        if(escolha==refreshPrd){
            //Confere se o usuário pediu algum pastel e desconta da quantidade de massa
            for (Estoque value : estoque) {
                if (new TelaPedido().retornaOpcaoPastel().equals(value.getNomeProduto())) {
                    value.setQuantProduto(value.getQuantProduto() - new TelaPedido().retornaQuantidadePastel());
                } else {
                    break;
                }
            }
            //Confere qual sabor de pastel que o usuário selecionou e desconta da quantidade de recheio
            for (Estoque value : estoque) {
                if (new TelaPedido().retornaSaboresPastel().equals(value.getNomeProduto())) {
                    value.setQuantProduto(value.getQuantProduto() - new TelaPedido().retornaQuantidadePastel());
                } else {
                    break;
                }
            }
            for (Estoque value : estoque){
                if (new TelaPedido().retornaBebida().equals(value.getNomeProduto())){
                    value.setQuantProduto(value.getQuantProduto() - new TelaPedido().retornaQuantidadeBebida());
                }
            }
            //Refresh na página com dispose e a criação do método novamente
            janela.dispose();
            new TelaProd().inserirEditar();
        }
    }
    /**
     * Verifica qual opção foi escolhida pelo usuário, entre cadastrar pastel ou bebida. Se foi escolhido pastel, é instanciado da classe Pastel
     e a partir daí são cadastrados os elementos. Se for bebida, é instanciado da classe Bebida e a partir disso serão cadastrados na ArrayList.
     * Somente uma ArrayList para as duas instâncias
     * @param e, juntamente com o ActionEvent, realiza a ação proposta.
     */
    public void salvarPrdActionPerformed(ActionEvent e){
        //Salva os dados na ArrayList
        if (radioButtonPastel.isSelected()) {
            Pastel pastel = new Pastel();
            pastel.setNomeProduto(Objects.requireNonNull(nomeProdutoPastelCombobox.getSelectedItem()).toString());
            pastel.setQuantProduto(Integer.parseInt(quantProduto.getText()));
            pastel.setValorProduto(Float.parseFloat(valorProduto.getText()));
            estoque.add(pastel);
        }
        if (radioButtonBebida.isSelected()){
            Bebida bebida = new Bebida();
            bebida.setNomeProduto(Objects.requireNonNull(nomeProdutoBebidaCombobox.getSelectedItem().toString()));
            bebida.setQuantProduto(Integer.parseInt(quantProduto.getText()));
            bebida.setValorProduto(Float.parseFloat(valorProduto.getText()));
            estoque.add(bebida);
        }
        
        JOptionPane.showMessageDialog(null, "Produto registrado!", "Cadastro de Produtos", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Igualmente ao método salvarPrdActionPerformed, nesse método é feita a conferência sobre qual item será salvo, se será um pastel ou uma bebida.
     * Posteriormente é feito o cadastro.
     * @param e, juntamente com o ActionEvent, realiza a ação proposta.
     */
    public void salvarEdicaoPrdActionPerformed (ActionEvent e){
        //Salva a edição feita pelo usuário
    	if (radioButtonPastel.isSelected()) {
	        estoque.get(produtosCadastrados.getSelectedIndex()).setNomeProduto(Objects.requireNonNull(nomeProdutoPastelCombobox.getSelectedItem()).toString());
	        estoque.get(produtosCadastrados.getSelectedIndex()).setQuantProduto(Integer.parseInt(quantProduto.getText()));
	        estoque.get(produtosCadastrados.getSelectedIndex()).setValorProduto(Float.parseFloat(valorProduto.getText()));
    	}
    	if (radioButtonBebida.isSelected()) {
    		estoque.get(produtosCadastrados.getSelectedIndex()).setNomeProduto(Objects.requireNonNull(nomeProdutoBebidaCombobox.getSelectedItem().toString()));
    		estoque.get(produtosCadastrados.getSelectedIndex()).setQuantProduto(Integer.parseInt(quantProduto.getText()));
	        estoque.get(produtosCadastrados.getSelectedIndex()).setValorProduto(Float.parseFloat(valorProduto.getText()));
    	}

        JOptionPane.showMessageDialog(null, "Produto registrado!", "Cadastro de Produtos", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Realiza o cadastramento de um ingrediente de Pastel e um ingrediente de Bebida aleatorio
     */
    public void cadastrarProdutoAleatorio(){
        Pastel pastelAleatorio = new Pastel();
        Bebida bebidaAleatoria = new Bebida();

        pastelAleatorio.setNomeProduto("Recheio - Carne com Queijo");
        pastelAleatorio.setQuantProduto(30);
        pastelAleatorio.setValorProduto(102);
        bebidaAleatoria.setNomeProduto("Refrigerante - Grande");
        bebidaAleatoria.setQuantProduto(25);
        bebidaAleatoria.setValorProduto(130);
        estoque.add(pastelAleatorio);
        estoque.add(bebidaAleatoria);
    }
}