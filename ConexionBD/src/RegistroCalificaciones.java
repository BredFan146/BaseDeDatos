import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistroCalificaciones extends JFrame {
    private Connection connection;

    public RegistroCalificaciones(Connection connection) {
        this.connection = connection;
        setTitle("Registro de Calificaciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField inscripcionIdField = new JTextField();
        JTextField calificacionField = new JTextField();

        Object[] mensaje = {
                "ID de Inscripción:", inscripcionIdField,
                "Calificación:", calificacionField
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Registrar Calificación", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String sql = "INSERT INTO grades (enrollment_id, grade) VALUES (?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(inscripcionIdField.getText()));
                pstmt.setDouble(2, Double.parseDouble(calificacionField.getText()));
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Calificación registrada exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }
}

