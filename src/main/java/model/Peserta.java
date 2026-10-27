
package model;

public class Peserta extends Orang {

    private String nim;
    private String prodi;
    private String jenisKelamin;
    private String email;

    // Constructor untuk program lama
    public Peserta(int id, String nama, String noHp,
                   String nim, String prodi) {
        this(id, nama, noHp, nim, prodi, "L", "");
    }

    // Constructor lengkap untuk database
    public Peserta(int id, String nama, String noHp,
                   String nim, String prodi,
                   String jenisKelamin, String email) {

        super(id, nama, noHp);

        setNim(nim);
        setProdi(prodi);
        setJenisKelamin(jenisKelamin);
        setEmail(email);
    }

    // Getter NIM
    public String getNim() {
        return nim;
    }

    // Getter program studi
    public String getProdi() {
        return prodi;
    }

    // Getter jenis kelamin
    public String getJenisKelamin() {
        return jenisKelamin;
    }

    // Getter email
    public String getEmail() {
        return email;
    }

    // Setter NIM
    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "NIM wajib diisi");
        }

        this.nim = nim.trim();
    }

    // Setter program studi
    public void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Prodi wajib diisi");
        }

        this.prodi = prodi.trim();
    }

    // Setter jenis kelamin
    public void setJenisKelamin(String jenisKelamin) {
        if (!"L".equals(jenisKelamin)
                && !"P".equals(jenisKelamin)) {
            throw new IllegalArgumentException(
                    "Jenis kelamin harus L atau P");
        }

        this.jenisKelamin = jenisKelamin;
    }

    // Setter email
    public void setEmail(String email) {
        String nilai = email == null ? "" : email.trim();

        if (!nilai.isEmpty()
                && !nilai.matches(
                        "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException(
                    "Format email tidak valid");
        }

        this.email = nilai;
    }

    // Menampilkan informasi peserta
    @Override
    public String getInfo() {
        return "PESERTA " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi()
                + " | Jenis Kelamin: " + getJenisKelamin()
                + " | Email: "
                + (getEmail().isEmpty() ? "-" : getEmail());
    }
}
