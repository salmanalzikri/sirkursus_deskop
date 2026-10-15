package app;

import model.Admin;
import model.Instruktur;
import model.Orang;
import model.Peserta;
import java.util.ArrayList;
import java.util.List;

public class DemoPolymorphism {
    public static void main(String[] args) {
        List<Orang> daftar = new ArrayList<>();
        
        // Memasukkan data valid ke dalam list
        daftar.add(new Peserta(1, "Abdul Reyhan Syaidina", "081234567890", "2924020", "Informatika"));
        daftar.add(new Instruktur(2, "Dina Pratama", "081211110001", "Java Desktop"));
        daftar.add(new Admin(3, "Budi Santoso", "081199998888", "Superadmin"));

        System.out.println("--- Cetak Semua (Polymorphism) ---");
        cetakSemua(daftar);

        System.out.println("\n--- Eksperimen instanceof ---");
        for (Orang o : daftar) {
            if (o instanceof Peserta) {
                Peserta p = (Peserta) o;
                System.out.println("NIM khusus peserta ditemukan: " + p.getNim());
            }
        }
    }

    // Praktikum F: Polymorphic Argument
    public static void tampilkanInfo(Orang orang) {
        System.out.println(orang.getInfo());
    }

    public static void cetakSemua(List<Orang> daftar) {
        for (Orang o : daftar) {
            tampilkanInfo(o);
        }
    }
}