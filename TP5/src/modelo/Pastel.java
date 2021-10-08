package modelo;
/**
 * Classe Pastel que serve para o cadastro de pedidos e do estoque
 * @author Guilherme Barbosa Ferreira
 *
 */
public class Pastel extends Estoque{
    private String tamanhoPastel;
    private String saborPastel;

    public Pastel() {
    }

    public String getTamanhoPastel() {
        return tamanhoPastel;
    }

    public void setTamanhoPastel(String tamanhoPastel) {
        this.tamanhoPastel = tamanhoPastel;
    }

    public String getSaborPastel() {
        return saborPastel;
    }

    public void setSaborPastel(String saborPastel) {
        this.saborPastel = saborPastel;
    }
}
