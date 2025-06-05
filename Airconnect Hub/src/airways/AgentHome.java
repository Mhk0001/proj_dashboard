
package airways;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgentHome extends JFrame implements ActionListener {

    public AgentHome(String username) {
        setLayout(null);
        setTitle("Agent Panel - " + username);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airways/icons/front.jpg"));
        JLabel background = new JLabel(i1);
        background.setBounds(0, 0, 1600, 800);
        add(background);

        // Heading
        JLabel heading = new JLabel("AGENT PANEL - AIR INDIA");
        heading.setBounds(500, 40, 1000, 40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 36));
        background.add(heading);

        // Menu Bar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        // Customer Menu
        JMenu customerMenu = new JMenu("Customer");
        menubar.add(customerMenu);

        JMenuItem addCustomer = new JMenuItem("Add Customer");
        addCustomer.addActionListener(this);
        customerMenu.add(addCustomer);

        // Flight Menu
        JMenu flightMenu = new JMenu("Flights");
        menubar.add(flightMenu);

        JMenuItem viewFlights = new JMenuItem("View Flight Info");
        viewFlights.addActionListener(this);
        flightMenu.add(viewFlights);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        flightMenu.add(bookFlight);

        JMenuItem cancelFlight = new JMenuItem("Cancel Flight");
        cancelFlight.addActionListener(this);
        flightMenu.add(cancelFlight);

        // Journey Menu
        JMenu journeyMenu = new JMenu("Journey");
        menubar.add(journeyMenu);

        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        journeyMenu.add(journeyDetails);

        // Ticket Menu
        JMenu ticketMenu = new JMenu("Ticket");
        menubar.add(ticketMenu);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticketMenu.add(boardingPass);

        JMenuItem checkBill = new JMenuItem("Check Bill");
        checkBill.addActionListener(this);
        ticketMenu.add(checkBill);

        // Session Menu
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
            case "Add Customer":
                new AddCustomer(); // Implemented already
                break;

            case "View Flight Info":
                new FlightInfo(); // Implemented already
                break;

            case "Book Flight":
                String input = JOptionPane.showInputDialog("How many seats do you want to book?");
                int numOfSeats;
                try {
                    numOfSeats = Integer.parseInt(input);
                    if (numOfSeats <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid positive number");
                    return;
                }

                for (int i = 1; i <= numOfSeats; i++) {
                    new BookFlight(i); // Constructor of BookFlight must accept int seat number
                }
                break;

            case "Cancel Flight":
                new CancelFlight(); // Already implemented
                break;

            case "Journey Details":
                new JourneyDetails(); // Already implemented
                break;

            case "Boarding Pass":
                new BoardingPass(); // Already implemented
                break;

            case "Check Bill":
                new Payment(); // Already implemented
                break;

            case "Logout":
                setVisible(false);
                new Login(); // Back to login screen
                break;
        }
    }

    public static void main(String[] args) {
        new AgentHome("agent");
    }
}
