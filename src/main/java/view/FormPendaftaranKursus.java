
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FormPendaftaranKursus extends JFrame {

    // Tema Ruby Red
    private final Color warnaLatar = new Color(250, 246, 247);
    private final Color warnaHeader = new Color(136, 8, 40);
    private final Color warnaProses = new Color(176, 30, 65);
    private final Color warnaReset = new Color(190, 35, 55);
    private final Color warnaTeks = new Color(55, 35, 42);
    private final Color warnaBorder = new Color(229, 211, 217);
    private final Color warnaPutih = Color.WHITE;

    private JPanel panelUtama;
    private JPanel panelHeader;
    private JPanel panelForm;
    private JPanel panelJenis;
    private JPanel panelFasilitas;
    private JPanel panelTombol;
    private JPanel panelKonten;
    private JPanel panelRingkasan;

    private JLabel lblJudul;
    private JLabel lblNama;
    private JLabel lblKodeAkses;
    private JLabel lblJenis;
    private JLabel lblKursus;
    private JLabel lblFasilitas;
    private JLabel lblRingkasan;

    private JTextField txtNama;
    private JPasswordField txtKodeAkses;
    private JRadioButton rbMahasiswa;
    private JRadioButton rbUmum;
    private ButtonGroup groupJenis;
    private JComboBox<String> cmbKursus;
    private JCheckBox cbModul;
    private JCheckBox cbSertifikat;
    private JCheckBox cbRekaman;
    private JTextArea txtRingkasan;
    private JScrollPane scrollRingkasan;
    private JButton btnProses;
    private JButton btnReset;

    public FormPendaftaranKursus() {
        initComponents();

        groupJenis.add(rbMahasiswa);
        groupJenis.add(rbUmum);

        txtRingkasan.setEditable(false);
        txtRingkasan.setLineWrap(false);

        getRootPane().setDefaultButton(btnProses);

        setMinimumSize(new Dimension(950, 680));
        setSize(new Dimension(1100, 760));
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        panelUtama = new JPanel(new BorderLayout());
        panelHeader = new JPanel(new BorderLayout());
        panelForm = new JPanel(new GridBagLayout());

        panelJenis = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 4)
        );

        panelFasilitas = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 4)
        );

        panelTombol = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 5)
        );

        panelKonten = new JPanel(
                new GridLayout(1, 2, 20, 0)
        );

        panelRingkasan = new JPanel(new BorderLayout(0, 15));

        lblJudul = new JLabel(
                "SIKURSUS",
                SwingConstants.CENTER
        );

        lblNama = new JLabel("Nama Peserta");
        lblKodeAkses = new JLabel("Kode Akses");
        lblJenis = new JLabel("Jenis Peserta");
        lblKursus = new JLabel("Pilihan Kursus");
        lblFasilitas = new JLabel("Fasilitas Tambahan");
        lblRingkasan = new JLabel("Ringkasan Pendaftaran");

        txtNama = new JTextField();
        txtKodeAkses = new JPasswordField();

        rbMahasiswa = new JRadioButton("Mahasiswa");
        rbUmum = new JRadioButton("Umum");
        groupJenis = new ButtonGroup();

        cmbKursus = new JComboBox<String>(new String[]{
            "-- Pilih Kursus --",
            "Java Desktop",
            "Data Science",
            "Web Programming",
            "UI/UX Design"
        });

        cbModul = new JCheckBox("Modul Cetak");
        cbSertifikat = new JCheckBox("Sertifikat");
        cbRekaman = new JCheckBox("Akses Rekaman");

        txtRingkasan = new JTextArea(15, 30);
        scrollRingkasan = new JScrollPane(txtRingkasan);

        btnProses = new JButton("Proses Pendaftaran");
        btnReset = new JButton("Reset");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Pendaftaran Kursus");

        // =========================
        // PANEL UTAMA
        // =========================

        panelUtama.setBackground(warnaLatar);
        panelUtama.setBorder(
                new EmptyBorder(0, 0, 0, 0)
        );

        // =========================
        // HEADER
        // =========================

        panelHeader.setBackground(warnaHeader);
        panelHeader.setBorder(
                new EmptyBorder(18, 25, 18, 25)
        );

        lblJudul.setFont(
                new Font("Segoe UI", Font.BOLD, 27)
        );
        lblJudul.setForeground(warnaPutih);

        JLabel lblSubjudul = new JLabel(
                "FORM PENDAFTARAN KURSUS",
                SwingConstants.CENTER
        );

        lblSubjudul.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );
        lblSubjudul.setForeground(
                new Color(255, 225, 232)
        );

        JPanel isiHeader = new JPanel(
                new BorderLayout(0, 5)
        );
        isiHeader.setOpaque(false);
        isiHeader.add(lblJudul, BorderLayout.CENTER);
        isiHeader.add(lblSubjudul, BorderLayout.SOUTH);

        panelHeader.add(isiHeader, BorderLayout.CENTER);

        // =========================
        // PANEL FORM KIRI
        // =========================

        panelForm.setBackground(warnaPutih);
        panelForm.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(warnaBorder, 1, true),
                        new EmptyBorder(18, 22, 18, 22)
                )
        );

        aturLabel(lblNama);
        aturLabel(lblKodeAkses);
        aturLabel(lblJenis);
        aturLabel(lblKursus);
        aturLabel(lblFasilitas);
        aturLabel(lblRingkasan);

        aturInput(txtNama);
        aturInput(txtKodeAkses);

        txtNama.setPreferredSize(
                new Dimension(270, 38)
        );

        txtKodeAkses.setPreferredSize(
                new Dimension(270, 38)
        );

        txtKodeAkses.setToolTipText(
                "Kode akses demo praktikum: PBO2026"
        );

        // Dropdown kursus
        cmbKursus.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );
        cmbKursus.setForeground(warnaTeks);
        cmbKursus.setBackground(warnaPutih);
        cmbKursus.setPreferredSize(
                new Dimension(270, 38)
        );

        // Radio button
        aturPilihan(rbMahasiswa);
        aturPilihan(rbUmum);

        panelJenis.setBackground(warnaPutih);
        panelJenis.add(rbMahasiswa);
        panelJenis.add(rbUmum);

        // Checkbox fasilitas
        aturPilihan(cbModul);
        aturPilihan(cbSertifikat);
        aturPilihan(cbRekaman);

        panelFasilitas.setBackground(warnaPutih);
        panelFasilitas.add(cbModul);
        panelFasilitas.add(cbSertifikat);
        panelFasilitas.add(cbRekaman);

        // Tombol
        aturTombol(
                btnProses,
                warnaProses,
                warnaPutih
        );

        aturTombol(
                btnReset,
                warnaReset,
                warnaPutih
        );

        btnProses.setPreferredSize(
                new Dimension(185, 42)
        );

        btnReset.setPreferredSize(
                new Dimension(105, 42)
        );

        btnProses.addActionListener(
                evt -> btnProsesActionPerformed()
        );

        btnReset.addActionListener(
                evt -> btnResetActionPerformed()
        );

        panelTombol.setBackground(warnaPutih);
        panelTombol.add(btnReset);
        panelTombol.add(btnProses);

        // Susunan form sebelah kiri
        int baris = 0;

        tambahBagian(baris++, "01  DATA PESERTA");

        addRow(baris++, lblNama, txtNama, 0);
        addRow(baris++, lblKodeAkses, txtKodeAkses, 0);
        addRow(baris++, lblJenis, panelJenis, 0);

        tambahBagian(baris++, "02  PILIHAN KURSUS");

        addRow(baris++, lblKursus, cmbKursus, 0);
        addRow(baris++, lblFasilitas, panelFasilitas, 0);

        GridBagConstraints cTombol =
                new GridBagConstraints();

        cTombol.gridx = 0;
        cTombol.gridy = baris;
        cTombol.gridwidth = 2;
        cTombol.weightx = 1;
        cTombol.fill = GridBagConstraints.HORIZONTAL;
        cTombol.anchor = GridBagConstraints.EAST;
        cTombol.insets = new Insets(25, 0, 0, 0);

        panelForm.add(panelTombol, cTombol);

        // =========================
        // PANEL RINGKASAN KANAN
        // =========================

        panelRingkasan.setBackground(warnaPutih);
        panelRingkasan.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(warnaBorder, 1, true),
                        new EmptyBorder(22, 22, 22, 22)
                )
        );

        JLabel lblInfoRingkasan = new JLabel(
                "Hasil dan rincian biaya pendaftaran"
        );

        lblInfoRingkasan.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        lblInfoRingkasan.setForeground(
                new Color(125, 105, 113)
        );

        JPanel kepalaRingkasan = new JPanel(
                new BorderLayout(0, 8)
        );
        kepalaRingkasan.setBackground(warnaPutih);

        lblRingkasan.setFont(
                new Font("Segoe UI", Font.BOLD, 19)
        );
        lblRingkasan.setForeground(warnaHeader);

        kepalaRingkasan.add(
                lblRingkasan,
                BorderLayout.NORTH
        );

        kepalaRingkasan.add(
                lblInfoRingkasan,
                BorderLayout.SOUTH
        );

        txtRingkasan.setFont(
                new Font("Consolas", Font.PLAIN, 13)
        );

        txtRingkasan.setForeground(warnaTeks);
        txtRingkasan.setBackground(
                new Color(255, 250, 252)
        );

        txtRingkasan.setMargin(
                new Insets(15, 15, 15, 15)
        );

        txtRingkasan.setEditable(false);
        txtRingkasan.setLineWrap(false);

        scrollRingkasan.setPreferredSize(
                new Dimension(420, 450)
        );

        scrollRingkasan.setBorder(
                new LineBorder(warnaBorder, 1, true)
        );

        panelRingkasan.add(
                kepalaRingkasan,
                BorderLayout.NORTH
        );

        panelRingkasan.add(
                scrollRingkasan,
                BorderLayout.CENTER
        );

        // =========================
        // GABUNGKAN KIRI DAN KANAN
        // =========================

        panelKonten.setBackground(warnaLatar);
        panelKonten.setBorder(
                new EmptyBorder(22, 25, 25, 25)
        );

        panelKonten.add(panelForm);
        panelKonten.add(panelRingkasan);

        panelUtama.add(
                panelHeader,
                BorderLayout.NORTH
        );

        panelUtama.add(
                panelKonten,
                BorderLayout.CENTER
        );

        setContentPane(panelUtama);
    }

    // =========================
    // STYLING KOMPONEN
    // =========================

    private void aturLabel(JLabel label) {
        label.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        label.setForeground(warnaTeks);
    }

    private void aturInput(JTextField input) {
        input.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        input.setForeground(warnaTeks);
        input.setBackground(warnaPutih);
        input.setCaretColor(warnaHeader);

        input.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(warnaBorder, 1, true),
                        new EmptyBorder(5, 10, 5, 10)
                )
        );
    }

    private void aturPilihan(
            javax.swing.AbstractButton pilihan) {

        pilihan.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        pilihan.setForeground(warnaTeks);
        pilihan.setBackground(warnaPutih);
        pilihan.setFocusPainted(false);
        pilihan.setOpaque(true);
    }

    private void aturTombol(
            JButton tombol,
            Color warna,
            Color warnaTeksTombol) {

        tombol.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        tombol.setBackground(warna);
        tombol.setForeground(warnaTeksTombol);
        tombol.setFocusPainted(false);
        tombol.setBorderPainted(false);
        tombol.setOpaque(true);

        tombol.setCursor(
                new java.awt.Cursor(
                        java.awt.Cursor.HAND_CURSOR
                )
        );
    }

    // =========================
    // JUDUL BAGIAN FORM
    // =========================

    private void tambahBagian(
            int row,
            String judul) {

        JLabel labelBagian = new JLabel(judul);

        labelBagian.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        labelBagian.setForeground(warnaHeader);

        labelBagian.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 1, 0, warnaBorder
                )
        );

        GridBagConstraints c =
                new GridBagConstraints();

        c.gridx = 0;
        c.gridy = row;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(14, 0, 10, 0);

        panelForm.add(labelBagian, c);
    }

    // =========================
    // SUSUNAN BARIS FORM
    // =========================

    private void addRow(
            int row,
            JLabel label,
            java.awt.Component component,
            double weightY) {

        GridBagConstraints cLabel =
                new GridBagConstraints();

        cLabel.gridx = 0;
        cLabel.gridy = row;
        cLabel.weightx = 0;
        cLabel.anchor = GridBagConstraints.NORTHWEST;
        cLabel.insets = new Insets(10, 0, 12, 12);

        panelForm.add(label, cLabel);

        GridBagConstraints cField =
                new GridBagConstraints();

        cField.gridx = 1;
        cField.gridy = row;
        cField.weightx = 1;
        cField.weighty = weightY;

        cField.fill = weightY > 0
                ? GridBagConstraints.BOTH
                : GridBagConstraints.HORIZONTAL;

        cField.anchor = GridBagConstraints.NORTHWEST;
        cField.insets = new Insets(3, 0, 12, 0);

        panelForm.add(component, cField);
    }

    // =========================
    // JENIS PESERTA
    // =========================

    private String getJenisPeserta() {
        if (rbMahasiswa.isSelected()) {
            return "Mahasiswa";
        }

        if (rbUmum.isSelected()) {
            return "Umum";
        }

        return "";
    }

    // =========================
    // PERHITUNGAN BIAYA
    // =========================

    private double hitungBiayaDasar(String kursus) {
        switch (kursus) {
            case "Java Desktop":
                return 500_000;

            case "Data Science":
                return 650_000;

            case "Web Programming":
                return 450_000;

            case "UI/UX Design":
                return 400_000;

            default:
                return 0;
        }
    }

    private double hitungBiayaFasilitas() {
        double total = 0;

        if (cbModul.isSelected()) {
            total += 50_000;
        }

        if (cbSertifikat.isSelected()) {
            total += 75_000;
        }

        if (cbRekaman.isSelected()) {
            total += 40_000;
        }

        return total;
    }

    private String buatRingkasanFasilitas() {
        String[] fasilitasDipilih = new String[3];
        int jumlah = 0;

        if (cbModul.isSelected()) {
            fasilitasDipilih[jumlah] = "Modul Cetak";
            jumlah++;
        }

        if (cbSertifikat.isSelected()) {
            fasilitasDipilih[jumlah] = "Sertifikat";
            jumlah++;
        }

        if (cbRekaman.isSelected()) {
            fasilitasDipilih[jumlah] = "Akses Rekaman";
            jumlah++;
        }

        if (jumlah == 0) {
            return "- Tidak ada fasilitas tambahan\n";
        }

        StringBuilder hasil = new StringBuilder();

        for (int i = 0; i < jumlah; i++) {
            hasil.append("- ")
                    .append(fasilitasDipilih[i])
                    .append("\n");
        }

        return hasil.toString();
    }

    // =========================
    // VALIDASI INPUT
    // =========================

    private boolean validasiInput() {
        String nama = txtNama.getText().trim();

        String kode = new String(
                txtKodeAkses.getPassword()
        );

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama peserta wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );

            txtNama.requestFocus();
            return false;
        }

        if (!kode.equals("PBO2026")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Kode akses demo salah.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );

            txtKodeAkses.requestFocus();
            return false;
        }

        if (!rbMahasiswa.isSelected()
                && !rbUmum.isSelected()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih jenis peserta.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        if (cmbKursus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih kursus terlebih dahulu.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );

            cmbKursus.requestFocus();
            return false;
        }

        return true;
    }

    // =========================
    // TOMBOL PROSES
    // =========================

    private void btnProsesActionPerformed() {
        if (!validasiInput()) {
            return;
        }

        String nama = txtNama.getText().trim();
        String jenis = getJenisPeserta();

        String kursus =
                cmbKursus.getSelectedItem().toString();

        double biayaDasar =
                hitungBiayaDasar(kursus);

        double diskon = jenis.equals("Mahasiswa")
                ? biayaDasar * 0.10
                : 0;

        double biayaFasilitas =
                hitungBiayaFasilitas();

        double total =
                biayaDasar - diskon + biayaFasilitas;

        String fasilitas =
                buatRingkasanFasilitas();

        String ringkasan =
                "PENDAFTARAN KURSUS\n"
                + "================================\n"
                + "Nama            : " + nama + "\n"
                + "Jenis Peserta   : " + jenis + "\n"
                + "Kursus          : " + kursus + "\n"
                + "Biaya Dasar     : "
                + formatRupiah(biayaDasar) + "\n"
                + "Diskon          : "
                + formatRupiah(diskon) + "\n"
                + "Fasilitas:\n"
                + fasilitas
                + "Biaya Fasilitas : "
                + formatRupiah(biayaFasilitas) + "\n"
                + "================================\n"
                + "TOTAL           : "
                + formatRupiah(total);

        txtRingkasan.setText(ringkasan);
        txtRingkasan.setCaretPosition(0);

        JOptionPane.showMessageDialog(
                this,
                "Pendaftaran berhasil diproses.",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================
    // TOMBOL RESET
    // =========================

    private void btnResetActionPerformed() {
        int jawab = JOptionPane.showConfirmDialog(
                this,
                "Kosongkan seluruh input?",
                "Konfirmasi Reset",
                JOptionPane.YES_NO_OPTION
        );

        if (jawab == JOptionPane.YES_OPTION) {
            resetForm();
        }
    }

    private void resetForm() {
        txtNama.setText("");
        txtKodeAkses.setText("");

        groupJenis.clearSelection();

        cmbKursus.setSelectedIndex(0);

        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);

        txtRingkasan.setText("");

        txtNama.requestFocus();
    }

    // =========================
    // FORMAT RUPIAH
    // =========================

    private String formatRupiah(double nilai) {
        String angka = String.format(
                Locale.US,
                "%,.0f",
                nilai
        ).replace(',', '.');

        return "Rp" + angka;
    }

    // =========================
    // MAIN PROGRAM
    // =========================

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info
                    : UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(
                            info.getClassName()
                    );
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(
                    FormPendaftaranKursus.class.getName()
            ).log(
                    java.util.logging.Level.WARNING,
                    "Nimbus tidak dapat diaktifkan",
                    ex
            );
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FormPendaftaranKursus().setVisible(true);
        });
    }
}
