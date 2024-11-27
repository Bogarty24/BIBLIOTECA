package utils;

import javax.swing.JFrame;
import javax.swing.Timer;

public class VentanaUtils {

    public static void abrirVentanaConEfecto(JFrame ventana) {
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true); // Hacer visible antes de animar la opacidad

        // Verificar que el sistema soporta opacidad
        if (ventana.getGraphicsConfiguration().getDevice().isWindowTranslucencySupported(java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
            ventana.setOpacity(0f); // Inicialmente transparente

            // Animar la opacidad
            Timer timer = new Timer(10, e -> {
                float opacity = ventana.getOpacity();
                if (opacity < 1f) {
                    ventana.setOpacity(opacity + 0.05f);
                } else {
                    ((Timer) e.getSource()).stop(); // Detener animación
                }
            });
            timer.start();
        } else {
            System.err.println("Translucency is not supported on this system.");
        }
    }

}
