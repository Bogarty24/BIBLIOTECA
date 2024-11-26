package dao;

import modelo.UsuarioModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean registrarUsuario(UsuarioModelo usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, tipo_usuario, email, telefono) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            // Establecer los valores de cada columna
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());  // Asegúrate de que esté cifrada si es necesario
            ps.setString(3, usuario.getTipoUsuario());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefono());

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Usuario agregado correctamente");
                return true;
            } else {
                System.err.println("No se pudo agregar el usuario");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar el usuario en la base de datos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
