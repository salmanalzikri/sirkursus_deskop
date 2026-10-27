
package view;

import dao.PesertaDAO;
import model.Peserta;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FormPeserta extends JFrame {

    private JTextField txtNim, txtNama, txtProdi;
    private JTextField txtNoHp, txtEmail;
    private JRadioButton rbLaki, rbPerempuan;

    public FormPeserta() {
        setTitle("Form Pendaftaran Peserta");
        setSize(480, 430);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(
                20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 5, 7, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNim = new JTextField(20);
        txtNama = new JTextField(20);
        txtProdi = new JTextField(20);
        txtNoHp = new JTextField(20);
        txtEmail = new JTextField(20);

        rbLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");

        ButtonGroup grupJK = new ButtonGroup();
        grupJK.add(rbLaki);
        grupJK.add(rbPerempuan);

        tambahBaris(panel, gbc, 0, "NIM", txtNim);
        tambahBaris(panel, gbc, 1, "Nama", txtNama);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Jenis Kelamin"), gbc);

        JPanel panelJK = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelJK.add(rbLaki);
        panelJK.add(rbPerempuan);

        gbc.gridx = 1;
        panel.add(panelJK, gbc);

        tambahBaris(panel, gbc, 3, "Program Studi", txtProdi);
        tambahBaris(panel, gbc, 4, "No. HP", txtNoHp);
        tambahBaris(panel, gbc, 5, "Email", txtEmail);

        JButton btnSimpan = new JButton("Simpan");
        JButton btnReset = new JButton("Reset");

        JPanel panelTombol = new JPanel();
        panelTombol.add(btnSimpan);
        panelTombol.add(btnReset);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(panelTombol, gbc);

        add(panel);

        btnSimpan.addActionListener(e -> simpanData());
        btnReset.addActionListener(e -> resetForm());
    }

    private void tambahBaris(JPanel panel,
                             GridBagConstraints gbc,
                             int baris, String label,
                             JTextField field) {
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = baris;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void simpanData() {
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();

        if (nim.isEmpty() || nama.isEmpty()
                || prodi.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "NIM, nama, dan program studi wajib diisi.");
            return;
        }

        if (!rbLaki.isSelected() && !rbPerempuan.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Silakan pilih jenis kelamin.");
            return;
        }

        String jk = rbLaki.isSelected() ? "L" : "P";

        try {
            Peserta peserta = new Peserta(
                    0, nama, noHp, nim, prodi, jk, email);

            PesertaDAO dao = new PesertaDAO();

            if (dao.insert(peserta)) {
                JOptionPane.showMessageDialog(this,
                        "Data peserta berhasil disimpan ke database!");
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data peserta gagal disimpan.");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Validasi",
                    JOptionPane.WARNING_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan data: " + ex.getMessage(),
                    "Kesalahan Database",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        txtNim.setText("");
        txtNama.setText("");
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");

        rbLaki.setSelected(false);
        rbPerempuan.setSelected(false);

        txtNim.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormPeserta().setVisible(true);
        });
    }
}
