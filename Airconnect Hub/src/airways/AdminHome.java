package airways;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminHome extends JFrame implements ActionListener {

    public AdminHome(String username) {
        setLayout(null);
        setTitle("Admin Panel - " + username);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airways/icons/front.jpg"));
        JLabel background = new JLabel(i1);
        background.setBounds(0, 0, 1600, 800);
        add(background);

        // Heading
        JLabel heading = new JLabel("ADMIN PANEL - AIR INDIA");
        heading.setBounds(500, 40, 1000, 40);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Tahoma", Font.BOLD, 36));
        background.add(heading);

        // Menu Bar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        // Flight Management
        JMenu manage = new JMenu("Manage Flights");
        menubar.add(manage);

        JMenuItem manageFlights = new JMenuItem("Add/Update/Delete Flights");
        manageFlights.addActionListener(this);
        manage.add(manageFlights);

        // Reservation Menu
        JMenu reservation = new JMenu("Reservations");
        menubar.add(reservation);

        JMenuItem viewReservations = new JMenuItem("View All Reservations");
        viewReservations.addActionListener(this);
        reservation.add(viewReservations);

        JMenuItem cancelTicket = new JMenuItem("Cancel Ticket");
        cancelTicket.addActionListener(this);
        reservation.add(cancelTicket);

        // Billing
        JMenu reports = new JMenu("Reports");
        menubar.add(reports);

        JMenuItem billingReport = new JMenuItem("View Billing Reports");
        billingReport.addActionListener(this);
        reports.add(billingReport);

        // Logout
        JMenu session = new JMenu("Session");
        menubar.add(session);

        JMenuItem logout = new JMenuItem("Logout");
        logout.addActionListener(this);
        session.add(logout);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        switch (text) {
            case "Add/Update/Delete Flights":
                new ManageFlights();  // Implement this class
                break;

            case "View All Reservations":
                new ViewReservations();  // Implement this class
                break;

            case "Cancel Ticket":
                new CancelFlight();  // You already have this
                break;

            case "View Billing Reports":
                new ViewBillingReports();  // Implement this class
                break;

            case "Logout":
                setVisible(false);
                new Login();
                break;
        }
    }

    public static void main(String[] args) {
        new AdminHome("admin");
    }
}
