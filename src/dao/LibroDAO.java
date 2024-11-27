package dao;

import modelo.LibroModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public boolean agregar(LibroModelo libro) {
        String sql = "INSERT INTO libros (titulo, autor, isbn, genero) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getGenero());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar libro: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id, String titulo) {
        String sql = "DELETE FROM libros WHERE id_libro = ? AND titulo = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, titulo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }

    public boolean modificar(LibroModelo libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, isbn = ?, genero = ? WHERE id_libro = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getIsbn());
            ps.setString(4, libro.getGenero());
            ps.setInt(5, libro.getIdLibro());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar libro: " + e.getMessage());
            return false;
        }
    }

    public LibroModelo buscarPorId(int id) {
        String sql = "SELECT * FROM libros WHERE id_libro = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new LibroModelo(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("genero"),
                        rs.getString("editorial"),
                        rs.getString("provincia")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar libro por ID: " + e.getMessage());
        }
        return null;
    }

    public List<LibroModelo> buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM libros WHERE titulo LIKE ?";
        List<LibroModelo> libros = new ArrayList<>();
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + titulo + "%");
            ResultSet rs = ps.executeQuery();
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
            System.err.println("Error al buscar libros por título: " + e.getMessage());
        }
        return libros;
    }

    public LibroModelo buscarPorIsbn(String isbn) {
        String sql = "SELECT * FROM libros WHERE isbn = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new LibroModelo(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getString("genero"),
                        rs.getString("editorial"),
                        rs.getString("provincia")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar libro por ISBN: " + e.getMessage());
        }
        return null;
    }

    public List<LibroModelo> obtenerTodos() {
        String sql = "SELECT * FROM libros";
        List<LibroModelo> libros = new ArrayList<>();
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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
            System.err.println("Error al obtener todos los libros: " + e.getMessage());
        }
        return libros;
    }

    public boolean modificarPorISBN(LibroModelo libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, genero = ?, editorial = ?, provincia = ? WHERE isbn = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getGenero()); // Incluyendo género
            ps.setString(4, libro.getEditorial());
            ps.setString(5, libro.getProvincia());
            ps.setString(6, libro.getIsbn());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar libro por ISBN: " + e.getMessage());
            return false;
        }
    }

}
