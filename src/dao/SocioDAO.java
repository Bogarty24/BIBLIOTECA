package dao;

import modelo.SocioModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO {

    public boolean agregarSocio(SocioModelo socio) {
        String sql = "INSERT INTO socios (nombre, direccion, telefono, correo) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getDireccion());
            ps.setString(3, socio.getTelefono());
            ps.setString(4, socio.getCorreo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean existeSocio(int idSocio) {
        String sql = "SELECT COUNT(*) FROM socios WHERE id_socio = ?";
        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Retorna true si existe el socio
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

    public SocioModelo buscarSocioPorId(int idSocio) {
        String sql = "SELECT * FROM socios WHERE id_socio = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SocioModelo(
                    rs.getInt("id_socio"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modificarSocio(SocioModelo socio) {
        String sql = "UPDATE socios SET nombre = ?, direccion = ?, telefono = ?, correo = ? WHERE id_socio = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getDireccion());
            ps.setString(3, socio.getTelefono());
            ps.setString(4, socio.getCorreo());
            ps.setInt(5, socio.getIdSocio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarSocio(int idSocio) {
        String sql = "DELETE FROM socios WHERE id_socio = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SocioModelo> obtenerSocios() {
        List<SocioModelo> socios = new ArrayList<>();
        String sql = "SELECT * FROM socios";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                socios.add(new SocioModelo(
                    rs.getInt("id_socio"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return socios;
    }
}
