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
import java.sql.*;
 public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public Login() {
        setTitle("Login");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(144, 238, 144));
        setLocationRelativeTo(null);
        setUndecorated(true); // Menghilangkan garis luar (border) dari JFrame


        // Membuat panel info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBackground(new Color(144, 238, 144));


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

        // Membuat panel untuk tombol-tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        buttonPanel.setPreferredSize(new Dimension(150, 40));

        // Membuat tombol minimize
        JButton minimizeButton = new JButton();
        minimizeButton.setPreferredSize(new Dimension(20, 20));
        minimizeButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\minimize.png"));
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
        buttonPanel.add(minimizeButton);

        // Membuat tombol maximize
        JButton maximizeButton = new JButton();
        maximizeButton.setPreferredSize(new Dimension(20, 20));
        maximizeButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\maximize.png"));
        maximizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                    setExtendedState(JFrame.NORMAL);
                } else {
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            }
        });

        buttonPanel.add(maximizeButton);

        // Membuat tombol close
        JButton closeButton = new JButton();
        closeButton.setPreferredSize(new Dimension(20, 20));
        closeButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\close.png"));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Welcome to the POS System");
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

        loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        registerButton = new JButton("Register");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean loginSuccess = validateLogin(username, password);
                if (loginSuccess) {
                    openOrderApp();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterPage();
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        String url = "jdbc:mysql://localhost/pos";
        String usernameDB = "root";
        String passwordDB = "";

        try {
            Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            boolean loginSuccess = resultSet.next();
            resultSet.close();
            statement.close();
            connection.close();
            return loginSuccess;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Login.this, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void openRegisterPage() {
        Register register = new Register();
        register.setVisible(true);
    }

    private void openOrderApp() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
    }
}
