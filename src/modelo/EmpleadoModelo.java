package modelo;

public class EmpleadoModelo {

    private int idTrabajador;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String departamento;

    // Constructor vacío
    public EmpleadoModelo() {}

    // Constructor con todos los atributos (incluye ID)
    public EmpleadoModelo(int idTrabajador, String nombre, String apellidos, String telefono, String email, String departamento) {
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.departamento = departamento;
    }

    // Constructor sin ID (para insertar nuevos empleados)
    public EmpleadoModelo(String nombre, String apellidos, String telefono, String email, String departamento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.departamento = departamento;
    }

    // Getters y Setters
    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "EmpleadoModelo{" +
                "idTrabajador=" + idTrabajador +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
