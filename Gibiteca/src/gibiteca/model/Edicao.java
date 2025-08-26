package gibiteca.model;

public class Edicao {

    private long id;
    private long idTitulo; //Chave que liga esta edição ao Título
    private int numero;
    private String tituloHistoria;
    private int anoPublicacao;
    private String estadoConservacao;

    public Edicao(long id, long idTitulo, int numero, String tituloHistoria, int anoPublicacao, String estadoConservacao) {
        this.id = id;
        this.idTitulo = idTitulo;
        this.numero = numero;
        this.tituloHistoria = tituloHistoria;
        this.anoPublicacao = anoPublicacao;
        this.estadoConservacao = estadoConservacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdTitulo() {
        return idTitulo;
    }

    public void setIdTitulo(long idTitulo) {
        this.idTitulo = idTitulo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTituloHistoria() {
        return tituloHistoria;
    }

    public void setTituloHistoria(String tituloHistoria) {
        this.tituloHistoria = tituloHistoria;
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
}