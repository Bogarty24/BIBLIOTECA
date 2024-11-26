package dao;

import modelo.PrestamoModelo;
import java.sql.*;

public class PrestamoDAO {
    public boolean agregarPrestamo(PrestamoModelo prestamo) {
        String sql = "INSERT INTO prestamos (id_libro, id_socio, fecha_prestamo) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, prestamo.getIdLibro());
            ps.setInt(2, prestamo.getIdSocio());
            ps.setString(3, prestamo.getFechaPrestamo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PrestamoModelo buscarPrestamoPorId(int idPrestamo) {
    String sql = "SELECT * FROM prestamos WHERE id_prestamo = ?";
    try (Connection conexion = ConexionDB.getConnection()) {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idPrestamo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new PrestamoModelo(
                rs.getInt("id_prestamo"),
                rs.getInt("id_libro"),
                rs.getInt("id_socio"),
                rs.getString("fecha_prestamo"),
                rs.getString("fecha_devolucion")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    public boolean devolverPrestamo(int idPrestamo) {
        String sql = "UPDATE prestamos SET fecha_devolucion = NOW() WHERE id_prestamo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idPrestamo);
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
