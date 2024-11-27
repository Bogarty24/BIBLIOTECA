package modelo;

public class LibroModelo {

    private int idLibro; // ID único del libro, generado automáticamente por la base de datos
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private String editorial;
    private String provincia;

    // Constructor vacío
    public LibroModelo() {}

    // Constructor completo (incluye el ID)
    public LibroModelo(int idLibro, String titulo, String autor, String isbn, String genero, String editorial, String provincia) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.editorial = editorial;
        this.provincia = provincia;
    }

    // Constructor sin ID
    public LibroModelo(String titulo, String autor, String isbn, String genero, String editorial, String provincia) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
        this.editorial = editorial;
        this.provincia = provincia;
    }

    // Constructor con solo título, autor, ISBN y género
    public LibroModelo(String titulo, String autor, String isbn, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.genero = genero;
    }

    // Getters y Setters
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "LibroModelo{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genero='" + genero + '\'' +
                ", editorial='" + editorial + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
