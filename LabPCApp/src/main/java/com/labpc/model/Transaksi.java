package com.labpc.model;

import java.time.LocalDateTime;

public class Transaksi {
    private String idTransaksi;
    private PC pc;
    private Peminjam peminjam;
    private LocalDateTime waktuMulai;
    private int durasiJam;
    private String keperluan;
    private boolean aktif;

    public Transaksi() {}

    public Transaksi(String idTransaksi, PC pc, Peminjam peminjam,
                     LocalDateTime waktuMulai, int durasiJam,
                     String keperluan, boolean aktif) {
        this.idTransaksi = idTransaksi;
        this.pc = pc;
        this.peminjam = peminjam;
        this.waktuMulai = waktuMulai;
        this.durasiJam = durasiJam;
        this.keperluan = keperluan;
        this.aktif = aktif;
    }

    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String id) { this.idTransaksi = id; }
    public PC getPc() { return pc; }
    public void setPc(PC pc) { this.pc = pc; }
    public Peminjam getPeminjam() { return peminjam; }
    public void setPeminjam(Peminjam peminjam) { this.peminjam = peminjam; }
    public LocalDateTime getWaktuMulai() { return waktuMulai; }
    public void setWaktuMulai(LocalDateTime waktuMulai) { this.waktuMulai = waktuMulai; }
    public int getDurasiJam() { return durasiJam; }
    public void setDurasiJam(int durasiJam) { this.durasiJam = durasiJam; }
    public String getKeperluan() { return keperluan; }
    public void setKeperluan(String keperluan) { this.keperluan = keperluan; }
    public boolean isAktif() { return aktif; }
    public void setAktif(boolean aktif) { this.aktif = aktif; }
}
