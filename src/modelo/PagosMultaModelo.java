package modelo;

public class PagosMultaModelo {
    private int idRecibo;
    private int idSocio;
    private double monto;
    private String estado;
    private String fechaPago;
    private double montoAdicional;

    public PagosMultaModelo(int idRecibo, int idSocio, double monto, String estado, String fechaPago, double montoAdicional) {
        this.idRecibo = idRecibo;
        this.idSocio = idSocio;
        this.monto = monto;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.montoAdicional = montoAdicional;
    }

    // Getters y Setters...

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMontoAdicional() {
        return montoAdicional;
    }

    public void setMontoAdicional(double montoAdicional) {
        this.montoAdicional = montoAdicional;
    }
}
