package view;

import dao.PesertaDAO;
import model.Peserta;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PesertaFrame extends javax.swing.JFrame {

    private final PesertaDAO dao = new PesertaDAO();
    private int selectedId = -1;

    public PesertaFrame() {
        initComponents();
        setLocationRelativeTo(null); // Menempatkan window di tengah layar
        configureTable();
        loadTable();
        clearForm();
    }

    private void configureTable() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID", "NIM", "Nama", "JK", "Prodi", "No. HP", "Email"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPeserta.setModel(model);
        tblPeserta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }

    private void fillTable(List<Peserta> data) {
        DefaultTableModel model = (DefaultTableModel) tblPeserta.getModel();
        model.setRowCount(0);
        
        // Safety check to prevent NullPointerException if DAO returns null
        if (data == null) {
            return;
        }
        
        for (Peserta p : data) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNim(),
                p.getNama(),
                p.getJenisKelamin(),
                p.getProdi(),
                p.getNoHp(),
                p.getEmail()
            });
        }
    }

    private void loadTable() {
        try {
            fillTable(dao.findAll());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Gagal memuat data: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void refreshData() {
        loadTable();
        clearForm();
        txtCari.setText("");
    }

    private void clearForm() {
        selectedId = -1;
        txtNim.setText("");
        txtNama.setText("");
        bgJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        tblPeserta.clearSelection();
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        txtNim.requestFocus();
    }

    private String getJenisKelamin() {
        if (rbLaki.isSelected()) {
            return "L";
        }
        if (rbPerempuan.isSelected()) {
            return "P";
        }
        return null;
    }

    private Peserta getFormData() {
        Peserta p = new Peserta();
        p.setId(selectedId);
        p.setNim(txtNim.getText().trim());
        p.setNama(txtNama.getText().trim());
        p.setJenisKelamin(getJenisKelamin());
        p.setProdi(txtProdi.getText().trim());
        p.setNoHp(txtNoHp.getText().trim());
        p.setEmail(txtEmail.getText().trim());
        return p;
    }

    private boolean validateForm() {
        if (txtNim.getText().trim().isEmpty()
                || txtNama.getText().trim().isEmpty()
                || txtProdi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "NIM, Nama, dan Prodi wajib diisi."
            );
            return false;
        }
        if (getJenisKelamin() == null) {
            JOptionPane.showMessageDialog(
                this,
                "Pilih jenis kelamin."
            );
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        bgJenisKelamin = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        txtProdi = new javax.swing.JTextField();
        txtNoHp = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCetaksemua = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeserta = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Master Data Peserta");

        jPanel2.setBackground(new java.awt.Color(242, 101, 34));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 28));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Master Data Peserta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel2.setText("Form Peserta");

        jLabel3.setText("NIM");
        jLabel4.setText("NAMA");
        jLabel5.setText("Jenis kelamin");
        jLabel6.setText("Program Studi");
        jLabel7.setText("Nomor HP");
        jLabel8.setText("Email");

        bgJenisKelamin.add(rbLaki);
        rbLaki.setText("Laki-laki");

        bgJenisKelamin.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCetaksemua.setText("Cetak Semua");
        btnCetaksemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetaksemuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rbLaki)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbPerempuan))
                            .addComponent(txtNim, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                            .addComponent(txtNama)
                            .addComponent(txtProdi)
                            .addComponent(txtNoHp)
                            .addComponent(txtEmail)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCetaksemua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rbLaki)
                    .addComponent(rbPerempuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCetaksemua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel9.setText("Data Peserta");

        jLabel10.setText("Pencarian");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        tblPeserta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "NIM", "Nama", "JK", "Prodi", "No. HP", "Email"}
        ));
        tblPeserta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesertaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeserta);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCari)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void tblPesertaMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblPeserta.getSelectedRow();
        if (row == -1) {
            return;
        }

        selectedId = Integer.parseInt(tblPeserta.getValueAt(row, 0).toString());
        txtNim.setText(tblPeserta.getValueAt(row, 1).toString());
        txtNama.setText(tblPeserta.getValueAt(row, 2).toString());

        String jk = tblPeserta.getValueAt(row, 3).toString();
        rbLaki.setSelected("L".equalsIgnoreCase(jk) || "Laki-laki".equalsIgnoreCase(jk));
        rbPerempuan.setSelected("P".equalsIgnoreCase(jk) || "Perempuan".equalsIgnoreCase(jk));

        txtProdi.setText(tblPeserta.getValueAt(row, 4).toString());
        txtNoHp.setText(tblPeserta.getValueAt(row, 5).toString());
        txtEmail.setText(tblPeserta.getValueAt(row, 6).toString());

        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validateForm()) {
            return;
        }
        try {
            Peserta p = getFormData();
            if (dao.insert(p)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Data peserta berhasil disimpan."
                );
                refreshData();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Gagal menyimpan data: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedId <= 0) {
            JOptionPane.showMessageDialog(
                this,
                "Pilih data peserta pada tabel terlebih dahulu."
            );
            return;
        }
        if (!validateForm()) {
            return;
        }
        try {
            Peserta p = getFormData();
            if (dao.update(p)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Data peserta berhasil diubah."
                );
                refreshData();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Data tidak ditemukan atau tidak berubah."
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Gagal mengubah data: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedId <= 0) {
            JOptionPane.showMessageDialog(
                this,
                "Pilih data peserta yang akan dihapus."
            );
            return;
        }

        String nama = txtNama.getText().trim();
        int jawab = JOptionPane.showConfirmDialog(
            this,
            "Yakin menghapus peserta: " + nama + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (jawab != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (dao.delete(selectedId)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Data peserta berhasil dihapus."
                );
                refreshData();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Gagal menghapus data: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        txtCari.setText("");
        loadTable();
        clearForm();
    }

    private void btnCetaksemuaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            List<Peserta> list = dao.findAll();
            fillTable(list);
            System.out.println("=== DAFTAR SEMUA PESERTA ===");
            if (list != null) {
                for (Peserta p : list) {
                    System.out.println(p.getId() + " | " + p.getNim() + " | " + p.getNama() + " | " + p.getProdi());
                }
                JOptionPane.showMessageDialog(this, "Seluruh data peserta (" + list.size() + " baris) berhasil ditampilkan.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Gagal memuat data: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        String keyword = txtCari.getText().trim();
        try {
            if (keyword.isEmpty()) {
                loadTable();
            } else {
                fillTable(dao.search(keyword));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Pencarian gagal: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PesertaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PesertaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.ButtonGroup bgJenisKelamin;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCetaksemua;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JTable tblPeserta;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtProdi;
    // End of variables declaration
}