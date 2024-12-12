import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class ModuloAdministrador extends JFrame {

    public ModuloAdministrador() {
        setTitle("Módulo del Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Configurar el layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mensaje de bienvenida
        JLabel tituloLabel = new JLabel("Bienvenido Administrador", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Ocupa todo el ancho
        panel.add(tituloLabel, gbc);

        // Botones de acciones
        gbc.gridwidth = 1; // Resetear a una columna
        gbc.gridy = 1;

        JButton generarReportesButton = new JButton("Generar Reportes");
        gbc.gridx = 0;
        panel.add(generarReportesButton, gbc);

        JButton registrarProfesoresButton = new JButton("Registrar Profesores/Empleados");
        gbc.gridx = 1;
        panel.add(registrarProfesoresButton, gbc);

        JButton actualizarCursosButton = new JButton("Actualizar Cursos");
        gbc.gridx = 2;
        panel.add(actualizarCursosButton, gbc);

        gbc.gridy = 2;

        JButton agregarCursoButton = new JButton("Agregar Nuevo Curso");
        gbc.gridx = 0;
        panel.add(agregarCursoButton, gbc);

        JButton gestionEmpleadosButton = new JButton("Gestión de Empleados");
        gbc.gridx = 1;
        panel.add(gestionEmpleadosButton, gbc);

        JButton gestionCursosButton = new JButton("Gestión de Cursos");
        gbc.gridx = 2;
        panel.add(gestionCursosButton, gbc);

        gbc.gridy = 3;

        JButton gestionAlumnosButton = new JButton("Gestión de Alumnos");
        gbc.gridx = 0;
        panel.add(gestionAlumnosButton, gbc);

        JButton generarOtroReporteButton = new JButton("Generar Reportes");
        gbc.gridx = 1;
        panel.add(generarOtroReporteButton, gbc);

        // Añadir el panel principal a la ventana
        add(panel);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ModuloAdministrador adminModule = new ModuloAdministrador();
            adminModule.setVisible(true);
        });
    }
}

