package modelo;
/**
 * Classe que envolve o cadastramento no Estoque e do Pedido
 * @author Guilherme Barbosa Ferreira
 *
 */
public class Bebida extends Estoque {
    private String tipoBebida;
    private String saborBebida;
    private String tamanhoBebida;

    public Bebida(){
    }

    public String getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(String tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public String getSaborBebida() {
        return saborBebida;
    }

    public void setSaborBebida(String saborBebida) {
        this.saborBebida = saborBebida;
    }

    public String getTamanhoBebida() {
        return tamanhoBebida;
    }

    public void setTamanhoBebida(String tamanhoBebida) {
        this.tamanhoBebida = tamanhoBebida;
    }
}
