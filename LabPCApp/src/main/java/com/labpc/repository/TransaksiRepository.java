package com.labpc.repository;

import com.labpc.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiRepository {

    public void simpan(Transaksi t) throws SQLException {
        String sql = "INSERT INTO transaksi (id_transaksi, kode_pc, nim, waktu_mulai, durasi_jam, keperluan, aktif) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getIdTransaksi());
            ps.setString(2, t.getPc().getKodePC());
            ps.setString(3, t.getPeminjam().getNim());
            ps.setTimestamp(4, Timestamp.valueOf(t.getWaktuMulai()));
            ps.setInt(5, t.getDurasiJam());
            ps.setString(6, t.getKeperluan());
            ps.setBoolean(7, t.isAktif());
            ps.executeUpdate();
        }
    }

    public List<Transaksi> getAll() throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT t.*, p.nama, pc.status "
                   + "FROM transaksi t "
                   + "JOIN peminjam p ON t.nim = p.nim "
                   + "JOIN pc ON t.kode_pc = pc.kode_pc "
                   + "ORDER BY t.waktu_mulai DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PC pc = new PC(rs.getString("kode_pc"), rs.getString("status"));
                Peminjam peminjam = new Peminjam(rs.getString("nim"), rs.getString("nama"));
                list.add(new Transaksi(
                    rs.getString("id_transaksi"),
                    pc, peminjam,
                    rs.getTimestamp("waktu_mulai").toLocalDateTime(),
                    rs.getInt("durasi_jam"),
                    rs.getString("keperluan"),
                    rs.getBoolean("aktif")
                ));
            }
        }
        return list;
    }

    public boolean adaTransaksiAktif(String kodePC) throws SQLException {
        String sql = "SELECT COUNT(*) FROM transaksi WHERE kode_pc = ? AND aktif = TRUE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodePC);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public Transaksi findById(String id) throws SQLException {
        String sql = "SELECT t.*, p.nama, pc.status "
                   + "FROM transaksi t "
                   + "JOIN peminjam p ON t.nim = p.nim "
                   + "JOIN pc ON t.kode_pc = pc.kode_pc "
                   + "WHERE t.id_transaksi = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PC pc = new PC(rs.getString("kode_pc"), rs.getString("status"));
                Peminjam peminjam = new Peminjam(rs.getString("nim"), rs.getString("nama"));
                return new Transaksi(
                    rs.getString("id_transaksi"),
                    pc, peminjam,
                    rs.getTimestamp("waktu_mulai").toLocalDateTime(),
                    rs.getInt("durasi_jam"),
                    rs.getString("keperluan"),
                    rs.getBoolean("aktif")
                );
            }
        }
        return null;
    }

    public void tutupTransaksi(String id) throws SQLException {
        String sql = "UPDATE transaksi SET aktif = FALSE WHERE id_transaksi = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
}
