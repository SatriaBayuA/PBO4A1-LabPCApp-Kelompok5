package com.labpc.service;

import com.labpc.exception.*;
import com.labpc.model.*;
import com.labpc.repository.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TransaksiService {
    private final PCRepository        pcRepo = new PCRepository();
    private final PeminjamRepository  pmRepo = new PeminjamRepository();
    private final TransaksiRepository trRepo = new TransaksiRepository();

    public void buatPeminjaman(String kodePC, String nim, String nama,
                               int durasiJam, String keperluan)
            throws DataTidakValidException, PCTidakTersediaException,
                   DoubleBookingException, SQLException {

        // Validasi 1: kodePC tidak boleh kosong
        if (kodePC == null || kodePC.trim().isEmpty())
            throw new DataTidakValidException("Kode PC tidak boleh kosong.");

        // Validasi 2: NIM tidak boleh kosong
        if (nim == null || nim.trim().isEmpty())
            throw new DataTidakValidException("NIM tidak boleh kosong.");

        // Validasi 3: durasiJam harus lebih dari 0
        if (durasiJam <= 0)
            throw new DataTidakValidException("Durasi jam harus lebih dari 0.");

        PC pc = pcRepo.findByKode(kodePC.trim());
        if (pc == null)
            throw new DataTidakValidException("PC dengan kode '" + kodePC + "' tidak ditemukan.");

        // Validasi 4: PC harus berstatus TERSEDIA
        if (!"TERSEDIA".equals(pc.getStatus()))
            throw new PCTidakTersediaException("PC " + kodePC + " sedang berstatus " + pc.getStatus() + ".");

        // Validasi 5: tidak boleh double booking
        if (trRepo.adaTransaksiAktif(kodePC.trim()))
            throw new DoubleBookingException("PC " + kodePC + " masih memiliki transaksi aktif.");

        // Simpan peminjam jika belum ada di database
        pmRepo.simpan(new Peminjam(nim.trim(), nama.trim()));

        // Buat dan simpan transaksi baru
        Transaksi t = new Transaksi(
            UUID.randomUUID().toString(),
            pc,
            new Peminjam(nim.trim(), nama.trim()),
            LocalDateTime.now(),
            durasiJam,
            keperluan,
            true
        );
        trRepo.simpan(t);

        // Update status PC menjadi DIPINJAM
        pcRepo.updateStatus(kodePC.trim(), "DIPINJAM");
    }

    public void kembalikan(String idTransaksi)
            throws TransaksiTidakAktifException, SQLException {

        Transaksi t = trRepo.findById(idTransaksi);

        // Validasi 6: hanya transaksi aktif yang bisa dikembalikan
        if (t == null || !t.isAktif())
            throw new TransaksiTidakAktifException("Transaksi tidak ditemukan atau sudah selesai.");

        trRepo.tutupTransaksi(idTransaksi);

        // Update status PC kembali menjadi TERSEDIA
        pcRepo.updateStatus(t.getPc().getKodePC(), "TERSEDIA");
    }

    public List<Transaksi> getRiwayat() throws SQLException {
        return trRepo.getAll();
    }
}
