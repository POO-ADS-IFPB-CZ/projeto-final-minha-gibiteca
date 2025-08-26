package gibiteca.model;

/**
 * Representa o título (revista/coleção) de quadrinhos.
 */
public class Titulo {
    private long id;
    private String nome;
    private String editora;
    private String autor;

    public Titulo() {}

    public Titulo(long id, String nome, String editora, String autor) {
        this.id = id;
        this.nome = nome;
        this.editora = editora;
        this.autor = autor;
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

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        // Mostra o nome na lista da GUI
        return nome != null ? nome : "";
    }
}
