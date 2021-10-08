package viewCRUD;

import modelo.Pedido;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Essa classe tem como objetivo primeiramente apresentar ao usu�rio duas opc�es, cadastrar ou listar os pedidos, por�m s� � poss�vel
 listar, quando algum pedido for cadastrado no sistema. Como cont�m as op��es de cadastrar ou listar, os m�todos que inserem a 
 interface swing tamb�m est�o presentes aqui. Encontra se as fun��es de cadastrar, editar, deletar, buscar e atualizar a p�gina
 a partir dos ActionPerformed.
 *@author Guilherme Barbosa Ferreira
 *@version 1.0 (Out 2021)
 */
public class TelaPedido implements ActionListener {
    private final JFrame janelaOpcoes = new JFrame("Pedidos");
    private JFrame janelaLista = new JFrame("Lista de Pedidos");
    private JList<String> listaPedidosCadastrados;
    private JButton cadastrar_pedido_Button;
    private JButton pedidosButton;
    private JButton cadastrarButtton = new JButton("Cadastrar");
    private JButton editarButton = new JButton("Editar");
    private JButton deletarButton = new JButton("Deletar");
    private JButton refreshButton = new JButton("Atualizar");
    private JButton salvarButton = new JButton("Salvar");
    private JTextField pesquisaPedido = new JTextField();
    private static JTextField numMesaText;
    private static JTextField quantPastelText;
    private static JTextField quantBebidaText;
    private static JTextField saborBebidaText;
    private static JComboBox<String> bebidaCombobox;
    private static JComboBox<String> opcoesPastelCombobox;
    private static JComboBox<String> tamanhoCombobox;
    private static JComboBox<String> tamanhoBebidaCombobox;
    private static boolean validacao = false;
    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static int numPedido = 0;

    /**
     * Esse m�todo tem como objetivo somente inserir a interface swing para intera��o com o usu�rio, em que ele
     escolha as op��es de cadastrar ou listar
     */
    public void telaOpcao() {
        //Adiciona os bot�es e a jframe
    	cadastroPedidoAleatorio();
        cadastrar_pedido_Button = new JButton("Cadastrar pedidos");
        pedidosButton = new JButton("Pedidos");

        cadastrar_pedido_Button.setBounds(120, 20, 150, 30);
        pedidosButton.setBounds(120, 70, 150, 30);

        janelaOpcoes.setLayout(null);

        janelaOpcoes.add(cadastrar_pedido_Button);
        janelaOpcoes.add(pedidosButton);

        janelaOpcoes.setSize(400, 180);
        janelaOpcoes.setVisible(true);
        janelaOpcoes.setLocationRelativeTo(null);

        cadastrar_pedido_Button.addActionListener(this);
        pedidosButton.addActionListener(this);
    }

    /**
     * Esse m�todo tem como objetivo inserir a interface swing na tela de cadastro, sendo completada pelo 
     m�todo placeComponentsCadastro
     */
    public void telaCadastroPedidos() {
        //Cria��o da tela de cadastro, com JFrame, JPanel, JButton
        JFrame janelaCadastro = new JFrame("Cadastro de pedidos");
        JPanel painel = new JPanel();

        cadastrarButtton.setBounds(110, 300, 100, 35);
        painel.add(cadastrarButtton);

        janelaCadastro.add(painel);
        numMesaText = new JTextField("0", 13);
        quantPastelText = new JTextField("0", 13);
        quantBebidaText = new JTextField("0", 13);
        saborBebidaText = new JTextField("Sabor", 13);
        placeComponentsCadastro(painel);

        janelaCadastro.setSize(340, 390);
        janelaCadastro.setLocationRelativeTo(null);
        janelaCadastro.setVisible(true);

        cadastrarButtton.addActionListener(this::cadastrarButtonActionPerformed);

        numMesaText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (!numMesaText.getText().isEmpty() && !quantBebidaText.getText().isEmpty() && !quantPastelText.getText().isEmpty() && !saborBebidaText.getText().isEmpty())
                    cadastrarButtton.setEnabled(true);
            }

            public void removeUpdate(DocumentEvent e) {
                if (numMesaText.getText().isEmpty()) {
                    cadastrarButtton.setEnabled(false);
                    cadastrarButtton.setToolTipText("Cadastre o n�mero da mesa!");
                }
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });

        quantBebidaText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (!numMesaText.getText().isEmpty() && !quantBebidaText.getText().isEmpty() && !quantPastelText.getText().isEmpty() && !saborBebidaText.getText().isEmpty())
                    cadastrarButtton.setEnabled(true);
            }

            public void removeUpdate(DocumentEvent e) {
                if (quantBebidaText.getText().isEmpty()){
                    cadastrarButtton.setEnabled(false);
                    cadastrarButtton.setToolTipText("Cadastre a quantidade de bebida!");
                }
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });

        quantPastelText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (!numMesaText.getText().isEmpty() && !quantBebidaText.getText().isEmpty() && !quantPastelText.getText().isEmpty() && !saborBebidaText.getText().isEmpty()) {
                    cadastrarButtton.setEnabled(true);
                    cadastrarButtton.setToolTipText(null);
                }
            }

            public void removeUpdate(DocumentEvent e) {
                if (quantPastelText.getText().isEmpty()){
                    cadastrarButtton.setEnabled(false);
                    cadastrarButtton.setToolTipText("Cadastre a quantidade de pastel!");
                }
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    /**
     * Sendo complemento do m�todo telaCadastroPedidos, esse m�todo realiza somente a fun��o de colocar a interface swing 
     para que o cadastro seja realizado posteriormente por outro m�todo
     * @param painel
     */
    
    private static void placeComponentsCadastro(JPanel painel) {
        //M�todo que insere as Labels e Buttons
        painel.setLayout(null);

        JLabel numMesaLabel = new JLabel("N�mero da mesa: ");
        numMesaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        numMesaLabel.setBounds(10, 5, 145, 35);
        painel.add(numMesaLabel);

        numMesaText.setBounds(165, 5, 145, 25);
        numMesaText.setForeground(Color.GRAY);
        painel.add(numMesaText);

        JLabel opcoesPastelLabel = new JLabel("Sabor do Pastel: ");
        opcoesPastelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        opcoesPastelLabel.setBounds(10, 40, 145, 35);
        painel.add(opcoesPastelLabel);

        String[] opcoes = {"Calabresa", "Carne", "Carne com queijo", "Queijo", "Presunto", "Frango com Catupiry", "Palmito"};
        opcoesPastelCombobox = new JComboBox<>(opcoes);
        opcoesPastelCombobox.setForeground(Color.GRAY);
        opcoesPastelCombobox.setBounds(165, 40, 145, 25);
        opcoesPastelCombobox.setMaximumRowCount(3);
        painel.add(opcoesPastelCombobox);

        JLabel tamanhoLabel = new JLabel("Tamanho do Pastel: ");
        tamanhoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        tamanhoLabel.setBounds(10, 75, 145, 35);
        painel.add(tamanhoLabel);

        String[] tamanho = {"Pequeno", "M�dio", "Grande"};
        tamanhoCombobox = new JComboBox<>(tamanho);
        tamanhoCombobox.setForeground(Color.GRAY);
        tamanhoCombobox.setBounds(165, 75, 145, 25);
        tamanhoCombobox.setMaximumRowCount(3);
        painel.add(tamanhoCombobox);

        JLabel quantPastelLabel = new JLabel("Quantidade de Pastel: ");
        quantPastelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantPastelLabel.setBounds(10, 110, 145, 35);
        painel.add(quantPastelLabel);

        quantPastelText.setBounds(165, 110, 145, 25);
        quantPastelText.setForeground(Color.GRAY);
        painel.add(quantPastelText);

        JLabel bebidaLabel = new JLabel("Bebida: ");
        bebidaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bebidaLabel.setBounds(10, 145, 145, 35);
        painel.add(bebidaLabel);

        String[] opcoesBebida = {"Caldo de Cana", "Refrigerante", "Suco", "�gua"};
        bebidaCombobox = new JComboBox<>(opcoesBebida);
        bebidaCombobox.setForeground(Color.GRAY);
        bebidaCombobox.setBounds(165, 145, 145, 25);
        bebidaCombobox.setMaximumRowCount(3);
        painel.add(bebidaCombobox);

        JLabel quantBebidaLabel = new JLabel("Quantidade de Bebida: ");
        quantBebidaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        quantBebidaLabel.setBounds(10, 180, 160, 35);
        painel.add(quantBebidaLabel);

        quantBebidaText.setBounds(165, 180, 145, 25);
        quantBebidaText.setForeground(Color.GRAY);
        painel.add(quantBebidaText);

        JLabel tamanhoBebidaLabel = new JLabel("Tamanho da Bebida: ");
        tamanhoBebidaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        tamanhoBebidaLabel.setBounds(10, 215, 160, 35);
        painel.add(tamanhoBebidaLabel);

        String[] tamanhoBebida = {"Pequeno", "M�dio", "Grande"};
        tamanhoBebidaCombobox = new JComboBox<>(tamanhoBebida);
        tamanhoBebidaCombobox.setForeground(Color.GRAY);
        tamanhoBebidaCombobox.setBounds(165, 215, 145, 25);
        tamanhoBebidaCombobox.setMaximumRowCount(3);
        painel.add(tamanhoBebidaCombobox);

        JLabel saborBebidaLabel = new JLabel("Sabor da Bebida: ");
        saborBebidaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        saborBebidaLabel.setBounds(10, 250, 160, 35);
        painel.add(saborBebidaLabel);

        saborBebidaText.setBounds(165, 250, 145, 25);
        saborBebidaText.setForeground(Color.GRAY);
        painel.add(saborBebidaText);
    }

    /**
     * Esse m�todo tem como objetivo criar a janela caso o usu�rio escolha a op��o de pedidos, � nela onde a interface � adicionada
     contando com um splitpanel que divide a tela em dois panels, sendo o da esquerda os n�meros dos pedidos, e da direita as informa��es.
     O intuito � que quando o usu�rio clique em algum pedido, automaticamente apare�a as informa��es do mesmo na direita
     */
    public void telaPedidos() {
        //Chama a JList criada pro m�todo
    	listaPedidosCadastrados = new JList<>();

        final JLabel label = new JLabel();
        JPanel panelPesquisa = new JPanel();
        JPanel panel = new JPanel();
        DefaultListModel<String> model = new DefaultListModel<>();
        JLabel labelPesquisa = new JLabel("Pesquisar:");
        JSplitPane splitPane = new JSplitPane();
        //Insere os n�meros de pedidos da ArrayList na JList
        listaPedidosCadastrados.setModel(model);
        for (int a = 0; a < pedidos.size(); a++) {
            model.addElement((Integer.toString(pedidos.get(a).getNumPedido())));
        }
        listaPedidosCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listaPedidosCadastrados.setFont(new Font("Arial", Font.PLAIN, 14));
        //Quando o usu�rio clicar em qualquer item da JList, ir� aparacer as informa��es sobre os dados do pedido
        listaPedidosCadastrados.getSelectionModel().addListSelectionListener(e -> {
            listaPedidosCadastrados.getSelectedValue();
            int index = listaPedidosCadastrados.getSelectedIndex();
            label.setFont(new Font("Arial",Font.PLAIN, 14));
            label.setText("<html><p style=\"text-align:center;\">" + "N�mero da mesa: " + pedidos.get(index).getNumMesa() + "<br/>Data e Hora: " + pedidos.get(index).getDataHora() +
                    "<br/>Sabor do Pastel: " + pedidos.get(index).getSaborPastel() + "<br/>Tamanho do Pastel: " + pedidos.get(index).getTamPastel()
                    + "<br/>Quantidade de Pastel: " + pedidos.get(index).getQuantPasteis() + "<br/>Bebida: " + pedidos.get(index).getTipoBebida() + "<br/>Quantidade bebida: "
                    + pedidos.get(index).getQuantBebida() + "<br/>Tamanho bebida: " + pedidos.get(index).getTamanhoBebida() + "<br/>Sabor bebida: "
                    + pedidos.get(index).getSaborBebida() + "<br/>Pre�o: R$"+pedidos.get(index).getValorPedido()+"</p><html>");
        });

        if (pedidos.size()<=0){
            editarButton.setEnabled(false);
            deletarButton.setEnabled(false);
        }

        //Adiciona scrollpane e divide a tela em dois, o lado da esquerda s�o os n�meros dos pedidos, e da direita os dados dos pedidos
        splitPane.setLeftComponent(new JScrollPane(listaPedidosCadastrados));
        splitPane.setDividerLocation(90);
        panel.add(label);
        splitPane.setRightComponent(panel);

        pesquisaPedido.setPreferredSize(new Dimension(200,25));
        pesquisaPedido.setBorder(new LineBorder(Color.BLACK, 1, true));
        labelPesquisa.setFont(new Font("Arial", Font.PLAIN, 14));

        panelPesquisa.add(labelPesquisa);
        panelPesquisa.add(pesquisaPedido);

        editarButton.setHorizontalAlignment(SwingConstants.CENTER);
        editarButton.setBounds(100, 230, 93, 30);

        deletarButton.setHorizontalAlignment(SwingConstants.CENTER);
        deletarButton.setBounds(195, 230, 93, 30);

        refreshButton.setHorizontalAlignment(SwingConstants.CENTER);
        refreshButton.setBounds(290, 230, 93, 30);

        janelaLista.add(panelPesquisa, BorderLayout.PAGE_START);
        janelaLista.add(editarButton);
        janelaLista.add(deletarButton);
        janelaLista.add(refreshButton);
        janelaLista.add(splitPane);
        janelaLista.pack();
        janelaLista.setLocationRelativeTo(null);
        janelaLista.setSize(400, 300);
        janelaLista.setVisible(true);

        editarButton.addActionListener(this::editarButtonActionPerformed);
        deletarButton.addActionListener(this::deletarButtonActionPerformed);
        refreshButton.addActionListener(this::refreshButtonActionPerformed);
        pesquisaPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoPedido();
            }
        });
    }

    /**
     * Cria uma nova tela para editar em que insere os elementos selecionados anteriormente pelo usu�rio no cadastro,
     se o usu�rio desejar alterar os dado, ter� essa liberdade.
     */
    public void editarPedido() {
        JFrame janelaEditar = new JFrame();
        JPanel painelEditar = new JPanel();

        //Prepara a JFieldText para receber outro valor que ser� inserido na edi��o
        numMesaText = new JTextField(Integer.toString(pedidos.get(listaPedidosCadastrados.getSelectedIndex()).getNumMesa()), 20);
        opcoesPastelCombobox.setSelectedItem(pedidos.get(listaPedidosCadastrados.getSelectedIndex()).getSaborPastel());
        tamanhoCombobox.setSelectedItem(pedidos.get(listaPedidosCadastrados.getSelectedIndex()).getTamPastel());
        bebidaCombobox.setSelectedItem(pedidos.get(listaPedidosCadastrados.getSelectedIndex()).getTipoBebida());
        quantBebidaText = new JTextField(Integer.toString(pedidos.get(listaPedidosCadastrados.getSelectedIndex()).getQuantBebida()), 20);
        tamanhoBebidaCombobox.setSelectedItem(pedidos.get(listaPedidosCadastrados.getSelectedIndex()).getTamanhoBebida());
        saborBebidaText.setText(pedidos.get(listaPedidosCadastrados.getSelectedIndex()).getSaborBebida());
        
        placeComponentsCadastro(painelEditar);

        salvarButton.setBounds(100, 300, 90, 30);

        janelaEditar.add(salvarButton);
        janelaEditar.add(painelEditar);
        janelaEditar.setLocationRelativeTo(null);
        janelaEditar.setVisible(true);
        janelaEditar.setSize(340, 390);

        salvarButton.addActionListener(this::salvarButtonActionPerformed);

    }

    /**
     * Seleciona atrav�s de um evento, a op��o do usu�rio entre cadastrar ou ver os pedidos.
     Ao clicar em cadastrar, ele criar� um novo m�todo de telaCadastroPedidos.
     Se clicar em pedidos, ser� feita anteriormente uma valida��o, para ver se algum pedido ja foi cadastrado, essa valida��o
     est� inserida no m�todo cadastrarButtonActionPerformed, com o intuito de somente quando o usu�rio clicar no bot�o de cadastrar,
     ser� validado o cadastro. Se a valida��o for verdadeira, ele criar� um novo m�todo de telaPedidos, caso for falso, aparecer� 
     uma mensagem de erro.
     *@param b, cria��o de um objeto choice, que recebe os atributos de e.
     */
    public void actionPerformed(ActionEvent b) {
        Object choice = b.getSource();

        if (choice == cadastrar_pedido_Button) {
            //Ao clicar no bot�o para come�ar o cadastramento, cria-se um novo m�todo
            new TelaPedido().telaCadastroPedidos();
        }
        if (choice == pedidosButton) {
            //Verifica se algum pedido j� foi cadastrado, sen�o aparece mensagem de erro
            if (validacao) {
                new TelaPedido().telaPedidos();
            } else {
                JOptionPane.showMessageDialog(null, "� preciso cadastrar ao menos um pedido antes de buscar!", null, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Tem com objetivo cadastrar os pedidos na ArrayList. � dividido em 3 etapas.
     * (1) Primeiramente � instanciado a classe Pedido, e a partir dela ser� cadastrado os campos a partir da intera��o com o usu�rio
     * (2) Ap�s, para calcular o valor do pedido, � feito uma verifica��o de escolha entre tamanhos dos produtos com if e elseif e a partir
     disso � gerado o valor.
     * (3) � feito a chamada de m�todos que ir�o retornar alguns valores para serem descontados do estoque, como a quantidade de massa
     de pastel (baseada por unidade).
     * @param c, realiza a a��o.
     */
    public void cadastrarButtonActionPerformed(ActionEvent c) {
        validacao = true;

        Pedido p = new Pedido();
        //Formato de data
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //Insere os dados inseridos na Arraylist
        numPedido++;
        p.setNumMesa(Integer.parseInt(numMesaText.getText()));
        p.setDataHora(dft.format(LocalDateTime.now()));
        p.setSaborPastel(Objects.requireNonNull(opcoesPastelCombobox.getSelectedItem()).toString());
        p.setTamPastel(Objects.requireNonNull(tamanhoCombobox.getSelectedItem()).toString());
        p.setQuantPasteis(Integer.parseInt(quantPastelText.getText()));
        p.setTipoBebida(Objects.requireNonNull(bebidaCombobox.getSelectedItem()).toString());
        p.setQuantBebida(Integer.parseInt(quantBebidaText.getText()));
        p.setTamanhoBebida(Objects.requireNonNull(tamanhoBebidaCombobox.getSelectedItem()).toString());
        p.setSaborBebida(saborBebidaText.getText());
        p.setNumPedido(numPedido);

        if(tamanhoCombobox.getSelectedItem().equals("Pequeno") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
            p.setValorPedido(3*Integer.parseInt(quantPastelText.getText())+2*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Pequeno") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
            p.setValorPedido(3*Integer.parseInt(quantPastelText.getText())+4*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Pequeno") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
            p.setValorPedido(3*Integer.parseInt(quantPastelText.getText())+6*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("M�dio") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
            p.setValorPedido(5 * Integer.parseInt(quantPastelText.getText())+2*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("M�dio") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
            p.setValorPedido(5*Integer.parseInt(quantPastelText.getText())+4*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("M�dio") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
            p.setValorPedido(5 * Integer.parseInt(quantPastelText.getText())+6*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Grande") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
            p.setValorPedido(7 * Integer.parseInt(quantPastelText.getText())+2*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Grande") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
            p.setValorPedido(7*Integer.parseInt(quantPastelText.getText())+4*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Grande") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
            p.setValorPedido(7 * Integer.parseInt(quantPastelText.getText())+6*Integer.parseInt(quantBebidaText.getText()));
        }

        pedidos.add(p);

        //Chama os m�todos para realizar o desconto de produtos ao cadastrar pedidos
        retornaOpcaoPastel();
        retornaQuantidadePastel();
        retornaQuantidadeBebida();
        retornaSaboresPastel();
        retornaBebida();

        JOptionPane.showMessageDialog(null, "Pedido cadastrado!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Tem como objetivo, ao usu�rio clicar no bot�o de editar, chamar o m�todo editarPedido.
     * @param e, realiza a a��o.
     */
    public void editarButtonActionPerformed(ActionEvent e) {
        editarPedido();
    }

    /**
     * Tem como objetivo excluir algum pedido da lista. O �ndice em que ser� exlcu�do � baseado no item em que o usu�rio
     ir� selecionar.
     * @param e, realiza a a��o.
     */
    public void deletarButtonActionPerformed(ActionEvent e) {
        pedidos.remove(listaPedidosCadastrados.getSelectedIndex());
    }

    /**
     * Ao clicar no bot�o de atualizar, o frame de janelaLista � encerrado, posteriormente � criado um novo m�todo de telaPedidos.
     * @param e, realiza a a��o.
     */
    public void refreshButtonActionPerformed(ActionEvent e) {
        janelaLista.dispose();
        new TelaPedido().telaPedidos();
    }
    
    /**
     * A��o ao usu�rio clicar no bot�o salvar.
     * Todos os dados ser�o substitu�dos no �ndice dentro da ArrayList, por�m so mudar� aqueles em que o usu�rio modificar ao realizar a edi��o
     * @param e, realiza a a��o.
     */
    public void salvarButtonActionPerformed(ActionEvent e) {
        //Salva a edi��o feita pelo usu�rio nos pedidos
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setNumMesa(Integer.parseInt(numMesaText.getText()));
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setDataHora(dft.format(LocalDateTime.now()));
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setSaborPastel(Objects.requireNonNull(opcoesPastelCombobox.getSelectedItem()).toString());
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setTamPastel(Objects.requireNonNull(tamanhoCombobox.getSelectedItem()).toString());
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setQuantPasteis(Integer.parseInt(quantPastelText.getText()));
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setTipoBebida(Objects.requireNonNull(bebidaCombobox.getSelectedItem()).toString());
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setQuantBebida(Integer.parseInt(quantBebidaText.getText()));
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setTamanhoBebida(Objects.requireNonNull(tamanhoBebidaCombobox.getSelectedItem()).toString());
        pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setSaborBebida(saborBebidaText.getText());
        if(tamanhoCombobox.getSelectedItem().equals("Pequeno") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(3*Integer.parseInt(quantPastelText.getText())+2*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Pequeno") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(3*Integer.parseInt(quantPastelText.getText())+4*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Pequeno") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(3*Integer.parseInt(quantPastelText.getText())+6*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("M�dio") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(5 * Integer.parseInt(quantPastelText.getText())+2*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("M�dio") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(5*Integer.parseInt(quantPastelText.getText())+4*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("M�dio") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(5 * Integer.parseInt(quantPastelText.getText())+6*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Grande") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(7 * Integer.parseInt(quantPastelText.getText())+2*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Grande") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(7*Integer.parseInt(quantPastelText.getText())+4*Integer.parseInt(quantBebidaText.getText()));
        }else if(tamanhoCombobox.getSelectedItem().equals("Grande") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
            pedidos.get(listaPedidosCadastrados.getSelectedIndex()).setValorPedido(7 * Integer.parseInt(quantPastelText.getText())+6*Integer.parseInt(quantBebidaText.getText()));
        }
        
        JOptionPane.showMessageDialog(null, "Edi��o cadastrada!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        
    }

    /**
     * M�todo que retorna o tamanho do pastel, s�o duas etapas:
     * (1) � feita uma verifica��o, ja feita anteriormente para constatar que algum item ja foi cadastrado
     * (2) Se ja foi cadastrado, � feita outra verifica��o que ir� comparar o tamanho do pastel selecionado pelo usu�rio com tamanhos j� pr� definidos
     * @return, se o tamanho selecionado pelo usu�rio for "Grande", retornar� "Massa Pastel Grande", e assim para todos os casos, observando o tamanho que ser� retornado.
   	 Esse retorno como "Massa Pastel Grande" � justamente para facilitar na hora de descontar os itens do estoque.
     */
    public String retornaOpcaoPastel() {
        //Verifica qual op��o foi selecionada na ComboBox e retorna se for verdadeiro
        if (validacao == true) {
            if (tamanhoCombobox.getSelectedItem().equals("Grande")) {
                return "Massa Pastel - Grande";
            } else if (tamanhoCombobox.getSelectedItem().equals("M�dio")) {
                return "Massa Pastel - M�dio";
            } else if (tamanhoCombobox.getSelectedItem().equals("Pequeno")) {
                return "Massa Pastel - Pequeno";
            } else {
                return "null";
            }
        } else
            return "null";
    }
    /**
     * M�todo que retorna a quantidade de past�is digitada no cadastro pelo usu�rio
     * @return quantidade de Pastel
     */
    public int retornaQuantidadePastel() {
        //Retorna quantidade de past�is
        return Integer.parseInt(quantPastelText.getText());
    }
    /**
     * M�todo que retorna a quantidade de bebida digitada no cadastro pelo usu�rio
     * @return quantidade de Bebida
     */
    public int retornaQuantidadeBebida(){
        return Integer.parseInt(quantBebidaText.getText());
    }
    /**
     * Retorna qual sabor do pastel o usu�rio escolheu. S�o duas etapas:
     * (1) Primeiramente � conferido se o pedido foi cadastrado
     * (2) Posteriormente, � conferido se a op��o que o usu�rio escolheu confere com as op��es j� setadas anteriormente. Se conferir,
     retorna o nome do recheio em quest�o, sen�o, retorna a String null.
     * @return String null se todas as condi��es forem falsas. Se for verdadeiro retorna o proposto.
     */
    public String retornaSaboresPastel() {
        //Verifica se algum pedido foi cadastrado, se sim, verifica qual foi o sabor escolhido e o retorna
        if (validacao == true) {
            if (opcoesPastelCombobox.getSelectedItem().equals("Calabresa")) {
                return "Recheio - Calabresa";
            } else if (opcoesPastelCombobox.getSelectedItem().equals("Carne")) {
                return "Recheio - Carne";
            } else if (opcoesPastelCombobox.getSelectedItem().equals("Carne com queijo")) {
                return "Recheio - Carne com Queijo";
            } else if (opcoesPastelCombobox.getSelectedItem().equals("Queijo")) {
                return "Recheio - Queijo";
            } else if (opcoesPastelCombobox.getSelectedItem().equals("Presunto")) {
                return "Recheio - Presunto";
            } else if (opcoesPastelCombobox.getSelectedItem().equals("Frango com Catupiry")) {
                return "Recheio - Frango com Catupiry";
            } else if (opcoesPastelCombobox.getSelectedItem().equals("Palmito")) {
                return "Recheio - Palmito";
            } else {
                return "Null";
            }
        } else
            return "null";
    }
    /**
     * Retorna qual bebida o usu�rio escolheu. S�o duas etapas:
     * (1) Primeiramente � conferido se o pedido foi cadastrado
     * (2) Posteriormente, � conferido se a op��o que o usu�rio escolheu confere com as op��es j� setadas anteriormente. Se conferir,
     retorna o nome da bebida em quest�o, sen�o, retorna a String null.
     * @return String "null" caso todas as verifica��es forem falsas, se for verdadeiro retorna as demais op��es.
     */
    public String retornaBebida(){
        if(validacao==true){
            if(bebidaCombobox.getSelectedItem().equals("Caldo de Cana") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")) {
                return "Caldo de Cana - Pequeno";
            }else if ((bebidaCombobox.getSelectedItem().equals("Caldo de Cana") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio"))) {
                return "Caldo de Cana - M�dio";
            }else if ((bebidaCombobox.getSelectedItem().equals("Caldo de Cana") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande"))){
                return "Caldo de Cana - Grande";
            }else if(bebidaCombobox.getSelectedItem().equals("Refrigerante") && tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
                return  "Refrigerante - Pequeno";
            }else if(bebidaCombobox.getSelectedItem().equals("Refrigerante") && tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
                return  "Refrigerante - M�dio";
            }else if(bebidaCombobox.getSelectedItem().equals("Refrigerante") && tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
                return "Refrigerante - Grande";
            }else if(bebidaCombobox.getSelectedItem().equals("Suco")&& tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
                return "Suco - Pequeno";
            }else if(bebidaCombobox.getSelectedItem().equals("Suco")&& tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
                return "Suco - M�dio";
            }else if(bebidaCombobox.getSelectedItem().equals("Suco")&& tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
                return "Suco - Grande";
            }else if(bebidaCombobox.getSelectedItem().equals("�gua")&& tamanhoBebidaCombobox.getSelectedItem().equals("Pequeno")){
                return "�gua - Pequeno";
            }else if(bebidaCombobox.getSelectedItem().equals("�gua")&& tamanhoBebidaCombobox.getSelectedItem().equals("M�dio")){
                return "�gua - M�dio";
            }else if(bebidaCombobox.getSelectedItem().equals("�gua")&& tamanhoBebidaCombobox.getSelectedItem().equals("Grande")){
                return "�gua - Grande";
            }else
                return "null";
        }else
            return "null";
    }
    /**
     * M�todo atrelado ao m�todo de buscar o pedido. Tem como objetivo verificar se o n�mero do pedido digitado pelo usu�rio no campo de texto 
     � igual a algum cadastrado pelo sistema, se sim abrir� uma tela, igualmente a tela de editar o pedido, para que o usu�rio consiga editar 
     se for de seu desejo. Al�m disso, � implementado o bot�o para salvar os dados que o usu�rio modificou.
     */
    public void infoPedido(){
        JFrame infoPedidoFrame = new JFrame();
        JPanel infoPanel = new JPanel();
        boolean achei = false;
        for (int i = 0; i<pedidos.size(); i++) {
            if (pesquisaPedido.getText().equals(String.valueOf(pedidos.get(i).getNumPedido()))) {
                achei = true;
                numMesaText.setText(Integer.toString(pedidos.get(i).getNumMesa()));
                opcoesPastelCombobox.setSelectedItem(pedidos.get(i).getSaborPastel());
                tamanhoCombobox.setSelectedItem(pedidos.get(i).getTamPastel());
                quantPastelText.setText(Integer.toString(pedidos.get(i).getQuantPasteis()));
                bebidaCombobox.setSelectedItem(pedidos.get(i).getTipoBebida());
                quantBebidaText.setText(Integer.toString(pedidos.get(i).getQuantBebida()));
                tamanhoBebidaCombobox.setSelectedItem(pedidos.get(i).getTamanhoBebida());
                saborBebidaText.setText(pedidos.get(i).getSaborBebida());
                placeComponentsCadastro(infoPanel);
                JButton salvarInfoEdicao = new JButton("Salvar");
                salvarInfoEdicao.setBounds(120,290,90,30);

                infoPedidoFrame.add(salvarInfoEdicao);
                infoPedidoFrame.add(infoPanel);
                infoPedidoFrame.setSize(350,380);
                infoPedidoFrame.setLocationRelativeTo(null);
                infoPedidoFrame.setVisible(true);

                int finalI = i;
                salvarInfoEdicao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pedidos.get(finalI).setNumMesa(Integer.parseInt(numMesaText.getText()));
                        pedidos.get(finalI).setSaborPastel(Objects.requireNonNull(opcoesPastelCombobox.getSelectedItem()).toString());
                        pedidos.get(finalI).setTamPastel(Objects.requireNonNull(tamanhoCombobox.getSelectedItem()).toString());
                        pedidos.get(finalI).setQuantPasteis(Integer.parseInt(quantPastelText.getText()));
                        pedidos.get(finalI).setTipoBebida(Objects.requireNonNull(bebidaCombobox.getSelectedItem()).toString());
                        pedidos.get(finalI).setQuantBebida(Integer.parseInt(quantBebidaText.getText()));
                        pedidos.get(finalI).setTamanhoBebida(Objects.requireNonNull(tamanhoBebidaCombobox.getSelectedItem()).toString());
                        pedidos.get(finalI).setSaborBebida(saborBebidaText.getText());
                        JOptionPane.showMessageDialog(null, "Edi��o realizada com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        }
        if (!achei)
            JOptionPane.showMessageDialog(null, "Erro ao encontrar pedido!", null, JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Realiza o cadastramento de um dado de um pedido aleatorio.
     */
    public void cadastroPedidoAleatorio(){
        Pedido pedidoAleatorio = new Pedido();

        pedidoAleatorio.setNumMesa(5);
        pedidoAleatorio.setDataHora("18:59 8/10/2021");
        pedidoAleatorio.setSaborPastel("Calabresa");
        pedidoAleatorio.setTamPastel("Grande");
        pedidoAleatorio.setQuantPasteis(2);
        pedidoAleatorio.setTipoBebida("Refrigerante");
        pedidoAleatorio.setQuantBebida(2);
        pedidoAleatorio.setTamanhoBebida("M�dio");
        pedidoAleatorio.setSaborBebida("Coca-Cola");
        pedidoAleatorio.setNumPedido(1);
        pedidos.add(pedidoAleatorio);
    }
}
