package com.labpc.service;

import com.labpc.exception.DataTidakValidException;
import com.labpc.model.PC;
import com.labpc.repository.PCRepository;
import java.sql.SQLException;
import java.util.List;

public class PCService {
    private final PCRepository repo = new PCRepository();

    public void tambahPC(String kodePC, String status)
            throws DataTidakValidException, SQLException {

        // Validasi 1: kodePC tidak boleh kosong
        if (kodePC == null || kodePC.trim().isEmpty())
            throw new DataTidakValidException("Kode PC tidak boleh kosong.");

        // Validasi 2: kodePC harus unik
        if (repo.isKodeExist(kodePC.trim()))
            throw new DataTidakValidException("Kode PC '" + kodePC + "' sudah terdaftar.");

        repo.tambah(new PC(kodePC.trim(), status));
    }

    public List<PC> getDaftarPC() throws SQLException {
        return repo.getAll();
    }

    public void hapusPC(String kodePC) throws SQLException {
        repo.hapus(kodePC);
    }
}
