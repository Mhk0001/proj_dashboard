//
//package airways;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//import com.toedter.calendar.JDateChooser;
//import java.util.Random;
//import java.text.SimpleDateFormat;
//
//
//public class BookFlight extends JFrame implements ActionListener {
//
//    private static int seatno;
//    private int availableSeats = 0;
//
//
//    private JTextField tfaadhar;
//    private JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode, labeltime, labelprice,labelAvailableSeats;
//    private JButton bookflight, fetchButton, flight;
//    private Choice source, destination;
//    private JDateChooser dcdate;
//
//    public BookFlight(int seatno) {
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//JLabel heading = new JLabel("Book Flight");
//heading.setBounds(420, 18, 500, 35);
//heading.setFont(new Font("Tahoma", Font.BOLD, 34));
//heading.setForeground(Color.BLUE);
//add(heading);
//
//// Aadhar Section
//JLabel lblaadhar = new JLabel("Aadhaar");
//lblaadhar.setBounds(60, 80, 150, 25);
//lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
//add(lblaadhar);
//
//tfaadhar = new JTextField();
//tfaadhar.setBounds(220, 80, 150, 25);
//add(tfaadhar);
//
//fetchButton = new JButton("Fetch User");
//fetchButton.setBounds(380, 80, 120, 25);
//fetchButton.setBackground(Color.BLACK);
//fetchButton.setForeground(Color.WHITE);
//fetchButton.addActionListener(this);
//add(fetchButton);
//
//// Passenger Details
//addLabel("Name", 130); tfname = addValueLabel(130);
//addLabel("Nationality", 180); tfnationality = addValueLabel(180);
//addLabel("Address", 230); tfaddress = addValueLabel(230);
//addLabel("Gender", 280); labelgender = addValueLabel(280);
//
//// Flight Search
//addLabel("Source", 330);
//source = new Choice(); source.setBounds(220, 330, 150, 25); add(source);
//
//addLabel("Destination", 380);
//destination = new Choice(); destination.setBounds(220, 380, 150, 25); add(destination);
//
//flight = new JButton("Fetch Flights");
//flight.setBounds(380, 380, 120, 25);
//flight.setBackground(Color.BLACK); flight.setForeground(Color.WHITE);
//flight.addActionListener(this);
//add(flight);
//
//// Flight Details
//addLabel("Flight Name", 430); labelfname = addValueLabel(430);
//addLabel("Flight Code", 480); labelfcode = addValueLabel(480);
//addLabel("Timings", 530); labeltime = addValueLabel(530);
//addLabel("Price", 580); labelprice = addValueLabel(580);
//addLabel("Seats Available", 630); labelAvailableSeats = addValueLabel(630);
//
//// Travel Date
//addLabel("Date of Travel", 680);
//dcdate = new JDateChooser();
//dcdate.setBounds(220, 680, 150, 25);
//add(dcdate);
//
//// Book Button
//bookflight = new JButton("Book Flight");
//bookflight.setBounds(220, 720, 150, 30);
//bookflight.setBackground(Color.BLACK);
//bookflight.setForeground(Color.WHITE);
//bookflight.addActionListener(this);
//add(bookflight);
//
//// Right Image
//ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airways/icons/details.jpg"));
//Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
//JLabel lblimage = new JLabel(new ImageIcon(i2));
//lblimage.setBounds(550, 120, 500, 400);
//add(lblimage);
//
//// Frame Setup
//setSize(1200, 850);
//setLocation(200, 30);
//setVisible(true);
//
//
//        loadFlightData();
//    }
//
//    private void addLabel(String text, int y) {
//        JLabel lbl = new JLabel(text);
//        lbl.setBounds(60, y, 150, 25);
//        lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        add(lbl);
//    }
//
//    private JLabel addValueLabel(int y) {
//        JLabel lbl = new JLabel();
//        lbl.setBounds(220, y, 150, 25);
//        add(lbl);
//        return lbl;
//    }
//
//    private void loadFlightData() {
//        try (Connection con = conn.getConnection();
//             Statement stmt = con.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT DISTINCT source, destination FROM flight")) {
//
//            while (rs.next()) {
//                source.add(rs.getString("source"));
//                destination.add(rs.getString("destination"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == fetchButton) {
//            fetchUserData(tfaadhar.getText());
//        } else if (ae.getSource() == flight) {
//            fetchFlightData(source.getSelectedItem(), destination.getSelectedItem());
//        } else {
//            bookFlight();
//        }
//    }
//
//    private void fetchUserData(String aadhar) {
//        String query = "SELECT * FROM passenger WHERE aadhar = ?";
//        try (Connection con = conn.getConnection();
//             PreparedStatement ps = con.prepareStatement(query)) {
//
//            ps.setString(1, aadhar);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                tfname.setText(rs.getString("name"));
//                tfnationality.setText(rs.getString("nationality"));
//                tfaddress.setText(rs.getString("address"));
//                labelgender.setText(rs.getString("gender"));
//            } else {
//                JOptionPane.showMessageDialog(null, "User not found!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void fetchFlightData(String src, String dest) {
//        String query = "SELECT * FROM flight WHERE source = ? AND destination = ?";
//        try (Connection con = conn.getConnection();
//             PreparedStatement ps = con.prepareStatement(query)) {
//
//            ps.setString(1, src);
//            ps.setString(2, dest);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                labelfname.setText(rs.getString("f_name"));
//                labelfcode.setText(rs.getString("f_code"));
//                labeltime.setText(rs.getString("time"));
//                labelprice.setText(rs.getString("price"));
//                availableSeats = rs.getInt("seats_available"); // Fetch available seats
//            labelAvailableSeats.setText(String.valueOf(availableSeats));
//            } else {
//                JOptionPane.showMessageDialog(null, "No flights for this route!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    package airways;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.Random;
import java.text.SimpleDateFormat;

public class BookFlight extends JFrame implements ActionListener {
    private static int seatno;
    private int availableSeats = 0;

    private JTextField tfaadhar;
    private JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode, labeltime, labelprice, labelAvailableSeats;
    private JButton bookflight, fetchButton, flight;
    private Choice source, destination;
    private JDateChooser dcdate;

    public BookFlight(int seatno) {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 18, 500, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 34));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblaadhar = new JLabel("Aadhaar");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);
        add(tfaadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.addActionListener(this);
        add(fetchButton);

        addLabel("Name", 130); tfname = addValueLabel(130);
        addLabel("Nationality", 180); tfnationality = addValueLabel(180);
        addLabel("Address", 230); tfaddress = addValueLabel(230);
        addLabel("Gender", 280); labelgender = addValueLabel(280);

        addLabel("Source", 330);
        source = new Choice(); source.setBounds(220, 330, 150, 25); add(source);

        addLabel("Destination", 380);
        destination = new Choice(); destination.setBounds(220, 380, 150, 25); add(destination);

        flight = new JButton("Fetch Flights");
        flight.setBounds(380, 380, 120, 25);
        flight.setBackground(Color.BLACK); flight.setForeground(Color.WHITE);
        flight.addActionListener(this);
        add(flight);

        addLabel("Flight Name", 430); labelfname = addValueLabel(430);
        addLabel("Flight Code", 480); labelfcode = addValueLabel(480);
        addLabel("Timings", 530); labeltime = addValueLabel(530);
        addLabel("Price", 580); labelprice = addValueLabel(580);
        addLabel("Seats Available", 630); labelAvailableSeats = addValueLabel(630);

        addLabel("Date of Travel", 680);
        dcdate = new JDateChooser();
        dcdate.setBounds(220, 680, 150, 25);
        add(dcdate);

        bookflight = new JButton("Book Flight");
        bookflight.setBounds(220, 720, 150, 30);
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.addActionListener(this);
        add(bookflight);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airways/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        JLabel lblimage = new JLabel(new ImageIcon(i2));
        lblimage.setBounds(550, 120, 500, 400);
        add(lblimage);

        setSize(1200, 850);
        setLocation(200, 30);
        setVisible(true);

        loadFlightData();
    }

    private void addLabel(String text, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(60, y, 150, 25);
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbl);
    }

    private JLabel addValueLabel(int y) {
        JLabel lbl = new JLabel();
        lbl.setBounds(220, y, 150, 25);
        add(lbl);
        return lbl;
    }

    private void loadFlightData() {
        try (Connection con = conn.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT source, destination FROM flight")) {

            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            fetchUserData(tfaadhar.getText());
        } else if (ae.getSource() == flight) {
            fetchFlightData(source.getSelectedItem(), destination.getSelectedItem());
        } else {
            bookFlight();
        }
    }

    private void fetchUserData(String aadhar) {
        String query = "SELECT * FROM passenger WHERE aadhar = ?";
        try (Connection con = conn.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, aadhar);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                tfaddress.setText(rs.getString("address"));
                labelgender.setText(rs.getString("gender"));
            } else {
                JOptionPane.showMessageDialog(null, "User not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchFlightData(String src, String dest) {
        String query = "SELECT * FROM flight WHERE source = ? AND destination = ?";
        try (Connection con = conn.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, src);
            ps.setString(2, dest);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                labelfname.setText(rs.getString("f_name"));
                labelfcode.setText(rs.getString("f_code"));
                labeltime.setText(rs.getString("time"));
                labelprice.setText(rs.getString("price"));
                availableSeats = rs.getInt("seats_available");
                labelAvailableSeats.setText(String.valueOf(availableSeats));
            } else {
                JOptionPane.showMessageDialog(null, "No flights for this route!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
private void bookFlight() {
    String aadhar = tfaadhar.getText().trim();
    String name = tfname.getText().trim();
    String nationality = tfnationality.getText().trim();
    String address = tfaddress.getText().trim();
    String gender = labelgender.getText().trim();
    String flightName = labelfname.getText().trim();
    String flightCode = labelfcode.getText().trim();
    String src = source.getSelectedItem();
    String dest = destination.getSelectedItem();
    String timing = labeltime.getText().trim();
    String price = labelprice.getText().trim();
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String travelDate = sdf.format(dcdate.getDate());

    if (aadhar.isEmpty() || name.isEmpty() || nationality.isEmpty() || flightName.isEmpty()
            || flightCode.isEmpty() || src.isEmpty() || dest.isEmpty()
            || timing.isEmpty() || price.isEmpty() || travelDate.isEmpty()) {
        JOptionPane.showMessageDialog(null, "All fields must be filled");
        return;
    }

    if (!aadhar.matches("\\d{12}")) {
        JOptionPane.showMessageDialog(null, "Invalid 12-digit Aadhaar number");
        return;
    }

    if (dcdate.getDate() == null || dcdate.getDate().before(new java.util.Date())) {
        JOptionPane.showMessageDialog(null, "Enter a valid future travel date");
        return;
    }

    if (availableSeats <= 0) {
        JOptionPane.showMessageDialog(null, "No seats available. Please try another flight.");
        return;
    }
    if (!name.matches("[a-zA-Z ]{3,}")) {
            JOptionPane.showMessageDialog(null, "Invalid name. Must be at least 3 letters and contain no digits or special characters.");
            return;
        }

        try (Connection con = conn.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM booking_log WHERE aadhar = ? AND booking_time > NOW() - INTERVAL 1 MINUTE")) {

            ps.setString(1, aadhar);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) >=1) {
                JOptionPane.showMessageDialog(null, "Too many bookings in a short time. Please wait a minute.");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    String pnr = "PNR-" + new Random().nextInt(999999);
    String ticket = "TIC-" + new Random().nextInt(9999);

    String query = "INSERT INTO reservation(pnr, ticket, aadhar, name, nationality, f_name, f_code, source, destination, travel_date, time, price) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String updateSeatsQuery = "UPDATE flight SET seats_available = seats_available - 1 WHERE f_code = ?";

    try (Connection con = conn.getConnection()) {
        con.setAutoCommit(false); // Start transaction

        try (PreparedStatement ps = con.prepareStatement(query);
             PreparedStatement seatStmt = con.prepareStatement(updateSeatsQuery)) {

            ps.setString(1, pnr);
            ps.setString(2, ticket);
            ps.setString(3, aadhar);
            ps.setString(4, name);
            ps.setString(5, nationality);
            ps.setString(6, flightName);
            ps.setString(7, flightCode);
            ps.setString(8, src);
            ps.setString(9, dest);
            ps.setString(10, travelDate);
            ps.setString(11, timing);
            ps.setString(12, price);

            ps.executeUpdate();

            seatStmt.setString(1, flightCode);
            seatStmt.executeUpdate();
String insertLog = "INSERT INTO booking_log (aadhar) VALUES (?)";
try (PreparedStatement psLog = con.prepareStatement(insertLog)) {
    psLog.setString(1, aadhar);
    psLog.executeUpdate();
}

            con.commit();

            JOptionPane.showMessageDialog(null, "Ticket Booked Successfully!\nPNR: " + pnr);
            setVisible(false);

        } catch (SQLException e) {
            con.rollback();
            throw e;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Booking failed. Try again.");
    }
}



    public static void main(String[] args) {
        new BookFlight(seatno);
    }
}
