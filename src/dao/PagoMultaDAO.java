package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import modelo.PagoMultaModelo;

public class PagoMultaDAO {

    // Registrar multa
    public boolean registrarMulta(int idSocio, double montoAdicional) {
        String sql = "INSERT INTO pagos_multas (id_socio, monto, monto_adicional, estado) VALUES (?, 0.00, ?, 'Pendiente') "
                + "ON DUPLICATE KEY UPDATE monto_adicional = monto_adicional + VALUES(monto_adicional)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ps.setDouble(2, montoAdicional); // Aquí se pasa explícitamente monto_adicional
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar multa: " + e.getMessage());
            return false;
        }
    }

    public List<PagoMultaModelo> obtenerTodos() {
        List<PagoMultaModelo> multas = new ArrayList<>();
        String sql = "SELECT * FROM pagos_multas";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                multas.add(new PagoMultaModelo(
                        rs.getInt("id_recibo"),
                        rs.getInt("id_socio"),
                        rs.getDouble("monto"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener multas: " + e.getMessage());
        }
        return multas;
    }

    public boolean actualizarEstado(int idRecibo, String estado) {
        String sql = "UPDATE pagos_multas SET estado = ? WHERE id_recibo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, idRecibo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarMulta(int idRecibo) {
        String sql = "DELETE FROM pagos_multas WHERE id_recibo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRecibo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar multa: " + e.getMessage());
            return false;
        }
    }

    public List<PagoMultaModelo> buscarPorIdRecibo(int idRecibo) {
        List<PagoMultaModelo> multas = new ArrayList<>();
        String sql = "SELECT * FROM pagos_multas WHERE id_recibo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRecibo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                multas.add(new PagoMultaModelo(
                        rs.getInt("id_recibo"),
                        rs.getInt("id_socio"),
                        rs.getDouble("monto"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar multa por ID recibo: " + e.getMessage());
        }
        return multas;
    }

    public List<PagoMultaModelo> buscarPorIdSocio(int idSocio) {
        List<PagoMultaModelo> multas = new ArrayList<>();
        String sql = "SELECT * FROM pagos_multas WHERE id_socio = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                multas.add(new PagoMultaModelo(
                        rs.getInt("id_recibo"),
                        rs.getInt("id_socio"),
                        rs.getDouble("monto"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar multa por ID socio: " + e.getMessage());
        }
        return multas;
    }

}
