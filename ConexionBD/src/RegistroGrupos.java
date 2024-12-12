import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistroGrupos extends JFrame {
    private Connection connection;

    public RegistroGrupos(Connection connection) {
        this.connection = connection;
        setTitle("Registro de Grupos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField cursoIdField = new JTextField();
        JTextField horarioField = new JTextField();
        JTextField salonField = new JTextField();

        Object[] mensaje = {
                "ID del Curso:", cursoIdField,
                "Horario:", horarioField,
                "Salón:", salonField
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Registrar Grupo", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String sql = "INSERT INTO groups (course_id, schedule, room) VALUES (?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(cursoIdField.getText()));
                pstmt.setString(2, horarioField.getText());
                pstmt.setString(3, salonField.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Grupo registrado exitosamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }
}
