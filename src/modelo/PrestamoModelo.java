package modelo;

public class PrestamoModelo {

    private int idPrestamo;
    private int idLibro;
    private int idSocio;
    private String fechaPrestamo;
    private String fechaDevolucion;

    // Constructor vacío
    public PrestamoModelo() {}

    // Constructor completo
    public PrestamoModelo(int idPrestamo, int idLibro, int idSocio, String fechaPrestamo, String fechaDevolucion) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idSocio = idSocio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Constructor sin ID de préstamo
    public PrestamoModelo(int idLibro, int idSocio, String fechaPrestamo, String fechaDevolucion) {
        this.idLibro = idLibro;
        this.idSocio = idSocio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y Setters
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
