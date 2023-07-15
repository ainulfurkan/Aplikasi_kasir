/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apilikasi.kasir;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField statusField;
    private JButton registerButton;

    public Register() {
        setTitle("Register");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(144, 238, 144));
        setLocationRelativeTo(null);
        setUndecorated(true); // Menghilangkan garis luar (border) dari JFrame

        // Membuat panel info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());

        // Membuat label gambar
        ImageIcon logoIcon = new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\info.png");
        JLabel logoLabel = new JLabel(logoIcon);

        // Menambahkan label gambar ke panel info
        GridBagConstraints infoConstraints = new GridBagConstraints();
        infoConstraints.gridx = 0;
        infoConstraints.gridy = 0;
        infoPanel.add(logoLabel, infoConstraints);

        // Menambahkan panel info ke frame
        add(infoPanel, BorderLayout.WEST);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);

        usernameField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordField, constraints);

        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(emailLabel, constraints);

        emailField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(emailField, constraints);

        JLabel addressLabel = new JLabel("Address:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(addressLabel, constraints);

        addressField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(addressField, constraints);

        JLabel statusLabel = new JLabel("Status:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(statusLabel, constraints);

        statusField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(statusField, constraints);

        registerButton = new JButton("Register");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        panel.add(registerButton, constraints);

        JButton cancelButton = new JButton("Cancel");
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        add(panel);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                String address = addressField.getText();
                String status = statusField.getText();
                registerUser(username, password, email, address, status);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void registerUser(String username, String password, String email, String address, String status) {
        String url = "jdbc:mysql://localhost/pos";
        String usernameDB = "root";
        String passwordDB = "";

        try {
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);

            String query = "INSERT INTO users (username, password, email, address, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, address);
            statement.setString(5, status);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(Register.this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(() -> {
                    dispose(); // Menutup jendela Register
                    Login login = new Login(); // Membuat objek Login
                    login.setVisible(true); // Menampilkan jendela Login
                });
            } else {
                JOptionPane.showMessageDialog(Register.this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Register.this, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}