/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author rowel
 */
public class LoginGUI extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCancel;
    private Login login;

    public LoginGUI() {
        setTitle("MotorPH Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        login = new Login("data/logins.csv"); // CSV file location

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(14);

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(15);

        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);

        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnCancel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogin) {
            String user = txtUsername.getText();
            String pass = String.valueOf(txtPassword.getPassword());

            if(login.validate(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                SwingUtilities.invokeLater(() -> {
                new EmployeeTableFrame().setVisible(true); // Replace with your main frame
                });
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!",
                                              "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource() == btnCancel) {
            System.exit(0);
        }
    }
}

