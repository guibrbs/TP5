package modelo;

/**
 * Classe Funcionário, serve para armazenar os dados de todos os funcionários a partir da ArrayList
 * @author Guilherme Barbosa Ferreira
 *
 */

public class Funcionario {
    private String nomeFunc;
    private String rgFunc;
    private String cpfFunc;
    private String telefoneFunc;
    private String funcaoFunc;
    private String salarioFunc;

    public Funcionario() {
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public String getRgFunc() {
        return rgFunc;
    }

    public void setRgFunc(String rgFunc) {
        this.rgFunc = rgFunc;
    }

    public String getCpfFunc() {
        return cpfFunc;
    }
    
    public void setCpfFunc(String cpfFunc) {
        this.cpfFunc = cpfFunc;
    }

    public String getTelefoneFunc() {
        return telefoneFunc;
    }

    public void setTelefoneFunc(String telefoneFunc) {
        this.telefoneFunc = telefoneFunc;
    }

    public String getFuncaoFunc() {
        return funcaoFunc;
    }

    public void setFuncaoFunc(String funcaoFunc) {
        this.funcaoFunc = funcaoFunc;
    }

    public String getSalarioFunc() {
        return salarioFunc;
    }

    public void setSalarioFunc(String salarioFunc) {
        this.salarioFunc = salarioFunc;
    }
}
