import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Reportes extends JFrame {
    private Connection connection;

    public Reportes(Connection connection) {
        this.connection = connection;

        // Configuración de la ventana
        setTitle("Generación de Reportes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con botones para diferentes reportes
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton reporteInscripcionesButton = new JButton("Reporte de Inscripciones");
        JButton reporteAlumnosButton = new JButton("Reporte de Alumnos");
        JButton reporteCursosButton = new JButton("Reporte de Cursos");

        // Acciones de los botones
        reporteInscripcionesButton.addActionListener(e -> generarReporteInscripciones());
        reporteAlumnosButton.addActionListener(e -> generarReporteAlumnos());
        reporteCursosButton.addActionListener(e -> generarReporteCursos());

        panel.add(reporteInscripcionesButton);
        panel.add(reporteAlumnosButton);
        panel.add(reporteCursosButton);

        add(panel, BorderLayout.CENTER);
    }

    private void generarReporteInscripciones() {
        mostrarTabla("SELECT u.name AS Alumno, c.name AS Curso, g.schedule AS Horario, g.room AS Salon " +
                        "FROM enrollments e " +
                        "JOIN users u ON e.user_id = u.id " +
                        "JOIN groups g ON e.group_id = g.id " +
                        "JOIN courses c ON g.course_id = c.id",
                new String[]{"Alumno", "Curso", "Horario", "Salón"});
    }

    private void generarReporteAlumnos() {
        mostrarTabla("SELECT id, name, email FROM users WHERE role = 'alumno'",
                new String[]{"ID", "Nombre", "Email"});
    }

    private void generarReporteCursos() {
        mostrarTabla("SELECT id, name, description, cost FROM courses",
                new String[]{"ID", "Nombre", "Descripción", "Costo"});
    }

    private void mostrarTabla(String query, String[] columnNames) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            while (rs.next()) {
                Object[] row = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame("Reporte");
            frame.add(scrollPane);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

