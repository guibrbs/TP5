package modelo;
/**
 * Classe abstrata Estoque, serve como herança para Bebida e Pastel
 * @author Guilherme Barbosa Ferreira
 *
 */
public abstract class Estoque {
    protected int quantProduto;
    protected String nomeProduto;
    protected float valorProduto;
    protected Pedido pedido;

    public Estoque(int quantProduto, String nomeProduto, float valorProduto, Pedido pedido) {
        this.quantProduto = quantProduto;
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.pedido = pedido;
    }
    
    public Estoque() {
    }

    public int getQuantProduto() {
        return quantProduto;
    }

    public void setQuantProduto(int quantProduto) {
        this.quantProduto = quantProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public float getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(float valorProduto) {
        this.valorProduto = valorProduto;
    }
}
