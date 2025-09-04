package exer02;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private char status;

    public Livro() {
        this.id = -1;
        this.titulo = "";
        this.autor = "";
        this.status = '*'; // pode ser 'D' (disponível) ou 'E' (emprestado)
    }
    
    public Livro(int id, String titulo, String autor, char status) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", status=" + status + "]";
    }
}
