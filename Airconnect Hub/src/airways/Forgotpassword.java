
package airways;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Forgotpassword extends JFrame implements ActionListener {

    JTextField tfUsername, tfNewPassword;
    JButton resetBtn, backBtn;

    public Forgotpassword() {
        setTitle("Reset Password");
        setLayout(null);

        JLabel heading = new JLabel("Forgot Password");
        heading.setBounds(130, 30, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblUser = new JLabel("Enter Username:");
        lblUser.setBounds(50, 100, 150, 30);
        add(lblUser);

        tfUsername = new JTextField();
        tfUsername.setBounds(200, 100, 150, 30);
        add(tfUsername);

        JLabel lblPass = new JLabel("New Password:");
        lblPass.setBounds(50, 150, 150, 30);
        add(lblPass);

        tfNewPassword = new JTextField();
        tfNewPassword.setBounds(200, 150, 150, 30);
        add(tfNewPassword);

        resetBtn = new JButton("Reset Password");
        resetBtn.setBounds(50, 210, 140, 30);
        resetBtn.addActionListener(this);
        add(resetBtn);

        backBtn = new JButton("Back to Login");
        backBtn.setBounds(210, 210, 140, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        getContentPane().setBackground(Color.WHITE);
        setSize(420, 320);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == resetBtn) {
            String username = tfUsername.getText();
            String newPassword = tfNewPassword.getText();

            if (username.equals("") || newPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty");
                return;
            }

            try {
                Connection con = conn.getConnection();
                PreparedStatement ps = con.prepareStatement(
                    "UPDATE login SET password=? WHERE username=?"
                );
                ps.setString(1, newPassword);
                ps.setString(2, username);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Password Updated!");
                    setVisible(false);
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while resetting password");
            }

        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Forgotpassword();
    }
}


