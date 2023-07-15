/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apilikasi.kasir;
import static com.sun.javafx.fxml.expression.Expression.add;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TransactionWindow extends JFrame {
    private final Object[][] makananList = {
            {"Ayam Krispy", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\AC.png")},
            {"Ayam Bakar", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\AB.png")},
            {"Ayam Kampung", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\AKM.png")},
            {"Ayam Tulang Lunak", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\ATL.png")},
            {"Lele Goreng", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\lele_goreng.png")},
            {"Double Wings", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\wings.png")},
            {"Bebek Goreng", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\bebek.png")},
            {"Bandeng Presto", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\bandeng.png")},

    };
    private final Object[][] minumanList;
    private final Object[][] tambahanList = {
            {"Jamur Crispy", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\jamur.png")},
            {"Kulit Crispy", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\kulit.png")},
            {"Cah Kangkung", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\cah kangkung.png")},
            {"Terong Penyet", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\terong.png")},
            {"Dimsum Ori", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\ori.png")},
            {"Dimsum Keju", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\keju.png")},
            {"Dimsum Dumpling", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\dumpling.png")},
            {"Mie BUCIN", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\bucin.png")}
    };

    private List<String> selectedItems = new ArrayList<>();

    private JLabel orderLabel;
    private final JLabel itemLabel;
    private final JLabel jumlahLabel;
    private final JLabel hargaLabel;
    private final JLabel totalLabel;
    private final JLabel pembayaranLabel;
    private JTextField pembayaranTextField;
    private JLabel kembalianLabel;

    public TransactionWindow() {
        this.minumanList = new Object[][]{{"Milo", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\milo.png")}, {"Jeruk", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\jeruk.png")}, {"Teh", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\teh.png")}, {"Capuchino", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\capuchino.png")}, {"Susu", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\susu.png")}, {"Soda Gembira", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\sogem.png")}, {"Joshua", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\joshua.png")}, {"STMJ", new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\stmj.png")}};
        setTitle("Transaction");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel transactionPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel makananLabel = new JLabel("Jenis Makanan");
        makananLabel.setHorizontalAlignment(SwingConstants.CENTER);
        makananLabel.setFont(makananLabel.getFont().deriveFont(Font.BOLD, 18f)); // Pertebal dan perbesar huruf
        transactionPanel.add(makananLabel);

        JLabel minumanLabel = new JLabel("Jenis Minuman");
        minumanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minumanLabel.setFont(minumanLabel.getFont().deriveFont(Font.BOLD, 18f)); // Pertebal dan perbesar huruf
        transactionPanel.add(minumanLabel);

        JLabel tambahanLabel = new JLabel("Menu Tambahan");
        tambahanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tambahanLabel.setFont(tambahanLabel.getFont().deriveFont(Font.BOLD, 18f)); // Pertebal dan perbesar huruf
        transactionPanel.add(tambahanLabel);


        int rowCount = Math.max(Math.max(makananList.length, minumanList.length), tambahanList.length);

        for (int i = 0; i < rowCount; i++) {
            if (i < makananList.length) {
                JButton makananButton = createButton((String) makananList[i][0], (ImageIcon) makananList[i][1]);
                transactionPanel.add(makananButton);
            } else {
                transactionPanel.add(new JPanel());
            }

            if (i < minumanList.length) {
                JButton minumanButton = createButton((String) minumanList[i][0], (ImageIcon) minumanList[i][1]);
                transactionPanel.add(minumanButton);
            } else {
                transactionPanel.add(new JPanel());
            }

            if (i < tambahanList.length) {
                JButton tambahanButton = createButton((String) tambahanList[i][0], (ImageIcon) tambahanList[i][1]);
                transactionPanel.add(tambahanButton);
            } else {
                transactionPanel.add(new JPanel());
            }
        }

        JScrollPane scrollPane = new JScrollPane(transactionPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
        // Set the close button icon
        ImageIcon closeIcon = new ImageIcon("D:\\SEMESTER 10\\SABO\\icon\\CLOSEE.png");
        closeButton.setIcon(closeIcon);
        closeButton.setText(""); // Hapus teks tombol

        itemLabel = new JLabel("Item: ");
        jumlahLabel = new JLabel("Jumlah: ");
        hargaLabel = new JLabel("Harga: ");
        totalLabel = new JLabel("Total: ");
        pembayaranLabel = new JLabel("Pembayaran:Rp ");
        pembayaranTextField = new JTextField();

        JButton hitungButton = new JButton("Hitung");
        hitungButton.setPreferredSize(new Dimension(40, 10));
        hitungButton.setBackground(Color.RED);
        kembalianLabel = new JLabel("Kembalian: ");
        hitungButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                double pembayaran = Double.parseDouble(pembayaranTextField.getText());
                double totalHarga = selectedItems.size() * 10;
                double kembalian = pembayaran - totalHarga;
                kembalianLabel.setText("Kembalian: Rp" + kembalian);
            }
        });

        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(new Color(144, 238, 144)); // Mengatur warna latar belakang panel

        infoPanel.add(itemLabel);
        infoPanel.add(jumlahLabel);
        infoPanel.add(hargaLabel);
        infoPanel.add(totalLabel);
        infoPanel.add(pembayaranLabel);
        infoPanel.add(pembayaranTextField);
        infoPanel.add(hitungButton);
        infoPanel.add(kembalianLabel);

        // ...
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel closeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closeButtonPanel.add(closeButton);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(infoPanel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(closeButtonPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);

        add(mainPanel, BorderLayout.CENTER);


    }

    private JButton createButton(String label, ImageIcon icon) {
        JButton button = new JButton(label, icon);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String item = button.getText();
                int jumlah = 1; // Jumlah item yang dipesan (dapat diubah sesuai kebutuhan)
                int harga = 10; // Harga item (dapat diubah sesuai kebutuhan)

                // Cek jika item sudah ada dalam daftar pesanan
                boolean itemExists = false;
                for (String selectedItem : selectedItems) {
                    if (selectedItem.equals(item)) {
                        itemExists = true;
                        break;
                    }
                }

                // Jika item sudah ada, tingkatkan jumlah
                if (itemExists) {
                    jumlah++;
                } else {
                    selectedItems.add(item);
                }

                StringBuilder itemText = new StringBuilder("Item: ");
                for (String selectedItem : selectedItems) {
                    itemText.append(selectedItem).append(", ");
                }
                itemText.delete(itemText.length() - 2, itemText.length()); // Menghapus koma terakhir
                itemLabel.setText(itemText.toString());

                jumlahLabel.setText("Jumlah: " + selectedItems.size());
                hargaLabel.setText("Harga: Rp" + harga);
                totalLabel.setText("Total: Rp" + selectedItems.size() * harga);
            }
        });

        return button;
    }
}