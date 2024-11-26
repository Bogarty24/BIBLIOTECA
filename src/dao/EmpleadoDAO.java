package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.EmpleadoModelo;

public class EmpleadoDAO {

    // Metodo para agregar un empleado
   public boolean agregarEmpleado(EmpleadoModelo empleado) {
    String sql = "INSERT INTO trabajadores (nombre_trab, apellidos_trab, telefono_trab, email_trab, departamento_trab) VALUES (?, ?, ?, ?, ?)";
    try (Connection conexion = ConexionDB.getConnection();
         PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setString(1, empleado.getNombreTrabajador());
        ps.setString(2, empleado.getApellidoTrabajador());
        ps.setString(3, empleado.getTelefonoTrabajador());
        ps.setString(4, empleado.getEmailTrabajador());
        ps.setString(5, empleado.getDepartamentoTrabajador());

        System.out.println("Ejecutando consulta SQL: " + ps.toString());
        int filasAfectadas = ps.executeUpdate();
        System.out.println("Filas afectadas: " + filasAfectadas);

        if (filasAfectadas > 0) {
            System.out.println("empleado agreagado correctamente");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al registrar el empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}


    // Metodo para obtener todos los empleados
    public List<EmpleadoModelo> obtenerEmpleados() {
        List<EmpleadoModelo> empleados = new ArrayList<>();
        String sql = "SELECT * FROM trabajadores";
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                EmpleadoModelo empleado = new EmpleadoModelo();
                empleado.setIdTrabajador(rs.getInt("id_trabajador"));
                empleado.setNombreTrabajador(rs.getString("nombre_trab"));
                empleado.setApellidoTrabajador(rs.getString("apellidos_trab"));
                empleado.setTelefonoTrabajador(rs.getString("telefono_trab"));
                empleado.setEmailTrabajador(rs.getString("email_trab"));
                empleado.setDepartamentoTrabajador(rs.getString("departamento_trab"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
    
    

    // Metodo para modificar un empleado
    public boolean modificarEmpleado(EmpleadoModelo empleado) {
        String sql = "UPDATE trabajadores SET nombre_trab = ?, apellidos_trab = ?, telefono_trab = ?, email_trab = ?, departamento_trab = ? WHERE id_trabajador = ?";
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombreTrabajador());
            ps.setString(2, empleado.getApellidoTrabajador());
            ps.setString(3, empleado.getTelefonoTrabajador());
            ps.setString(4, empleado.getEmailTrabajador());
            ps.setString(5, empleado.getDepartamentoTrabajador());
            ps.setInt(6, empleado.getIdTrabajador());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para eliminar un empleado
    public boolean eliminarEmpleado(int idTrabajador) {
        String sql = "DELETE FROM trabajadores WHERE id_trabajador = ?";
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo para buscar un empleado por ID
    public EmpleadoModelo buscarEmpleadoPorId(int idTrabajador) {
        String sql = "SELECT * FROM trabajadores WHERE id_trabajador = ?";
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idTrabajador);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EmpleadoModelo empleado = new EmpleadoModelo();
                    empleado.setIdTrabajador(rs.getInt("id_trabajador"));
                    empleado.setNombreTrabajador(rs.getString("nombre_trab"));
                    empleado.setApellidoTrabajador(rs.getString("apellidos_trab"));
                    empleado.setTelefonoTrabajador(rs.getString("telefono_trab"));
                    empleado.setEmailTrabajador(rs.getString("email_trab"));
                    empleado.setDepartamentoTrabajador(rs.getString("departamento_trab"));
                    return empleado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
