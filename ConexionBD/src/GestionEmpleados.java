import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class GestionEmpleados extends JFrame {
    private Connection connection;

    public GestionEmpleados(Connection connection) {
        this.connection = connection;
        setTitle("Gestión de Empleados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Definir columnas
        String[] columnNames = {"ID", "Nombre", "Email", "Rol", "Sueldo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "SELECT id, name, email, role, salary FROM users WHERE role IN ('empleado', 'maestro')";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getDouble("salary")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}

