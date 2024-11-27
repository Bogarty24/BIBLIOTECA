package controlador;

import vistas.GestionPrestamosVista;
import dao.PrestamoDAO;
import dao.PagoMultaDAO;
import modelo.PrestamoModelo;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GestionPrestamosController {

    private GestionPrestamosVista prestamosVista;
    private PrestamoDAO prestamoDAO;
    private PagoMultaDAO multaDAO;

    public GestionPrestamosController(GestionPrestamosVista prestamosVista) {
        this.prestamosVista = prestamosVista;
        this.prestamoDAO = new PrestamoDAO();
        this.multaDAO = new PagoMultaDAO();

        // Asociar los botones con acciones
        this.prestamosVista.getBtn_registrar().addActionListener(e -> registrarPrestamo());
        this.prestamosVista.getBtn_devolver().addActionListener(e -> devolverPrestamo());

        this.prestamosVista.setVisible(true);
    }

    private void registrarPrestamo() {
        try {
            int idLibro = Integer.parseInt(prestamosVista.getTxt_id_libro().getText());
            int idSocio = Integer.parseInt(prestamosVista.getTxt_id_socio().getText());
            String fechaInput = prestamosVista.getTxt_fecha_prestamo().getText();

            // Validar que el socio y el libro existan
            if (!prestamoDAO.existeSocio(idSocio)) {
                JOptionPane.showMessageDialog(prestamosVista, "El socio con ID " + idSocio + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!prestamoDAO.existeLibro(idLibro)) {
                JOptionPane.showMessageDialog(prestamosVista, "El libro con ID " + idLibro + " no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir fecha al formato correcto
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaPrestamo = LocalDate.parse(fechaInput, inputFormatter).format(outputFormatter);

            PrestamoModelo prestamo = new PrestamoModelo(idLibro, idSocio, fechaPrestamo, null);
            boolean registrado = prestamoDAO.registrarPrestamo(prestamo);

            if (registrado) {
                JOptionPane.showMessageDialog(prestamosVista, "Préstamo registrado con éxito.", "Registrar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(prestamosVista, "Error al registrar el préstamo.", "Registrar", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(prestamosVista, "Por favor, ingrese valores válidos para ID del libro y del socio.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(prestamosVista, "Formato de fecha incorrecto. Use el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void devolverPrestamo() {
        try {
            // Solicitar ID del socio y libro
            int idSocio = Integer.parseInt(JOptionPane.showInputDialog(prestamosVista, "Ingrese el ID del socio:"));
            int idLibro = Integer.parseInt(JOptionPane.showInputDialog(prestamosVista, "Ingrese el ID del libro:"));

            // Solicitar fecha de devolución
            String fechaInput = JOptionPane.showInputDialog(prestamosVista, "Ingrese la fecha de devolución (formato dd/MM/yyyy):");
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaDevolucion = LocalDate.parse(fechaInput, inputFormatter).format(outputFormatter);

            // Verificar la fecha de préstamo
            String fechaPrestamo = prestamoDAO.obtenerFechaPrestamo(idLibro, idSocio);
            if (fechaPrestamo == null) {
                JOptionPane.showMessageDialog(prestamosVista, "No se encontró el préstamo correspondiente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular multa si es necesario
            LocalDate prestamoDate = LocalDate.parse(fechaPrestamo);
            LocalDate devolucionDate = LocalDate.parse(fechaDevolucion);

            long diasAtraso = ChronoUnit.DAYS.between(prestamoDate.plusDays(30), devolucionDate);

            if (diasAtraso > 0) {
                double multa = diasAtraso * 3.00; // 3 euros por cada día de retraso
                boolean multaRegistrada = multaDAO.registrarMulta(idSocio, multa);

                if (multaRegistrada) {
                    JOptionPane.showMessageDialog(prestamosVista, "Se ha registrado una multa de " + multa + " euros por retraso.", "Devolver", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(prestamosVista, "Error al registrar la multa.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Registrar la devolución
            boolean devuelto = prestamoDAO.devolverPrestamo(idLibro, idSocio, fechaDevolucion);

            if (devuelto) {
                JOptionPane.showMessageDialog(prestamosVista, "Libro devuelto con éxito.", "Devolver", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(prestamosVista, "Error al procesar la devolución.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(prestamosVista, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(prestamosVista, "Formato de fecha incorrecto. Use el formato dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
