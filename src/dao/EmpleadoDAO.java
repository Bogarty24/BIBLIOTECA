package dao;

import modelo.EmpleadoModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    // Registrar empleado
    public boolean registrarEmpleado(EmpleadoModelo empleado) {
        String sql = "INSERT INTO empleados (nombre, apellidos, telefono, email, departamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getTelefono());
            ps.setString(4, empleado.getEmail());
            ps.setString(5, empleado.getDepartamento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar empleado: " + e.getMessage());
            return false;
        }
    }

    // Modificar empleado
    public boolean modificarEmpleado(EmpleadoModelo empleado) {
        String sql = "UPDATE empleados SET telefono = ?, email = ?, departamento = ? WHERE nombre = ? AND apellidos = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, empleado.getTelefono());
            ps.setString(2, empleado.getEmail());
            ps.setString(3, empleado.getDepartamento());
            ps.setString(4, empleado.getNombre());
            ps.setString(5, empleado.getApellidos());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar empleado: " + e.getMessage());
            return false;
        }
    }

    // Eliminar empleado
    public boolean eliminarEmpleado(int idTrabajador) {
        String sql = "DELETE FROM empleados WHERE id_trabajador = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idTrabajador);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }

    // Buscar empleado por ID
    public EmpleadoModelo buscarPorId(int idTrabajador) {
        String sql = "SELECT * FROM empleados WHERE id_trabajador = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idTrabajador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EmpleadoModelo(
                        rs.getInt("id_trabajador"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("departamento")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar empleado por ID: " + e.getMessage());
        }
        return null;
    }

    // Buscar empleado por nombre, apellidos y departamento
    public EmpleadoModelo buscarPorNombreApellidosDepto(String nombre, String apellidos, String departamento) {
        String sql = "SELECT * FROM empleados WHERE nombre = ? AND apellidos = ? AND departamento = ?";
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, departamento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EmpleadoModelo(
                        rs.getInt("id_trabajador"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("departamento")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar empleado por nombre, apellidos y departamento: " + e.getMessage());
        }
        return null;
    }

    // Obtener todos los empleados
    public List<EmpleadoModelo> obtenerTodos() {
        String sql = "SELECT * FROM empleados";
        List<EmpleadoModelo> empleados = new ArrayList<>();
        try (Connection conexion = ConexionDB.getConnection()) {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                empleados.add(new EmpleadoModelo(
                        rs.getInt("id_trabajador"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("departamento")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los empleados: " + e.getMessage());
        }
        return empleados;
    }
}
