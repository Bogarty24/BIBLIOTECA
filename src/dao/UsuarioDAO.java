
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.UsuarioModelo;

public class UsuarioDAO {

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Retorna true si se encuentra un registro
        } catch (SQLException e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
            return false;
        }
    }
    public boolean registrarUsuario(UsuarioModelo usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, tipo_usuario, email, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getTipoUsuario());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
    public String verificarCredenciales(String usuario, String contraseñaHasheada) {
        String sql = "SELECT tipo_usuario FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseñaHasheada);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("tipo_usuario"); // Devuelve el rol del usuario
            }
        } catch (Exception e) {
            System.err.println("Error al verificar credenciales: " + e.getMessage());
        }
        return null; // Credenciales inválidas
    }
}
