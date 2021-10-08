package viewCRUD;

import modelo.Funcionario;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 * Essa classe � destina para o CRUD de funcion�rio.
 * Nela est� contido as intera��es a partir do swing. Dividida em m�todos que inserem a interface e que executam o CRUD.
 * Obt�m uma lista no meio com os nomes dos funcion�rios, e uma op��o de busca a partir do nome. S� � poss�vel cadastrar um CPF por funcion�rio.
 * @author Guilherme Barbosa Ferreira
 * @version 1.0 (Out 2021)
 */
public class TelaFunc implements ActionListener {
    private JFrame janela=new JFrame("Janela Funcion�rios");
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton editar = new JButton("Editar");
    private JButton deletar = new JButton("Deletar");
    private JButton refresh = new JButton("Atualizar");
    private JButton salvar = new JButton("Salvar");
    private JButton salvarEdicao = new JButton("Salvar");
    private JTextField nomeTxt = new JTextField("Nome do Funcion�rio", 30);
    private JTextField rgTxt = new JTextField("RG do Funcion�rio", 30);
    private JTextField cpfTxt = new JTextField("CPF do Funcion�rio", 30);
    private JTextField telTxt = new JTextField("Telefone do Funcion�rio", 30);
    private JTextField funcTxt = new JTextField("Fun��o do Funcion�rio", 30);
    private JTextField salarioTxt = new JTextField("Sal�rio do Funcion�rio", 30);
    private JTextField pesquisaFunc = new JTextField(30);
    private JLabel tituloLabel = new JLabel("Cadastro de Funcion�rios");
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private String[] funcCadastrados = new String[50];
    private JList<String> funcionariosCadastrados=new JList<>(funcCadastrados);
    private boolean verificaExistencia = false;

    /**
     *Insere as interfaces iniciais, al�m de uma lista no meio da janela, inicialmente a lista n�o cont�m nada, pois conforme h� a adi��o
     de elementos na ArrayList, a lista vai se atualizar clicando no bot�o de atualizar. Os bot�es de editar e deletar ser�o somente ativados
     quando algum funcion�rio for cadastrado.
     */
    public void inserirEditar(){
       cadastroAleatorio();
       janela.setSize(500,400);

       JPanel panelList = new JPanel();
       JPanel panelButtons = new JPanel();
       JPanel panelText = new JPanel();
       JScrollPane scrollPane = new JScrollPane(panelList);
       JLabel pesquisarLabel = new JLabel("Pesquisar:");
       scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
       scrollPane.setPreferredSize(new Dimension(300,315));

       pesquisarLabel.setFont(new Font("Arial", Font.PLAIN, 14));
       pesquisaFunc.setPreferredSize(new Dimension(300,25));
       pesquisaFunc.setBorder(new LineBorder(Color.BLACK, 1, true));

       for (int i = 0; i<funcionarios.size(); i++) {
           funcCadastrados[i] = funcionarios.get(i).getNomeFunc();
       }

       funcionariosCadastrados.setFont(new Font("Arial", Font.PLAIN, 16));
       funcionariosCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

       panelText.add(pesquisarLabel);
       panelText.add(pesquisaFunc);
       panelList.add(funcionariosCadastrados);
       panelButtons.add(cadastrar);
       panelButtons.add(editar);
       panelButtons.add(deletar);
       panelButtons.add(refresh);
       janela.add(panelText, BorderLayout.PAGE_START);
       janela.add(panelButtons, BorderLayout.PAGE_END);
       janela.add(scrollPane, BorderLayout.CENTER);
       janela.setVisible(true);
       janela.setLocationRelativeTo(null);

       cadastrar.addActionListener(this);
       editar.addActionListener(this);
       deletar.addActionListener(this);
       refresh.addActionListener(this);
       pesquisaFunc.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
               infoFuncionario();
           }
       });

       if (funcionarios.size()>0){
           editar.setEnabled(true);
           deletar.setEnabled(true);
           pesquisaFunc.setEnabled(true);
       }else{
           editar.setEnabled(false);
           deletar.setEnabled(false);
           pesquisaFunc.setEnabled(false);
       }

    }
    /**
     * Verifica se o funcion�rio pesquisado est� cadastrado. Se sim, aparecer� uma tela igual a de cadastro com as informa��es do mesmo.
     Se n�o, aparecer� uma mensagem de erro.
     */
    public void infoFuncionario (){
        JFrame telaInfo = new JFrame();
        JPanel panelInfo = new JPanel();
        boolean achei = false;
        for (int v = 0; v<funcionarios.size(); v++) {
            if (pesquisaFunc.getText().equals(funcionarios.get(v).getNomeFunc())) {
                achei = true;
                nomeTxt.setText(funcionarios.get(v).getNomeFunc());
                rgTxt.setText(funcionarios.get(v).getRgFunc());
                cpfTxt.setText(funcionarios.get(v).getCpfFunc());
                telTxt.setText(funcionarios.get(v).getTelefoneFunc());
                funcTxt.setText(funcionarios.get(v).getFuncaoFunc());
                salarioTxt.setText(funcionarios.get(v).getSalarioFunc());
                tituloLabel.setText("Funcion�rio encontrado");
                tituloLabel.setBounds(150,5,250,30);
                placeComponentsCadastro(panelInfo);
                JButton salvarInfoEdicao = new JButton("Salvar");
                salvarInfoEdicao.setBounds(150,260,90,30);

                telaInfo.add(salvarInfoEdicao);
                telaInfo.add(panelInfo);
                telaInfo.setSize(400,350);
                telaInfo.setLocationRelativeTo(null);
                telaInfo.setVisible(true);

                int finalV = v;
                salvarInfoEdicao.addActionListener(e -> {
                    for (Funcionario funcionario : funcionarios) {
                        if (cpfTxt.getText().equals(funcionario.getCpfFunc()))
                            verificaExistencia = true;
                    }
                    if (!verificaExistencia) {
                        funcionarios.get(finalV).setNomeFunc(nomeTxt.getText());
                        funcionarios.get(finalV).setRgFunc(rgTxt.getText());
                        funcionarios.get(finalV).setCpfFunc(cpfTxt.getText());
                        funcionarios.get(finalV).setTelefoneFunc(telTxt.getText());
                        funcionarios.get(finalV).setFuncaoFunc(funcTxt.getText());
                        funcionarios.get(finalV).setSalarioFunc(salarioTxt.getText());

                        JOptionPane.showMessageDialog(null, "Edi��o realizada com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "ERRO - CPF j� existente", "Cadastro", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        }
        if (!achei)
            JOptionPane.showMessageDialog(null, "Erro ao encontrar funcion�rio!", null, JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Uma nova janela com a interface swing
     */
    public void cadastroFuncionarios(){
        JFrame janelaCadastroFuncionarios = new JFrame();
        JPanel painelCadastro = new JPanel();

        salvar.setBounds(150,260, 90,30);
        painelCadastro.add(salvar);
        janelaCadastroFuncionarios.add(painelCadastro);
        placeComponentsCadastro(painelCadastro);

        janelaCadastroFuncionarios.setSize(400,350);
        janelaCadastroFuncionarios.setLocationRelativeTo(null);
        janelaCadastroFuncionarios.setVisible(true);

        salvar.addActionListener(this::salvarActionPerformed);
    }
    /**
     * Complemento do m�todo cadastroFuncionarios, esse m�todo � respons�vel por inserir na janela praticamente todas as interfaces 
     que o usu�rio utiliza.
     * @param painelCadastro, � passado o painel como par�metro para os dados do m�todo serem inseridos na jframe.
     */
    public void placeComponentsCadastro(JPanel painelCadastro){
        painelCadastro.setLayout(null);

        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setBounds(75,5,250,30);

        JLabel nome = new JLabel("Nome: ");
        nome.setFont(new Font("Arial", Font.PLAIN, 15));
        nome.setBounds(60,45,90,30);

        JLabel rg = new JLabel("RG: ");
        rg.setFont(new Font("Arial", Font.PLAIN, 15));
        rg.setBounds(60, 80,90,30);

        JLabel cpf = new JLabel("CPF: ");
        cpf.setFont(new Font("Arial", Font.PLAIN, 15));
        cpf.setBounds(60,115,90,30);

        JLabel telefoneFunc = new JLabel("Telefone: ");
        telefoneFunc.setFont(new Font("Arial", Font.PLAIN, 15));
        telefoneFunc.setBounds(60,150,90,30);

        JLabel funcaoFunc = new JLabel("Fun��o: ");
        funcaoFunc.setFont(new Font("Arial", Font.PLAIN, 15));
        funcaoFunc.setBounds(60,185,90,30);

        JLabel salarioFunc = new JLabel("S�lario: ");
        salarioFunc.setFont(new Font("Arial", Font.PLAIN, 15));
        salarioFunc.setBounds(60,220,90,30);

        nomeTxt.setForeground(Color.GRAY);
        nomeTxt.setBounds(140, 45, 190,25);

        rgTxt.setForeground(Color.GRAY);
        rgTxt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (rgTxt.getText().length()>6)
                    e.consume();
            }
        });
        rgTxt.setBounds(140,80,190,25);

        cpfTxt.setForeground(Color.GRAY);
        cpfTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (cpfTxt.getText().length()>10)
                    e.consume();
            }
        });
        cpfTxt.setBounds(140,115,190,25);

        telTxt.setForeground(Color.GRAY);
        telTxt.setBounds(140,150,190,25);
        telTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (telTxt.getText().length()>11)
                    e.consume();
            }
        });

        funcTxt.setForeground(Color.GRAY);
        funcTxt.setBounds(140,185,190,25);
        salarioTxt.setForeground(Color.GRAY);
        salarioTxt.setBounds(140,220,190,25);

        painelCadastro.add(tituloLabel);
        painelCadastro.add(nome);
        painelCadastro.add(nomeTxt);
        painelCadastro.add(rg);
        painelCadastro.add(rgTxt);
        painelCadastro.add(cpf);
        painelCadastro.add(cpfTxt);
        painelCadastro.add(telefoneFunc);
        painelCadastro.add(telTxt);
        painelCadastro.add(funcaoFunc);
        painelCadastro.add(funcTxt);
        painelCadastro.add(salarioFunc);
        painelCadastro.add(salarioTxt);
    }
    /**
     * Cria uma nova janela para edi��o do funcion�rio em quest�o. Ao abrir a janela, os dados do funcion�rio estar� contido nos campos de texto.
     */
    public void editarFuncionario(){
        JFrame telaEditar = new JFrame();
        JPanel panelEditar = new JPanel();

        nomeTxt.setText(funcionarios.get(funcionariosCadastrados.getSelectedIndex()).getNomeFunc());
        rgTxt.setText(funcionarios.get(funcionariosCadastrados.getSelectedIndex()).getRgFunc());
        cpfTxt.setText(funcionarios.get(funcionariosCadastrados.getSelectedIndex()).getCpfFunc());
        telTxt.setText(funcionarios.get(funcionariosCadastrados.getSelectedIndex()).getTelefoneFunc());
        funcTxt.setText(funcionarios.get(funcionariosCadastrados.getSelectedIndex()).getFuncaoFunc());
        salarioTxt.setText(funcionarios.get(funcionariosCadastrados.getSelectedIndex()).getSalarioFunc());
        placeComponentsCadastro(panelEditar);

        salvarEdicao.setBounds(150,260,90,30);

        telaEditar.add(salvarEdicao);
        telaEditar.add(panelEditar);
        telaEditar.setSize(400,350);
        telaEditar.setLocationRelativeTo(null);
        telaEditar.setVisible(true);

        salvarEdicao.addActionListener(this::salvarEdicaoActionPerfomed);
    }
    /**
     * Deleta o funcion�rio selecionado
     */
    public void deletarFuncionario(){
        funcionarios.remove(funcionariosCadastrados.getSelectedIndex());
    }
    
    /**
     * Verifica qual op��o o usu�rio clicou a partir dos bot�es, e com isso, abre inst�ncias e m�todos referentes.
     */
    public void actionPerformed(ActionEvent e) {
        Object choice = e.getSource();
        if(choice==cadastrar){
            new TelaFunc().cadastroFuncionarios();
        }
        if(choice==editar){
            editarFuncionario();
        }
        if(choice==deletar){
            deletarFuncionario();
        }
        if(choice==refresh){
            janela.dispose();
            new TelaFunc().inserirEditar();
        }
    }
    /**
     * Verifica se existe algum funcion�rio com aquele CPF inserido no campo de texto. 
     * Se n�o existir, � verificado se o CPF existe, se existir o cadastro � realizado, sen�o, aparece mensagem para inserir um CPF v�lido.
     * Se j� existir algum CPF igual, uma mensagem de erro aparece informando que j� existe um CPF similar.
     * @param e, realiza a a��o.
     */
    public void salvarActionPerformed(ActionEvent e){
    	Funcionario f = new Funcionario();

        for (Funcionario funcionario : funcionarios) {
            if (cpfTxt.getText().equals(funcionario.getCpfFunc()))
                verificaExistencia = true;
        }

        if (!verificaExistencia){
            if (validaCPF(cpfTxt.getText())) {
                f.setNomeFunc(nomeTxt.getText());
                f.setRgFunc(rgTxt.getText());
                f.setCpfFunc(cpfTxt.getText());
                f.setTelefoneFunc(telTxt.getText());
                f.setFuncaoFunc(funcTxt.getText());
                f.setSalarioFunc(salarioTxt.getText());

                funcionarios.add(f);
                JOptionPane.showMessageDialog(null, "Funcion�rio cadastrado!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Digite um CPF v�lido", "Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "ERRO - CPF j� existente", "Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Verifica se existe o funcion�rio com aquele CPF.
     * Verifica se o CPF est� como o desejado, se sim realiza a edi��o dentro da ArrayList.
     * @param e, realiza a a��o.
     */
    public void salvarEdicaoActionPerfomed (ActionEvent e){
        for (Funcionario funcionario : funcionarios) {
            if (cpfTxt.getText().equals(funcionario.getCpfFunc()))
                verificaExistencia = true;
        }
        if (!verificaExistencia) {
        	if(validaCPF(cpfTxt.getText())) {
	            funcionarios.get(funcionariosCadastrados.getSelectedIndex()).setNomeFunc(nomeTxt.getText());
	            funcionarios.get(funcionariosCadastrados.getSelectedIndex()).setRgFunc(rgTxt.getText());
	            funcionarios.get(funcionariosCadastrados.getSelectedIndex()).setCpfFunc(cpfTxt.getText());
	            funcionarios.get(funcionariosCadastrados.getSelectedIndex()).setTelefoneFunc(telTxt.getText());
	            funcionarios.get(funcionariosCadastrados.getSelectedIndex()).setFuncaoFunc(funcTxt.getText());
	            funcionarios.get(funcionariosCadastrados.getSelectedIndex()).setSalarioFunc(salarioTxt.getText());
	            JOptionPane.showMessageDialog(null, "Edi��o cadastrada!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        	}else{
                JOptionPane.showMessageDialog(null, "Digite um CPF v�lido", "Cadastro", JOptionPane.ERROR_MESSAGE);
        	}
        }else{
        	JOptionPane.showMessageDialog(null, "ERRO - CPF j� existente", "Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Verifica se o CPF digitado est� no padr�o esperado.
     * @return retorna verdadeiro se atender todos os requisitos, sen�o retorna falso.
     * @param cpf, passa o cpf inserido no campo de texto por par�metro para String cpf.
     */
    public boolean validaCPF(String cpf){
        char dig10, dig11;
        int sm, r, num, peso;
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("33333333333") ||
        		cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") ||
        		cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") ||
        		cpf.length()!=11)
            return false;

        try{
            sm=0;
            peso=10;
            for(int i = 0; i<9; i++){
                num = (int)(cpf.charAt(i)-48);
                sm = sm + (num*peso);
                peso = peso - 1;
            }
            r = 11 - (sm%11);
            if ((r==10) || (r==11)){
                dig10='0';
            }else{
                dig10 = (char)(r+48);
            }
            sm=0;
            peso=11;
            for(int i = 0; i<10; i++){
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char)(r + 48);
            if((dig10==cpf.charAt(9))&&(dig11==cpf.charAt(10))){
                return true;
            }else
                return false;
        }catch (InputMismatchException error){
            return false;
        }
    }
    /**
     * Realiza o cadastramento de dois funcion�rios aleat�rios.
     */
    public void cadastroAleatorio(){
        Funcionario aleatorio = new Funcionario();
        Funcionario aleatorio2 = new Funcionario();
        aleatorio.setNomeFunc("Janice Marmou Sardinha");
        aleatorio.setRgFunc("3223197");
        aleatorio.setCpfFunc("05693818195");
        aleatorio.setTelefoneFunc("61991199619");
        aleatorio.setFuncaoFunc("Atendente");
        aleatorio.setSalarioFunc("3000.00");

        aleatorio2.setNomeFunc("Flor Tigre Lira");
        aleatorio2.setRgFunc("2977269");
        aleatorio2.setCpfFunc("68635438779");
        aleatorio2.setTelefoneFunc("63600884298");
        aleatorio2.setFuncaoFunc("Atendente");
        aleatorio2.setSalarioFunc("3000.00");
        funcionarios.add(aleatorio);
        funcionarios.add(aleatorio2);
    }
}