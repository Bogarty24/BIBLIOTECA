package modelo;

public class SocioModelo {

    private int idSocio; // ID único, generado automáticamente por la base de datos
    private String nombre;
    private String correo;
    private String direccion;
    private String telefono;

    // Constructor vacío
    public SocioModelo() {
    }

    // Constructor completo (incluye ID)
    public SocioModelo(int idSocio, String nombre, String correo, String direccion, String telefono) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Constructor sin ID (para registrar nuevos socios)
    public SocioModelo(String nombre, String correo, String direccion, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "SocioModelo{"
                + "idSocio=" + idSocio
                + ", nombre='" + nombre + '\''
                + ", correo='" + correo + '\''
                + ", direccion='" + direccion + '\''
                + ", telefono='" + telefono + '\''
                + '}';
    }
}
