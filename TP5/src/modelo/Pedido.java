package modelo;
/**
 * Classe Pedido, ser para realizar o cadastro dos pedidos a partir dos getters e setters.
 * @author Guilherme Barbosa Ferreira
 *
 */
public class Pedido {
    private String dataHora;
    private int numMesa;
    private int quantPasteis;
    private int quantBebida;
    private float valorPedido;
    private int numPedido;
    private Pastel tamPastel;
    private Pastel saborPastel;
    private Bebida saborBebida;
    private Bebida tipoBebida;
    private Bebida tamanhoBebida;

    public Pedido() {
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public int getQuantPasteis() {
        return quantPasteis;
    }

    public void setQuantPasteis(int quantPasteis) {
        this.quantPasteis = quantPasteis;
    }

    public int getQuantBebida() {
        return quantBebida;
    }

    public void setQuantBebida(int quantBebida) {
        this.quantBebida = quantBebida;
    }

    public float getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(float valorPedido) {
        this.valorPedido = valorPedido;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public String getTamPastel() {
        return tamPastel.getTamanhoPastel();
    }

    public void setTamPastel(String s) {
        tamPastel = new Pastel();
        tamPastel.setTamanhoPastel(s);
    }

    public String getSaborPastel() {
        return saborPastel.getSaborPastel();
    }

    public void setSaborPastel(String s) {
        saborPastel = new Pastel();
        saborPastel.setSaborPastel(s);
    }

    public String getSaborBebida() {
        return saborBebida.getSaborBebida();
    }

    public void setSaborBebida(String s) {
        saborBebida = new Bebida();
        saborBebida.setSaborBebida(s);
    }

    public String getTipoBebida() {
        return tipoBebida.getTipoBebida();
    }

    public void setTipoBebida(String s) {
        tipoBebida = new Bebida();
        tipoBebida.setTipoBebida(s);
    }

    public String getTamanhoBebida() {
        return tamanhoBebida.getTamanhoBebida();
    }

    public void setTamanhoBebida(String s) {
        tamanhoBebida = new Bebida();
        tamanhoBebida.setTamanhoBebida(s);
    }
}