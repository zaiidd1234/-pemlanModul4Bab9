import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MahasiswaFrameGUI extends JFrame {

    private static final Color COLOR_PRIMARY = new Color(30, 80, 160);
    private static final Color COLOR_BG      = new Color(245, 248, 255);
    private static final Color COLOR_BORDER  = new Color(180, 200, 240);

    private Mahasiswa mahasiswa;

    public MahasiswaFrameGUI(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
        buatJendela();
    }

    private void buatJendela() {
        setTitle("Data Mahasiswa");
        setSize(430, 320);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        //Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header
        JLabel headerLabel = new JLabel("Data Mahasiswa Terdaftar", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        headerLabel.setForeground(COLOR_PRIMARY);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Tabel data
        JPanel dataPanel = new JPanel(new GridLayout(6, 2, 8, 10));
        dataPanel.setBackground(Color.WHITE);
        dataPanel.setBorder(BorderFactory.createCompoundBorder( new LineBorder(COLOR_BORDER, 1, true), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        String[][] baris = {
            { "Nama", mahasiswa.getNamaLengkap() }, { "Tanggal Lahir", mahasiswa.getTanggalLahir() }, { "No. Pendaftaran", mahasiswa.getNomorPendaftaran() }, { "No. Telp", mahasiswa.getNoTelp() }, { "Alamat", mahasiswa.getAlamat() }, { "E-mail", mahasiswa.getEmail() }};

        for (String[] b : baris) {
            JLabel lblKey = new JLabel(b[0]);
            lblKey.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblKey.setForeground(new Color(60, 80, 120));

            JLabel lblVal = new JLabel(": " + b[1]);
            lblVal.setFont(new Font("Segoe UI", Font.PLAIN, 12));

            dataPanel.add(lblKey);
            dataPanel.add(lblVal);
        }

        
        JButton btnTutup = new JButton("Tutup");
        btnTutup.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnTutup.setBackground(COLOR_PRIMARY);
        btnTutup.setForeground(Color.WHITE);
        btnTutup.setFocusPainted(false);
        btnTutup.setOpaque(true);
        btnTutup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTutup.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));
        btnTutup.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(COLOR_BG);
        btnPanel.add(btnTutup);

        
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(dataPanel,   BorderLayout.CENTER);
        mainPanel.add(btnPanel,    BorderLayout.SOUTH);

        getContentPane().add(mainPanel);  

        
        setVisible(true);
    }
}