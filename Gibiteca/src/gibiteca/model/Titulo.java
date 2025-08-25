<<<<<<< HEAD
package gibiteca.model;

/**
 * Representa o titulo de uma HQ.
 * EX: "Turma da Mônica".
 */
public class Titulo {
    private long id;
    private String nome;
    private String editora;
    private String autor;

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
    public String toString(){
        return nome;
        // Faz com que o nome do título apareça na lista da GUI.
    }
}
=======
package gibiteca.model;

/**
 * Representa o titulo de uma HQ.
 * EX: "Turma da Mônica".
 */
public class Titulo {
    private long id;
    private String nome;
    private String editora;
    private String autor;

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
    public String toString(){
        return nome;
        // Faz com que o nome do título apareça na lista da GUI.
    }
}
>>>>>>> f2bcc2d (tentando resolver problemas da sub pasta)
