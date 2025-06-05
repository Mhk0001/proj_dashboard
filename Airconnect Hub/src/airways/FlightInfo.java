package airways;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame {

    public FlightInfo() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();

        try {
            Connection con = conn.getConnection(); // Use your MySQL connection class
            String query = "SELECT * FROM flight";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs)); // Populate JTable

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading flight data.");
        }

        JScrollPane jsp = new JScrollPane(table); // Add table to scroll pane
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}
