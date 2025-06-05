package airways;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageFlights extends JFrame implements ActionListener {

    JTextField tfCode, tfName, tfSource, tfDestination, tfTime, tfPrice, tfSeats;
    JButton addBtn, updateBtn, deleteBtn, clearBtn;

    public ManageFlights() {
        setTitle("Manage Flights - Admin Panel");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel(" Manage Flights");
        heading.setBounds(320, 20, 400, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        heading.setForeground(new Color(0, 102, 204));
        add(heading);

        // Labels and text fields
        int labelX = 60, fieldX = 230, width = 220, height = 30;
        int y = 80, gap = 50;

        addLabel("Flight Code", labelX, y); tfCode = addField(fieldX, y); y += gap;
        addLabel("Flight Name", labelX, y); tfName = addField(fieldX, y); y += gap;
        addLabel("Source", labelX, y); tfSource = addField(fieldX, y); y += gap;
        addLabel("Destination", labelX, y); tfDestination = addField(fieldX, y); y += gap;
        addLabel("Time", labelX, y); tfTime = addField(fieldX, y); y += gap;
        addLabel("Price", labelX, y); tfPrice = addField(fieldX, y); y += gap;
        addLabel("Seats Available", labelX, y); tfSeats = addField(fieldX, y); y += gap;

        // Buttons
        addBtn = createButton("Add", 60, y);
        updateBtn = createButton("Update", 150, y);
        deleteBtn = createButton("Delete", 250, y);
        clearBtn = createButton("Clear", 350, y);

        // Right-side image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airways/icons/MF.jpg"));
        Image img = icon.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(550, 100, 400, 350);
        add(imageLabel);

        setSize(1000, 650);
        setLocation(250, 100);
        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(label);
    }

    private JTextField addField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 220, 25);
        add(field);
        return field;
    }

    private JButton createButton(String title, int x, int y) {
        JButton btn = new JButton(title);
        btn.setBounds(x, y, 80, 30);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        add(btn);
        return btn;
    }

    public void actionPerformed(ActionEvent ae) {
        String code = tfCode.getText().trim();
        String name = tfName.getText().trim();
        String source = tfSource.getText().trim();
        String destination = tfDestination.getText().trim();
        String time = tfTime.getText().trim();
        String price = tfPrice.getText().trim();
        String seats = tfSeats.getText().trim();

        try (Connection con = conn.getConnection()) {
            if (ae.getSource() == addBtn) {
                String query = "INSERT INTO flight (f_code, f_name, source, destination, time, price, seats_available) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, code);
                ps.setString(2, name);
                ps.setString(3, source);
                ps.setString(4, destination);
                ps.setString(5, time);
                ps.setDouble(6, Double.parseDouble(price));
                ps.setInt(7, Integer.parseInt(seats));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "✅ Flight Added Successfully");

            } else if (ae.getSource() == updateBtn) {
                String query = "UPDATE flight SET f_name=?, source=?, destination=?, time=?, price=?, seats_available=? WHERE f_code=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, source);
                ps.setString(3, destination);
                ps.setString(4, time);
                ps.setDouble(5, Double.parseDouble(price));
                ps.setInt(6, Integer.parseInt(seats));
                ps.setString(7, code);
                int rows = ps.executeUpdate();
                if (rows > 0) JOptionPane.showMessageDialog(null, "✅ Flight Updated Successfully");
                else JOptionPane.showMessageDialog(null, "❌ Flight Code Not Found");

            } else if (ae.getSource() == deleteBtn) {
                String query = "DELETE FROM flight WHERE f_code = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, code);
                int rows = ps.executeUpdate();
                if (rows > 0) JOptionPane.showMessageDialog(null, "🗑️ Flight Deleted Successfully");
                else JOptionPane.showMessageDialog(null, "❌ Flight Code Not Found");

            } else if (ae.getSource() == clearBtn) {
                tfCode.setText(""); tfName.setText(""); tfSource.setText("");
                tfDestination.setText(""); tfTime.setText(""); tfPrice.setText(""); tfSeats.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "⚠ Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ManageFlights();
    }
}
