import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class FormPendaftaranGUI extends JFrame {

    private static final Color COLOR_PRIMARY = new Color(30, 80, 160);
    private static final Color COLOR_BG      = new Color(240, 244, 255);
    private static final Color COLOR_BORDER  = new Color(180, 200, 240);
    private static final Color COLOR_LABEL   = new Color(50, 70, 110);

    // Field form
    private JTextField txtNama;
    private JTextField txtTanggalLahir;
    private JTextField txtNomorPendaftaran;
    private JTextField txtNoTelp;
    private JTextArea  txtAlamat;
    private JTextField txtEmail;
    private JButton    btnSubmit;
    private JButton    btnReset;

    public FormPendaftaranGUI() {
        buatKomponen();
        susunLayout();
        pasangListener();
    }

    private void buatKomponen() {
        setTitle("Form Pendaftaran Mahasiswa Baru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 570);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COLOR_BG);

        txtNama             = buatTextField();
        txtTanggalLahir     = buatTextField();
        txtNomorPendaftaran = buatTextField();
        txtNoTelp           = buatTextField();
        txtEmail            = buatTextField();

        txtAlamat = new JTextArea(3, 20);
        txtAlamat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        txtAlamat.setBorder(BorderFactory.createCompoundBorder(new LineBorder(COLOR_BORDER, 1, true), BorderFactory.createEmptyBorder(5, 8, 5, 8)));

        btnSubmit = buatButton("Submit", COLOR_PRIMARY);
        btnReset  = buatButton("Reset",  new Color(180, 40, 40));
    }

    private void susunLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_PRIMARY);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel lblTitle = new JLabel("Form Pendaftaran Mahasiswa Baru");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Universitas Brawijaya — Program Studi Teknlogi Informasi");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(new Color(180, 210, 255));
        headerPanel.add(lblTitle, BorderLayout.NORTH);
        headerPanel.add(lblSub,   BorderLayout.SOUTH);


        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(COLOR_BORDER, 1, true), BorderFactory.createEmptyBorder(20, 25, 20, 25)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 4, 6, 4);

        tambahBaris(cardPanel, gbc, 0, "Nama Lengkap", txtNama);
        tambahBaris(cardPanel, gbc, 1, "Tanggal Lahir", txtTanggalLahir);
        tambahBaris(cardPanel, gbc, 2, "Nomor Pendaftaran", txtNomorPendaftaran);
        tambahBaris(cardPanel, gbc, 3, "No. Telp", txtNoTelp);
        tambahBarisArea(cardPanel, gbc, 4, "Alamat", txtAlamat);
        tambahBaris(cardPanel, gbc, 5, "E-mail", txtEmail);

        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(COLOR_BG);
        btnPanel.add(btnSubmit);
        btnPanel.add(btnReset);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    
    private void pasangListener() {
        btnSubmit.addActionListener(e -> klikSubmit());
        btnReset.addActionListener(e  -> klikReset());
    }

    
    private void klikSubmit() {
        // Cek ada kolom kosong?
        if (!formValid()) {
            JOptionPane.showMessageDialog( this,"Masih ada kolom yang belum terisi!\nSilakan lengkapi semua data.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // konfirmasi
        int jawaban = JOptionPane.showConfirmDialog( this,"Apakah anda yakin data yang Anda isi sudah benar?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Jika Cancel atau tutup dialog maka kembali ke form
        if (jawaban != JOptionPane.OK_OPTION) {
            return;
        }

    
        Mahasiswa mhs = new Mahasiswa(txtNama.getText().trim(), txtTanggalLahir.getText().trim(), txtNomorPendaftaran.getText().trim(), txtNoTelp.getText().trim(), txtAlamat.getText().trim(), txtEmail.getText().trim());

        // jenedela baru
        new MahasiswaFrameGUI(mhs);
    }

    //reset form
    private void klikReset() {
        int jawaban = JOptionPane.showConfirmDialog(this, "Reset semua data form?", "Konfirmasi Reset", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (jawaban == JOptionPane.YES_OPTION) {
            txtNama.setText("");
            txtTanggalLahir.setText("");
            txtNomorPendaftaran.setText("");
            txtNoTelp.setText("");
            txtAlamat.setText("");
            txtEmail.setText("");
            txtNama.requestFocus();
        }
    }

    private boolean formValid() {
        return !txtNama.getText().trim().isEmpty() && !txtTanggalLahir.getText().trim().isEmpty() && !txtNomorPendaftaran.getText().trim().isEmpty() && !txtNoTelp.getText().trim().isEmpty() && !txtAlamat.getText().trim().isEmpty() && !txtEmail.getText().trim().isEmpty();
    }

    private void tambahBaris(JPanel panel, GridBagConstraints gbc, int baris, String teks, JTextField field) {
        gbc.gridx = 0; 
        gbc.gridy = baris; 
        gbc.weightx = 0.35;
        JLabel lbl = new JLabel(teks);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(COLOR_LABEL);
        panel.add(lbl, gbc);
        gbc.gridx = 1; gbc.weightx = 0.65;
        panel.add(field, gbc);
    }

    private void tambahBarisArea(JPanel panel, GridBagConstraints gbc, int baris, String teks, JTextArea area) {
        gbc.gridx = 0; 
        gbc.gridy = baris; 
        gbc.weightx = 0.35;
        JLabel lbl = new JLabel(teks);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(COLOR_LABEL);
        panel.add(lbl, gbc);

        gbc.gridx = 1; 
        gbc.weightx = 0.65;
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1, true));
        scroll.setPreferredSize(new Dimension(260, 65));
        panel.add(scroll, gbc);
    }

    private JTextField buatTextField() {
        JTextField tf = new JTextField(20);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        tf.setBorder(BorderFactory.createCompoundBorder(new LineBorder(COLOR_BORDER, 1, true),BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        return tf;
    }

    private JButton buatButton(String teks, Color warna) {
        JButton btn = new JButton(teks);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(warna);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        Color hover = warna.darker();
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            @Override public void mouseExited (MouseEvent e) { btn.setBackground(warna); }
        });
        return btn;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            FormPendaftaranGUI form = new FormPendaftaranGUI();
            form.setVisible(true);
        });
    }
}