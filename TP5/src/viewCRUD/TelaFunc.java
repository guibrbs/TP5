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
 * Essa classe é destina para o CRUD de funcionário.
 * Nela está contido as interações a partir do swing. Dividida em métodos que inserem a interface e que executam o CRUD.
 * Obtém uma lista no meio com os nomes dos funcionários, e uma opção de busca a partir do nome. Só é possível cadastrar um CPF por funcionário.
 * @author Guilherme Barbosa Ferreira
 * @version 1.0 (Out 2021)
 */
public class TelaFunc implements ActionListener {
    private JFrame janela=new JFrame("Janela Funcionários");
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton editar = new JButton("Editar");
    private JButton deletar = new JButton("Deletar");
    private JButton refresh = new JButton("Atualizar");
    private JButton salvar = new JButton("Salvar");
    private JButton salvarEdicao = new JButton("Salvar");
    private JTextField nomeTxt = new JTextField("Nome do Funcionário", 30);
    private JTextField rgTxt = new JTextField("RG do Funcionário", 30);
    private JTextField cpfTxt = new JTextField("CPF do Funcionário", 30);
    private JTextField telTxt = new JTextField("Telefone do Funcionário", 30);
    private JTextField funcTxt = new JTextField("Função do Funcionário", 30);
    private JTextField salarioTxt = new JTextField("Salário do Funcionário", 30);
    private JTextField pesquisaFunc = new JTextField(30);
    private JLabel tituloLabel = new JLabel("Cadastro de Funcionários");
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private String[] funcCadastrados = new String[50];
    private JList<String> funcionariosCadastrados=new JList<>(funcCadastrados);
    private boolean verificaExistencia = false;

    /**
     *Insere as interfaces iniciais, além de uma lista no meio da janela, inicialmente a lista não contém nada, pois conforme há a adição
     de elementos na ArrayList, a lista vai se atualizar clicando no botão de atualizar. Os botões de editar e deletar serão somente ativados
     quando algum funcionário for cadastrado.
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
     * Verifica se o funcionário pesquisado está cadastrado. Se sim, aparecerá uma tela igual a de cadastro com as informações do mesmo.
     Se não, aparecerá uma mensagem de erro.
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
                tituloLabel.setText("Funcionário encontrado");
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

                        JOptionPane.showMessageDialog(null, "Edição realizada com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "ERRO - CPF já existente", "Cadastro", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        }
        if (!achei)
            JOptionPane.showMessageDialog(null, "Erro ao encontrar funcionário!", null, JOptionPane.ERROR_MESSAGE);
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
     * Complemento do método cadastroFuncionarios, esse método é responsável por inserir na janela praticamente todas as interfaces 
     que o usuário utiliza.
     * @param painelCadastro, é passado o painel como parãmetro para os dados do método serem inseridos na jframe.
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

        JLabel funcaoFunc = new JLabel("Função: ");
        funcaoFunc.setFont(new Font("Arial", Font.PLAIN, 15));
        funcaoFunc.setBounds(60,185,90,30);

        JLabel salarioFunc = new JLabel("Sálario: ");
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
     * Cria uma nova janela para edição do funcionário em questão. Ao abrir a janela, os dados do funcionário estará contido nos campos de texto.
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
     * Deleta o funcionário selecionado
     */
    public void deletarFuncionario(){
        funcionarios.remove(funcionariosCadastrados.getSelectedIndex());
    }
    
    /**
     * Verifica qual opção o usuário clicou a partir dos botões, e com isso, abre instâncias e métodos referentes.
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
     * Verifica se existe algum funcionário com aquele CPF inserido no campo de texto. 
     * Se não existir, é verificado se o CPF existe, se existir o cadastro é realizado, senão, aparece mensagem para inserir um CPF válido.
     * Se já existir algum CPF igual, uma mensagem de erro aparece informando que já existe um CPF similar.
     * @param e, realiza a ação.
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
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Digite um CPF válido", "Cadastro", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "ERRO - CPF já existente", "Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Verifica se existe o funcionário com aquele CPF.
     * Verifica se o CPF está como o desejado, se sim realiza a edição dentro da ArrayList.
     * @param e, realiza a ação.
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
	            JOptionPane.showMessageDialog(null, "Edição cadastrada!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        	}else{
                JOptionPane.showMessageDialog(null, "Digite um CPF válido", "Cadastro", JOptionPane.ERROR_MESSAGE);
        	}
        }else{
        	JOptionPane.showMessageDialog(null, "ERRO - CPF já existente", "Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Verifica se o CPF digitado está no padrão esperado.
     * @return retorna verdadeiro se atender todos os requisitos, senão retorna falso.
     * @param cpf, passa o cpf inserido no campo de texto por parãmetro para String cpf.
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
     * Realiza o cadastramento de dois funcionários aleatórios.
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