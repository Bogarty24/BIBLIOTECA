package modelo;

public class PagoMultaModelo {
    private int idRecibo;
    private int idSocio;
    private double monto;
    private String estado;

    public PagoMultaModelo(int idRecibo, int idSocio, double monto, String estado) {
        this.idRecibo = idRecibo;
        this.idSocio = idSocio;
        this.monto = monto;
        this.estado = estado;
    }

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
}
