import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistroInscripciones extends JFrame {
    private Connection connection;

    public RegistroInscripciones(Connection connection) {
        this.connection = connection;
        setTitle("Registro de Inscripciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField alumnoIdField = new JTextField();
        JTextField grupoIdField = new JTextField();

        Object[] mensaje = {
                "ID del Alumno:", alumnoIdField,
                "ID del Grupo:", grupoIdField
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Registrar Inscripción", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String sql = "INSERT INTO enrollments (user_id, group_id) VALUES (?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(alumnoIdField.getText()));
                pstmt.setInt(2, Integer.parseInt(grupoIdField.getText()));
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Inscripción registrada exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }
}

