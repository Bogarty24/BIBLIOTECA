package dao;

import modelo.LibroModelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LibroDAO {

    public boolean agregarLibro(LibroModelo libro) {
        String sql = "INSERT INTO libros (titulo, autor, isbn, genero, editorial, provincia) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getGenero());
            ps.setString(5, libro.getEditorial());
            ps.setString(6, libro.getProvincia());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarLibro(LibroModelo libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, genero = ?, editorial = ?, provincia = ? WHERE isbn = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getGenero());
            ps.setString(4, libro.getEditorial());
            ps.setString(5, libro.getProvincia());
            ps.setString(6, libro.getIsbn());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean eliminarLibro(String isbn) {
        String sql = "DELETE FROM libros WHERE isbn = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, isbn);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Si al menos una fila fue afectada, significa que se eliminó el libro.
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LibroModelo buscarLibroPorIsbn(String isbn) {
        String sql = "SELECT * FROM libros WHERE isbn = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Crear y devolver el libro con los datos obtenidos de la consulta
                int idLibro = rs.getInt("id_libro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                String editorial = rs.getString("editorial");
                String provincia = rs.getString("provincia");
                return new LibroModelo(idLibro, titulo, autor, isbn, genero, editorial, provincia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; // Devolver null si no se encuentra el libro
    }

    public List<LibroModelo> obtenerLibros() {
        List<LibroModelo> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Connection conexion = ConexionDB.getConnection()) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                libros.add(new LibroModelo(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("genero"),
                        rs.getString("editorial"),
                        rs.getString("provincia")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public boolean existeLibro(int idLibro) {
        String sql = "SELECT COUNT(*) FROM libros WHERE id_libro = ?";
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Retorna true si existe el libro
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
