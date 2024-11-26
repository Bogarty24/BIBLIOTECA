package modelo;

import java.io.Serializable;

public class EmpleadoModelo implements Serializable {
    private int idTrabajador;
    private String nombreTrabajador;
    private String apellidoTrabajador;
    private String telefonoTrabajador;
    private String emailTrabajador;
    private String departamentoTrabajador;

    // Constructor vacío
    public EmpleadoModelo() {
    }

    // Constructor con parámetros

    public EmpleadoModelo(int idTrabajador, String nombreTrabajador, String apellidoTrabajador, String telefonoTrabajador, String emailTrabajador, String departamentoTrabajador) {
        this.idTrabajador = idTrabajador;
        this.nombreTrabajador = nombreTrabajador;
        this.apellidoTrabajador = apellidoTrabajador;
        this.telefonoTrabajador = telefonoTrabajador;
        this.emailTrabajador = emailTrabajador;
        this.departamentoTrabajador = departamentoTrabajador;
    }
    

    // Getters y Setters
    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getApellidoTrabajador() {
        return apellidoTrabajador;
    }

    public void setApellidoTrabajador(String apellidoTrabajador) {
        this.apellidoTrabajador = apellidoTrabajador;
    }

    public String getTelefonoTrabajador() {
        return telefonoTrabajador;
    }

    public void setTelefonoTrabajador(String telefonoTrabajador) {
        this.telefonoTrabajador = telefonoTrabajador;
    }

    public String getEmailTrabajador() {
        return emailTrabajador;
    }

    public void setEmailTrabajador(String emailTrabajador) {
        this.emailTrabajador = emailTrabajador;
    }

    public String getDepartamentoTrabajador() {
        return departamentoTrabajador;
    }

    public void setDepartamentoTrabajador(String departamentoTrabajador) {
        this.departamentoTrabajador = departamentoTrabajador;
    }
}
