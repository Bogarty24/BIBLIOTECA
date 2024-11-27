package dao;

import modelo.PrestamoModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrestamoDAO {

    // Registrar préstamo
    public boolean registrarPrestamo(PrestamoModelo prestamo) {
        String sql = "INSERT INTO prestamos (id_libro, id_socio, fecha_prestamo) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, prestamo.getIdLibro());
            ps.setInt(2, prestamo.getIdSocio());
            ps.setString(3, prestamo.getFechaPrestamo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar préstamo: " + e.getMessage());
            return false;
        }
    }

    // Actualizar fecha de devolución
    public boolean devolverPrestamo(int idLibro, int idSocio, String fechaDevolucion) {
        String sql = "UPDATE prestamos SET fecha_devolucion = ? WHERE id_libro = ? AND id_socio = ? AND fecha_devolucion IS NULL";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, fechaDevolucion);
            ps.setInt(2, idLibro);
            ps.setInt(3, idSocio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al devolver préstamo: " + e.getMessage());
            return false;
        }
    }

    // Obtener fecha de préstamo
    public String obtenerFechaPrestamo(int idLibro, int idSocio) {
        String sql = "SELECT fecha_prestamo FROM prestamos WHERE id_libro = ? AND id_socio = ? AND fecha_devolucion IS NULL";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idLibro);
            ps.setInt(2, idSocio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("fecha_prestamo");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener fecha de préstamo: " + e.getMessage());
        }
        return null;
    }

    public boolean existeSocio(int idSocio) {
        String sql = "SELECT COUNT(*) AS total FROM socios WHERE id_socio = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia del socio: " + e.getMessage());
        }
        return false;
    }

    public boolean existeLibro(int idLibro) {
        String sql = "SELECT COUNT(*) AS total FROM libros WHERE id_libro = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia del libro: " + e.getMessage());
        }
        return false;
    }

}
