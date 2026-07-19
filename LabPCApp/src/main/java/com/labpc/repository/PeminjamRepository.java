package com.labpc.repository;

import com.labpc.model.Peminjam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeminjamRepository {

    public void simpan(Peminjam p) throws SQLException {
        if (findByNim(p.getNim()) != null) return; // sudah ada, skip insert
        String sql = "INSERT INTO peminjam (nim, nama) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.executeUpdate();
        }
    }

    public List<Peminjam> getAll() throws SQLException {
        List<Peminjam> list = new ArrayList<>();
        String sql = "SELECT * FROM peminjam ORDER BY nim";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Peminjam(rs.getString("nim"), rs.getString("nama")));
            }
        }
        return list;
    }

    public Peminjam findByNim(String nim) throws SQLException {
        String sql = "SELECT * FROM peminjam WHERE nim = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Peminjam(rs.getString("nim"), rs.getString("nama"));
            }
        }
        return null;
    }

    public void hapus(String nim) throws SQLException {
        String sql = "DELETE FROM peminjam WHERE nim = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);
            ps.executeUpdate();
        }
    }
}
