package model;

public class Kursus {
    private String kode;
    private String nama;
    private String level;
    private double biaya;

    // 1. Tambahkan constructor yang menerima 4 parameter
    public Kursus(String kode, String nama, String level, double biaya) {
        this.kode = kode;
        this.nama = nama;
        this.level = level;
        setBiaya(biaya); // Gunakan setter yang sudah memiliki validasi
    }

    public double getBiaya() { return biaya; }

    public void setBiaya(double biaya) {
        if (biaya < 0) {
            throw new IllegalArgumentException("Biaya tidak boleh negatif");
        }
        this.biaya = biaya;
    }

    // 2. Tambahkan method hitungBiayaSetelahDiskon
    public double hitungBiayaSetelahDiskon(double persenDiskon) {
        double nominalDiskon = this.biaya * (persenDiskon / 100.0);
        return this.biaya - nominalDiskon;
    }
}