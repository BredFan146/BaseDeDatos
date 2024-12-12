import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportesAdmin extends JFrame {
    private Connection connection;

    public ReportesAdmin(Connection connection) {
        this.connection = connection;

        setTitle("Generación de Reportes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botones para los diferentes reportes
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton reporteUsuariosButton = new JButton("Reporte de Usuarios");
        JButton reporteCursosButton = new JButton("Reporte de Cursos");
        JButton reporteInscripcionesButton = new JButton("Reporte de Inscripciones");

        reporteUsuariosButton.addActionListener(e -> mostrarReporte("SELECT id, name, email, role FROM users",
                new String[]{"ID", "Nombre", "Email", "Rol"}));

        reporteCursosButton.addActionListener(e -> mostrarReporte("SELECT id, name, description, cost FROM courses",
                new String[]{"ID", "Nombre", "Descripción", "Costo"}));

        reporteInscripcionesButton.addActionListener(e -> mostrarReporte(
                "SELECT u.name AS Alumno, c.name AS Curso, g.schedule AS Horario, g.room AS Salon " +
                        "FROM enrollments e " +
                        "JOIN users u ON e.user_id = u.id " +
                        "JOIN groups g ON e.group_id = g.id " +
                        "JOIN courses c ON g.course_id = c.id",
                new String[]{"Alumno", "Curso", "Horario", "Salón"}));

        panel.add(reporteUsuariosButton);
        panel.add(reporteCursosButton);
        panel.add(reporteInscripcionesButton);

        add(panel);
    }

    private void mostrarReporte(String query, String[] columnNames) {
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

