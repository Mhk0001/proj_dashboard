///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package airways;
//
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//import net.proteanit.sql.DbUtils;
//
//public class Checkpayment extends JFrame implements ActionListener {
//    JTextField textfield;
//    JTable table;
//    JLabel label, FlightCode, Price, JourneyD, JourneyT, UName, lables;
//
//    public static void main(String args[]) {
//        new Checkpayment();
//    }
//
//    Checkpayment() {
//        initialize();
//    }
//
//    private void initialize() {
//        setTitle("Payment Details");
//        getContentPane().setBackground(Color.white);
//        setSize(900, 600);
//        setLayout(null);
//
//        JLabel Fcode = new JLabel("Flight Code");
//        Fcode.setFont(new Font("Arial", Font.BOLD, 18));
//        Fcode.setBounds(200, 160, 150, 26);
//        add(Fcode);
//
//        textfield = new JTextField();
//        textfield.setBounds(320, 160, 150, 26);
//        textfield.setFont(new Font("Arial", Font.BOLD, 16));
//        add(textfield);
//
//        table = new JTable();
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(50, 300, 800, 200);
//        add(scrollPane);
//
//        JButton show = new JButton("Show");
//        show.setFont(new Font("Arial", Font.BOLD, 16));
//        show.setBackground(Color.black);
//        show.setForeground(Color.white);
//        show.setBounds(500, 160, 150, 24);
//        show.addActionListener(this);
//        add(show);
//
//        label = new JLabel("Check Payment Details");
//        label.setForeground(Color.BLACK);
//        label.setFont(new Font("Arial", Font.BOLD, 33));
//        label.setBounds(250, 40, 400, 40);
//        add(label);
//
//        FlightCode = new JLabel(" ");
//        FlightCode.setFont(new Font("Arial", Font.BOLD, 16));
//        FlightCode.setBounds(100, 270, 100, 26);
//        add(FlightCode);
//
//        Price = new JLabel(" ");
//        Price.setFont(new Font("Arial", Font.BOLD, 16));
//        Price.setBounds(220, 270, 100, 26);
//        add(Price);
//
//        JourneyD = new JLabel(" ");
//        JourneyD.setFont(new Font("Arial", Font.BOLD, 16));
//        JourneyD.setBounds(340, 270, 120, 26);
//        add(JourneyD);
//
//        JourneyT = new JLabel(" ");
//        JourneyT.setFont(new Font("Arial", Font.BOLD, 16));
//        JourneyT.setBounds(470, 270, 120, 26);
//        add(JourneyT);
//
//        UName = new JLabel(" ");
//        UName.setFont(new Font("Arial", Font.BOLD, 16));
//        UName.setBounds(600, 270, 100, 26);
//        add(UName);
//
//        lables = new JLabel(" ");
//        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("airways/icons/Payment.png"));
//        Image i2 = ic.getImage().getScaledInstance(1550, 800, Image.SCALE_SMOOTH);
//        lables.setIcon(new ImageIcon(i2));
//        lables.setBounds(0, 0, 900, 600);
//        add(lables);
//
//        setLocation(200, 50);
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//        String fc = textfield.getText().trim();
//        if (fc.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please enter flight code");
//            return;
//        }
//
//        try (Connection con = conn.getConnection()) {
//            String query = "SELECT TICKET, Price, ddate, timing, name FROM reservation WHERE flightcode = ?";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, fc);
//            ResultSet rs = pst.executeQuery();
//
//            if (!rs.isBeforeFirst()) {
//                JOptionPane.showMessageDialog(null, "No Information Found");
//                return;
//            }
//
//            table.setModel(DbUtils.resultSetToTableModel(rs));
//
//            // Optional: set labels if you want to show first row separately
//            if (rs.next()) {
//                FlightCode.setText(fc);
//                Price.setText(rs.getString("Price"));
//                JourneyD.setText(rs.getString("ddate"));
//                JourneyT.setText(rs.getString("timing"));
//                UName.setText(rs.getString("name"));
//            }
//
//            rs.close();
//            pst.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
package airways;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Payment extends JFrame implements ActionListener {
    JTextField textfield, tfpnr;
    JTable table;
    JLabel label, FlightCode, Price, JourneyD, JourneyT, UName, lables;
    JComboBox<String> paymentMethod;
    JButton show, payBtn;

    public static void main(String args[]) {
        new Payment();
    }

    Payment() {
        initialize();
    }

    private void initialize() {
        setTitle("Payment Details");
        getContentPane().setLayout(null);
        setSize(950, 700);

        JLabel Fcode = new JLabel("Flight Code");
        Fcode.setFont(new Font("Arial", Font.BOLD, 18));
        Fcode.setBounds(200, 160, 150, 26);
        add(Fcode);

        textfield = new JTextField();
        textfield.setBounds(320, 160, 150, 26);
        textfield.setFont(new Font("Arial", Font.BOLD, 16));
        add(textfield);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 300, 820, 200);
        add(scrollPane);

        show = new JButton("Show");
        show.setFont(new Font("Arial", Font.BOLD, 16));
        show.setBackground(Color.black);
        show.setForeground(Color.white);
        show.setBounds(500, 160, 150, 24);
        show.addActionListener(this);
        add(show);

        label = new JLabel("Check Payment Details");
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 33));
        label.setBounds(250, 40, 450, 40);
        add(label);

        FlightCode = new JLabel(" ");
        FlightCode.setFont(new Font("Arial", Font.BOLD, 16));
        FlightCode.setBounds(100, 270, 100, 26);
        add(FlightCode);

        Price = new JLabel(" ");
        Price.setFont(new Font("Arial", Font.BOLD, 16));
        Price.setBounds(220, 270, 100, 26);
        add(Price);

        JourneyD = new JLabel(" ");
        JourneyD.setFont(new Font("Arial", Font.BOLD, 16));
        JourneyD.setBounds(340, 270, 120, 26);
        add(JourneyD);

        JourneyT = new JLabel(" ");
        JourneyT.setFont(new Font("Arial", Font.BOLD, 16));
        JourneyT.setBounds(470, 270, 120, 26);
        add(JourneyT);

        UName = new JLabel(" ");
        UName.setFont(new Font("Arial", Font.BOLD, 16));
        UName.setBounds(600, 270, 100, 26);
        add(UName);

        // PNR input
        JLabel lblpnr = new JLabel("PNR:");
        lblpnr.setBounds(50, 520, 80, 25);
        lblpnr.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblpnr);

        tfpnr = new JTextField();
        tfpnr.setBounds(120, 520, 150, 25);
        add(tfpnr);

        // Payment Method
        JLabel lblmethod = new JLabel("Method:");
        lblmethod.setBounds(300, 520, 80, 25);
        lblmethod.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblmethod);

        String methods[] = {"UPI", "Card", "Net Banking", "Wallet"};
        paymentMethod = new JComboBox<>(methods);
        paymentMethod.setBounds(380, 520, 150, 25);
        add(paymentMethod);

        // Payment button
        payBtn = new JButton("Make Payment");
        payBtn.setBounds(550, 520, 150, 25);
        payBtn.setBackground(Color.GREEN);
        payBtn.setForeground(Color.BLACK);
        payBtn.setFont(new Font("Arial", Font.BOLD, 14));
        add(payBtn);

        payBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                makePayment();
            }
        });

        // Background Image
        lables = new JLabel();
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("airways/icons/airindia.png"));
        Image i2 = ic.getImage().getScaledInstance(1550, 800, Image.SCALE_SMOOTH);
        lables.setIcon(new ImageIcon(i2));
        lables.setBounds(0, 0, 950, 700);
        add(lables);

        setLocation(200, 30);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String fc = textfield.getText().trim();
        if (fc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter flight code");
            return;
        }

        try (Connection con = conn.getConnection()) {
            String query = "SELECT ticket, price, travel_date, time, name FROM reservation WHERE f_code = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, fc);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }

            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Display first row data on labels
            if (rs.next()) {
                FlightCode.setText(fc);
                Price.setText(rs.getString("price"));
                JourneyD.setText(rs.getString("travel_date"));
                JourneyT.setText(rs.getString("time"));
                UName.setText(rs.getString("name"));
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makePayment() {
        String pnr = tfpnr.getText().trim();
        String method = (String) paymentMethod.getSelectedItem();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter PNR to proceed");
            return;
        }

        try (Connection con = conn.getConnection()) {
            // Check if reservation exists
            String fetchQuery = "SELECT price, f_code, name FROM reservation WHERE pnr = ?";
            PreparedStatement fetchStmt = con.prepareStatement(fetchQuery);
            fetchStmt.setString(1, pnr);
            ResultSet rs = fetchStmt.executeQuery();

            if (rs.next()) {
                int price = rs.getInt("price");
                String fc = rs.getString("f_code");
                String name = rs.getString("name");

                // Insert payment
                String insertPayment = "INSERT INTO payment (pnr, flightcode, name, amount, payment_method, status, payment_date) VALUES (?, ?, ?, ?, ?, ?, CURDATE())";
                PreparedStatement insertStmt = con.prepareStatement(insertPayment);
                insertStmt.setString(1, pnr);
                insertStmt.setString(2, fc);
                insertStmt.setString(3, name);
                insertStmt.setInt(4, price);
                insertStmt.setString(5, method);
                insertStmt.setString(6, "Paid");

                insertStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Payment successful for ₹" + price);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid PNR");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

