package gibiteca.model;

/**
 * Uma edição específica de um título.
 */
public class Edicao {
    private long id;
    private int numero;
    private int anoPublicacao;
    private String estadoConservacao;

    public Edicao() {}

    public Edicao(long id, int numero, int anoPublicacao, String estadoConservacao) {
        this.id = id;
        this.numero = numero;
        this.anoPublicacao = anoPublicacao;
        this.estadoConservacao = estadoConservacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    @Override
    public String toString() {
        return "Ed. " + numero + " (" + anoPublicacao + ")";
    }
}
