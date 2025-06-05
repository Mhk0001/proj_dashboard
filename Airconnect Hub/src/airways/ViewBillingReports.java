/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airways;



import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class ViewBillingReports extends JFrame implements ActionListener {
    JTable table;
    JButton loadButton;
    JLabel totalRevenueLabel;
    JDateChooser fromDate, toDate;

    public ViewBillingReports() {
        setTitle("Billing Reports");
        setLayout(null);
        setSize(1000, 600);
        setLocation(250, 100);

        JLabel heading = new JLabel("Billing Reports");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(380, 20, 300, 30);
        add(heading);

        JLabel lblFrom = new JLabel("From Date:");
        lblFrom.setBounds(100, 70, 100, 25);
        add(lblFrom);

        fromDate = new JDateChooser();
        fromDate.setBounds(200, 70, 150, 25);
        add(fromDate);

        JLabel lblTo = new JLabel("To Date:");
        lblTo.setBounds(400, 70, 100, 25);
        add(lblTo);

        toDate = new JDateChooser();
        toDate.setBounds(480, 70, 150, 25);
        add(toDate);

        loadButton = new JButton("Load Data");
        loadButton.setBounds(650, 70, 120, 25);
        loadButton.addActionListener(this);
        add(loadButton);

        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 120, 800, 300);
        add(sp);

        totalRevenueLabel = new JLabel("Total Revenue: ₹0");
        totalRevenueLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        totalRevenueLabel.setBounds(100, 440, 300, 30);
        add(totalRevenueLabel);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String from = sdf.format(fromDate.getDate());
        String to = sdf.format(toDate.getDate());

        String query = "SELECT pnr, name, travel_date, price FROM reservation WHERE travel_date BETWEEN ? AND ?";
        String sumQuery = "SELECT SUM(price) AS total FROM reservation WHERE travel_date BETWEEN ? AND ?";

        try (Connection con = conn.getConnection()) {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, from);
            pst.setString(2, to);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            PreparedStatement sumPst = con.prepareStatement(sumQuery);
            sumPst.setString(1, from);
            sumPst.setString(2, to);
            ResultSet sumRs = sumPst.executeQuery();

            if (sumRs.next()) {
                int total = sumRs.getInt("total");
                totalRevenueLabel.setText("Total Revenue: ₹" + total);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewBillingReports();
    }
}


