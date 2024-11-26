package dao;

import java.sql.ResultSet;

import modelo.PagosMultaModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoMultaDAO {

    public boolean agregarMulta(PagosMultaModelo multa) {
        String sql = "INSERT INTO pagos_multas (id_socio, monto, estado, fecha_pago, monto_adicional) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, multa.getIdSocio());
            ps.setDouble(2, multa.getMonto());
            ps.setString(3, multa.getEstado());
            ps.setString(4, multa.getFechaPago());
            ps.setDouble(5, multa.getMontoAdicional());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarMulta(int idRecibo, double montoAdicional) {
        String sql = "UPDATE pagos_multas SET monto_adicional = ? WHERE id_recibo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setDouble(1, montoAdicional);
            ps.setInt(2, idRecibo);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public class PagosMultasDAO {

        public boolean actualizarMulta(int idRecibo, double montoAdicional) {
            String sql = "UPDATE pagos_multas SET monto_adicional = ? WHERE id_recibo = ?";
            try (Connection conexion = ConexionDB.getConnection()) {
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setDouble(1, montoAdicional);
                ps.setInt(2, idRecibo);
                int filasActualizadas = ps.executeUpdate();
                return filasActualizadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public PagosMultaModelo buscarMultaPorId(int idRecibo) {
        String sql = "SELECT * FROM pagos_multas WHERE id_recibo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRecibo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PagosMultaModelo(
                        rs.getInt("id_recibo"),
                        rs.getInt("id_socio"),
                        rs.getDouble("monto"),
                        rs.getString("estado"),
                        rs.getDate("fecha_pago") != null ? rs.getDate("fecha_pago").toString() : null,
                        rs.getDouble("monto_adicional")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarMulta(int idRecibo) {
        String sql = "DELETE FROM pagos_multas WHERE id_recibo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRecibo);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean pagarMulta(int idRecibo) {
        String sql = "UPDATE pagos_multas SET estado = 'Pagado', fecha_pago = NOW() WHERE id_recibo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRecibo);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<PagosMultaModelo> obtenerTodasLasMultas() {
    List<PagosMultaModelo> listaMultas = new ArrayList<>();
    String sql = "SELECT * FROM pagos_multas";
    
    try (Connection conexion = ConexionDB.getConnection();
         PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            PagosMultaModelo multa = new PagosMultaModelo(
                rs.getInt("id_recibo"),
                rs.getInt("id_socio"),
                rs.getDouble("monto"),
                rs.getString("estado"),
                rs.getString("fecha_pago") != null ? rs.getString("fecha_pago") : null,
                rs.getDouble("monto_adicional")
            );
            listaMultas.add(multa);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return listaMultas;
}


}
