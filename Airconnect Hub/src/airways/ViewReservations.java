package airways;

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.sql.*;

public class ViewReservations extends JFrame {

    JTable table;

    public ViewReservations() {
        setTitle("All Reservations");

        // Set layout of the main content pane to null
        setLayout(null);
        setSize(1200, 700);
        setLocation(200, 100);

        // ===== Load background image and set it as JLabel =====
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("airways/icons/MF.jpg"));
        Image img = backgroundIcon.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(img));
        background.setBounds(0, 0, 1200, 700);
        background.setLayout(null);
        setContentPane(background); // Set the background as the main content pane

        // ===== Heading =====
        JLabel heading = new JLabel("📋 All Flight Reservations");
        heading.setBounds(400, 20, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);
        background.add(heading);

        // ===== Table and ScrollPane =====
        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 80, 1100, 500);
        background.add(sp);

        // ===== Load data =====
        loadReservationData();

        setVisible(true);
    }

    private void loadReservationData() {
        try (Connection con = conn.getConnection()) {
            String query = "SELECT * FROM reservation";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠ Failed to load reservations: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewReservations();
    }
}
