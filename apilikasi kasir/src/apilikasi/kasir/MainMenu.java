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

public class MainMenu extends JFrame {
    private JButton homeButton;
    private JButton transactionButton;
    private JButton historyButton;
    private JButton developerButton;
    private JLabel closeButton;

    public MainMenu() {
        setTitle("Main Menu");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Buat JLabel sebagai wadah untuk gambar latar belakang
        JLabel backgroundLabel = new JLabel(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\a.png"));
        backgroundLabel.setLayout(new BorderLayout());

        // Buat panel utama dan tambahkan backgroundLabel ke dalamnya
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(backgroundLabel, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("Selamat Datang di iPOS kelompok 7");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        homeButton = new JButton();
        homeButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\home.png"));
        homeButton.setText("Home");
        homeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        homeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        homeButton.setPreferredSize(new Dimension(80, 80));
        buttonPanel.add(homeButton);

        transactionButton = new JButton();
        transactionButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\pembelian.png"));
        transactionButton.setText("Transaction");
        transactionButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        transactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        transactionButton.setPreferredSize(new Dimension(80, 80));
        buttonPanel.add(transactionButton);

        historyButton = new JButton();
        historyButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\history.png"));
        historyButton.setText("History");
        historyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        historyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        historyButton.setPreferredSize(new Dimension(80, 80));
        buttonPanel.add(historyButton);

        developerButton = new JButton();
        developerButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\developer.png"));
        developerButton.setText("Developer");
        developerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        developerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        developerButton.setPreferredSize(new Dimension(80, 80));
        buttonPanel.add(developerButton);

        closeButton = new JLabel();
        closeButton.setIcon(new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\CLOSEE.png"));
        closeButton.setText("Close");
        closeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        closeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        closeButton.setPreferredSize(new Dimension(80, 80));
        buttonPanel.add(closeButton); // Menambahkan closeButton ke buttonPanel

        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openLoginPage();
                dispose();
            }
        });

        panel.add(buttonPanel, BorderLayout.NORTH);
        add(panel);

        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TransactionWindow transactionWindow = new TransactionWindow();
                transactionWindow.setVisible(true);
            }
        });

        developerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showDeveloperMenu(evt);
            }
        });
    }

    private void showDeveloperMenu(ActionEvent evt) {
        JMenuItem tentangMenuItem = new JMenuItem("Tentang");
        JMenuItem kontakMenuItem = new JMenuItem("Kontak");

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(tentangMenuItem);
        popupMenu.add(kontakMenuItem);

        ActionListener menuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JMenuItem source = (JMenuItem) e.getSource();
                String menuText = source.getText();

                if (menuText.equals("Tentang")) {
                    // Tampilkan menu tentang
                    JOptionPane.showMessageDialog(MainMenu.this, "Aplikasi ini dibuat oleh kelompok 7 untuk memenuhi project UAS dari mata kuliah System Aplikasi Berbasis Obyek.");
                } else if (menuText.equals("Kontak")) {
                    // Tampilkan menu kontak
                    String developerInfo = "Nama Pengembang: Yeremias N. Fernando\n" +
                            "No. Telp: 1234567890\n" +
                            "Alamat: Jl. Borobudur No. 49, Kota.Malang\n" +
                            "Email: nando@gmail.com";
                    JOptionPane.showMessageDialog(MainMenu.this, developerInfo);
                }
            }
        };

        tentangMenuItem.addActionListener(menuActionListener);
        kontakMenuItem.addActionListener(menuActionListener);

        popupMenu.show((Component) evt.getSource(), 0, developerButton.getHeight());
    }

    private void openLoginPage() {
        Login login = new Login();
        login.setVisible(true);
    }

}