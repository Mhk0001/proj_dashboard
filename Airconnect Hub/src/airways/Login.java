//package airways;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Login extends JFrame implements ActionListener {
//    JButton submit, reset, close;
//    JTextField tfu;
//    JPasswordField tfpassword;
//
//    public Login() {
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//
//        JLabel lblu = new JLabel("Username");
//        lblu.setBounds(20, 20, 100, 20);
//        add(lblu);
//
//        tfu = new JTextField();
//        tfu.setBounds(130, 20, 200, 20);
//        add(tfu);
//
//        JLabel lblp = new JLabel("Password");
//        lblp.setBounds(20, 60, 100, 20);
//        add(lblp);
//
//        tfpassword = new JPasswordField();
//        tfpassword.setBounds(130, 60, 200, 20);
//        add(tfpassword);
//
//        reset = new JButton("Reset");
//        reset.setBounds(40, 120, 120, 25);
//        reset.addActionListener(this);
//        add(reset);
//
//        submit = new JButton("Submit");
//        submit.setBounds(190, 120, 120, 25);
//        submit.addActionListener(this);
//        add(submit);
//
//        close = new JButton("Close");
//        close.setBounds(120, 160, 120, 25);
//        close.addActionListener(this);
//        add(close);
//
//        setSize(400, 250);
//        setLocation(600, 250);
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == submit) {
//            String username = tfu.getText();
//            String password = new String(tfpassword.getPassword());
//try {
//    Connection con = conn.getConnection(); // get actual MySQL connection
//    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
//    PreparedStatement pstmt = con.prepareStatement(query);
//    pstmt.setString(1, username);
//    pstmt.setString(2, password);
//
//    ResultSet rs = pstmt.executeQuery();
//
//    if (rs.next()) {
//        new Home();
//        JOptionPane.showMessageDialog(null, "Login Successful");
//        setVisible(false);
//    } else {
//        JOptionPane.showMessageDialog(null, "Invalid Username or Password");
//    }
//
//    rs.close();
//    pstmt.close();
//    con.close(); // ✅ This now works because `con` is of type Connection
//} catch (Exception e) {
//    e.printStackTrace();
//}
//
//            
//
//             
//
//        } else if (ae.getSource() == reset) {
//            tfu.setText("");
//            tfpassword.setText("");
//
//        } else if (ae.getSource() == close) {
//            setVisible(false);
//        }
//    }
//
//    public static void main(String[] args) {
//        new Login();
//    }
//}






//package airways;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Login extends JFrame implements ActionListener {
//
//    JTextField tfUsername;
//    JPasswordField pfPassword;
//    JComboBox<String> cbRole;
//    JButton loginBtn, cancelBtn;
//
//    public Login() {
//        setTitle("Airline Login");
//        setLayout(null);
//
//        JLabel heading = new JLabel("Airline Management System Login");
//        heading.setBounds(100, 30, 400, 30);
//        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
//        add(heading);
//
//        JLabel lblUsername = new JLabel("Username:");
//        lblUsername.setBounds(100, 80, 100, 30);
//        add(lblUsername);
//
//        tfUsername = new JTextField();
//        tfUsername.setBounds(200, 80, 200, 30);
//        add(tfUsername);
//
//        JLabel lblPassword = new JLabel("Password:");
//        lblPassword.setBounds(100, 130, 100, 30);
//        add(lblPassword);
//
//        pfPassword = new JPasswordField();
//        pfPassword.setBounds(200, 130, 200, 30);
//        add(pfPassword);
//
//        JLabel lblRole = new JLabel("Role:");
//        lblRole.setBounds(100, 180, 100, 30);
//        add(lblRole);
//
//        cbRole = new JComboBox<>(new String[]{"Admin", "Agent"});
//        cbRole.setBounds(200, 180, 200, 30);
//        add(cbRole);
//
//        loginBtn = new JButton("Login");
//        loginBtn.setBounds(120, 240, 100, 30);
//        loginBtn.addActionListener(this);
//        add(loginBtn);
//
//        cancelBtn = new JButton("Cancel");
//        cancelBtn.setBounds(250, 240, 100, 30);
//        cancelBtn.addActionListener(this);
//        add(cancelBtn);
//
//        setSize(500, 350);
//        setLocation(500, 250);
//        setVisible(true);
//        getContentPane().setBackground(Color.WHITE);
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == loginBtn) {
//            String username = tfUsername.getText();
//            String password = String.valueOf(pfPassword.getPassword());
//            String role = (String) cbRole.getSelectedItem();
//
//            try {
//                Connection con = conn.getConnection(); // You already have this utility class
//                String query = "SELECT * FROM login WHERE username=? AND password=? AND role=?";
//                PreparedStatement ps = con.prepareStatement(query);
//                ps.setString(1, username);
//                ps.setString(2, password);
//                ps.setString(3, role);
//
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    JOptionPane.showMessageDialog(null, "Login Successful as " + role);
//
//                    setVisible(false);
//                    if (role.equals("Admin")) {
//                        new AdminHome(username);
//                    } else {
//                        new AgentHome(username);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
//                }
//
//                rs.close();
//                ps.close();
//                con.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
//            }
//
//        } else if (ae.getSource() == cancelBtn) {
//            System.exit(0);
//        }
//    }
//
//    public static void main(String[] args) {
//        new Login();
//    }
//}



package airways;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField tfUsername;
    JPasswordField pfPassword;
    JButton loginBtn, registerBtn, cancelBtn, forgotBtn;

    public Login() {
        setTitle("Air India - Login");
        setLayout(null);

        JLabel heading = new JLabel("Login / Register");
        heading.setBounds(140, 29, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(80, 100, 100, 30);
        add(lblUsername);

        tfUsername = new JTextField();
        tfUsername.setBounds(180, 100, 200, 30);
        add(tfUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(80, 150, 100, 30);
        add(lblPassword);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(180, 150, 200, 30);
        add(pfPassword);
        Font commonFont = new Font("Segoe UI", Font.PLAIN, 14);
lblUsername.setFont(commonFont);
lblPassword.setFont(commonFont);
tfUsername.setFont(commonFont);
pfPassword.setFont(commonFont);
Font commonfont = new Font("Segoe UI", Font.PLAIN, 24);
heading.setFont(commonfont);
tfUsername.setToolTipText("Enter your username");
pfPassword.setToolTipText("Enter your password");




        loginBtn = new JButton("Login");
        loginBtn.setBounds(50, 220, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(170, 220, 100, 30);
        registerBtn.addActionListener(this);
        add(registerBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(290, 220, 100, 30);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        forgotBtn = new JButton("Forgot Password");
        forgotBtn.setBounds(125, 270, 200, 30);
        forgotBtn.setForeground(Color.RED.darker());
        forgotBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
forgotBtn.setBorderPainted(false);
forgotBtn.setContentAreaFilled(false);
forgotBtn.setFocusPainted(false);
forgotBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      
        forgotBtn.addActionListener(this);
        add(forgotBtn);
        
        


        setSize(450, 380);
        setLocation(500, 250);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae) {
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());

        if (ae.getSource() == loginBtn) {
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields are required");
                return;
            }

            try {
                Connection con = conn.getConnection();
                String query = "SELECT * FROM login WHERE username=? AND password=?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String role = rs.getString("role");
                    JOptionPane.showMessageDialog(null, "Login Successful as " + role);

                    setVisible(false);
                    if (role.equalsIgnoreCase("Admin")) {
                        new AdminHome(username);
                    } else {
                        new AgentHome(username);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error!");
            }

        } else if (ae.getSource() == registerBtn) {
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields are required");
                return;
            }

            try {
                Connection con = conn.getConnection();

                // Check if user already exists
                String checkQuery = "SELECT * FROM login WHERE username=?";
                PreparedStatement checkPs = con.prepareStatement(checkQuery);
                checkPs.setString(1, username);
                ResultSet rs = checkPs.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists!");
                } else {
                    String insertQuery = "INSERT INTO login (username, password, role) VALUES (?, ?, 'Agent')";
                    PreparedStatement ps = con.prepareStatement(insertQuery);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Registered Successfully as Agent. You can now login.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Registration failed!");
            }

        } else if (ae.getSource() == forgotBtn) {
            setVisible(false);
            new Forgotpassword(); // You must implement this class
        } else if (ae.getSource() == cancelBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
        
    }
}
