/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airways;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class CancelFlight extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, flight;

    public CancelFlight() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random random = new Random();

        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airways/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);

        JLabel lblpnr = new JLabel("PNR Number");
        lblpnr.setBounds(60, 80, 150, 25);
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpnr);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);

        JLabel lblcancel = new JLabel("Cancellation No");
        lblcancel.setBounds(60, 180, 150, 25);
        lblcancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcancel);

        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);

        JLabel lblflightc = new JLabel("Flight Code");
        lblflightc.setBounds(60, 230, 150, 25);
        lblflightc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblflightc);

        lblfcode = new JLabel();
        lblfcode.setBounds(220, 230, 150, 25);
        add(lblfcode);

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 280, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);

        lbldateoftravel = new JLabel();
        lbldateoftravel.setBounds(220, 280, 150, 25);
        add(lbldateoftravel);

        flight = new JButton("Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(220, 330, 120, 25);
        flight.addActionListener(this);
        add(flight);

        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();

        if (ae.getSource() == fetchButton) {
            try (Connection con = conn.getConnection()) {
                String query = "SELECT * FROM reservation WHERE PNR = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, pnr);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    lblfcode.setText(rs.getString("f_code"));
                    lbldateoftravel.setText(rs.getString("travel_date"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                }

                rs.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String name = tfname.getText();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String date = lbldateoftravel.getText();

            try (Connection con = conn.getConnection()) {
                con.setAutoCommit(false); // Begin transaction

                try {
                    // Insert into cancel table
                    String insertQuery = "INSERT INTO cancel (pnr, name, cancellationno, flightcode, ddate) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                    insertStmt.setString(1, pnr);
                    insertStmt.setString(2, name);
                    insertStmt.setString(3, cancelno);
                    insertStmt.setString(4, fcode);
                    insertStmt.setString(5, date);
                    insertStmt.executeUpdate();

                    // Delete from reservation table
                    String deleteQuery = "DELETE FROM reservation WHERE PNR = ?";
                    PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
                    deleteStmt.setString(1, pnr);
                    deleteStmt.executeUpdate();
                    
                    
                    String updateSeatsQuery = "UPDATE flight SET seats_available = seats_available + 1 WHERE f_code = ?";
PreparedStatement updateSeatsStmt = con.prepareStatement(updateSeatsQuery);
updateSeatsStmt.setString(1, fcode); // use flightcode from the cancelled ticket
updateSeatsStmt.executeUpdate();
updateSeatsStmt.close();


                    con.commit();
                    JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                    setVisible(false);
                } catch (SQLException e) {
                    con.rollback(); // Rollback on failure
                    throw e;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new CancelFlight();
    }
}

