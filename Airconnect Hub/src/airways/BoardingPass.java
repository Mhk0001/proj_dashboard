
package airways;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BoardingPass extends JFrame implements ActionListener {
    JTextField tfpnr;
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;
    JButton fetchButton;

    public BoardingPass() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("AIR INDIA");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(360, 50, 300, 30);
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subheading.setForeground(Color.BLUE);
        add(subheading);

        JLabel lblaadhar = new JLabel("PNR DETAILS");
        lblaadhar.setBounds(60, 100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 100, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 100, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        // Labels for result
        addLabel("NAME", 60, 140);        tfname = addValueLabel(220, 140);
        addLabel("NATIONALITY", 60, 180); tfnationality = addValueLabel(220, 180);
        addLabel("SRC", 60, 220);         lblsrc = addValueLabel(220, 220);
        addLabel("DEST", 380, 220);       lbldest = addValueLabel(540, 220);
        addLabel("Flight Name", 60, 260); labelfname = addValueLabel(220, 260);
        addLabel("Flight Code", 380, 260);labelfcode = addValueLabel(540, 260);
        addLabel("Date", 60, 300);        labeldate = addValueLabel(220, 300);

        // Airline image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airways/icons/airindia.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        JLabel lblimage = new JLabel(new ImageIcon(i2));
        lblimage.setBounds(600, 0, 300, 300);
        add(lblimage);

        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }

    // Utility method to add a label
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(label);
    }

    // Utility method to add value label
    private JLabel addValueLabel(int x, int y) {
        JLabel label = new JLabel();
        label.setBounds(x, y, 150, 25);
        add(label);
        return label;
    }

    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText().trim();

        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter PNR");
            return;
        }

        try {
            Connection con = conn.getConnection(); // from conn.java
            String query = "SELECT * FROM reservation WHERE PNR = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, pnr);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                lblsrc.setText(rs.getString("source"));
                lbldest.setText(rs.getString("destination"));
                labelfname.setText(rs.getString("f_name"));
                labelfcode.setText(rs.getString("f_code"));
                labeldate.setText(rs.getString("travel_date"));
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}

