package dao;

import modelo.SocioModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO {

    // Registrar socio
    public boolean registrarSocio(SocioModelo socio) {
        String sql = "INSERT INTO socios (nombre, correo, direccion, telefono) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getCorreo());
            ps.setString(3, socio.getDireccion());
            ps.setString(4, socio.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar socio: " + e.getMessage());
            return false;
        }
    }

    // Modificar socio
    public boolean modificarSocio(SocioModelo socio) {
        String sql = "UPDATE socios SET direccion = ?, telefono = ? WHERE nombre = ? AND correo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, socio.getDireccion());
            ps.setString(2, socio.getTelefono());
            ps.setString(3, socio.getNombre());
            ps.setString(4, socio.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar socio: " + e.getMessage());
            return false;
        }
    }

    // Eliminar socio
    public boolean eliminarSocio(String nombre, String correo) {
        String sql = "DELETE FROM socios WHERE nombre = ? AND correo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar socio: " + e.getMessage());
            return false;
        }
    }

    // Buscar socio por ID
    public SocioModelo buscarPorId(int id) {
        String sql = "SELECT * FROM socios WHERE id_socio = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SocioModelo(
                        rs.getInt("id_socio"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar socio por ID: " + e.getMessage());
        }
        return null;
    }

    // Buscar socio por nombre y correo
    public SocioModelo buscarPorNombreYCorreo(String nombre, String correo) {
        String sql = "SELECT * FROM socios WHERE nombre = ? AND correo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SocioModelo(
                        rs.getInt("id_socio"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar socio por nombre y correo: " + e.getMessage());
        }
        return null;
    }

    // Obtener todos los socios
    public List<SocioModelo> obtenerTodos() {
        String sql = "SELECT * FROM socios";
        List<SocioModelo> socios = new ArrayList<>();
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                socios.add(new SocioModelo(
                        rs.getInt("id_socio"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los socios: " + e.getMessage());
        }
        return socios;
    }
}
