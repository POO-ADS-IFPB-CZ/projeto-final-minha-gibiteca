package gibiteca.model;

public class Personagem {

    private long id;
    private String nome;
    private String primeiraAparicao;

    public Personagem(long id, String nome, String primeiraAparicao) {
        this.id = id;
        this.nome = nome;
        this.primeiraAparicao = primeiraAparicao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrimeiraAparicao() {
        return primeiraAparicao;
    }

    public void setPrimeiraAparicao(String primeiraAparicao) {
        this.primeiraAparicao = primeiraAparicao;
    }
}