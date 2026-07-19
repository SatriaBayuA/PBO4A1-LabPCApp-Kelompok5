package com.labpc.service;

import com.labpc.exception.DataTidakValidException;
import com.labpc.model.Peminjam;
import com.labpc.repository.PeminjamRepository;
import java.sql.SQLException;
import java.util.List;

public class PeminjamService {
    private final PeminjamRepository repo = new PeminjamRepository();

    public void tambahPeminjam(String nim, String nama)
            throws DataTidakValidException, SQLException {

        // Validasi 3: NIM tidak boleh kosong
        if (nim == null || nim.trim().isEmpty())
            throw new DataTidakValidException("NIM tidak boleh kosong.");

        // Validasi 4: Nama tidak boleh kosong
        if (nama == null || nama.trim().isEmpty())
            throw new DataTidakValidException("Nama tidak boleh kosong.");

        repo.simpan(new Peminjam(nim.trim(), nama.trim()));
    }

    public List<Peminjam> getDaftarPeminjam() throws SQLException {
        return repo.getAll();
    }

    public void hapusPeminjam(String nim) throws SQLException {
        repo.hapus(nim);
    }
}
