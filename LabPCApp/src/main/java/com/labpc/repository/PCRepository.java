package com.labpc.repository;

import com.labpc.model.PC;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PCRepository {

    public void tambah(PC pc) throws SQLException {
        String sql = "INSERT INTO pc (kode_pc, status) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pc.getKodePC());
            ps.setString(2, pc.getStatus());
            ps.executeUpdate();
        }
    }

    public List<PC> getAll() throws SQLException {
        List<PC> list = new ArrayList<>();
        String sql = "SELECT * FROM pc ORDER BY kode_pc";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PC(rs.getString("kode_pc"), rs.getString("status")));
            }
        }
        return list;
    }

    public PC findByKode(String kode) throws SQLException {
        String sql = "SELECT * FROM pc WHERE kode_pc = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PC(rs.getString("kode_pc"), rs.getString("status"));
            }
        }
        return null;
    }

    public boolean isKodeExist(String kode) throws SQLException {
        return findByKode(kode) != null;
    }

    public void updateStatus(String kode, String status) throws SQLException {
        String sql = "UPDATE pc SET status = ? WHERE kode_pc = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, kode);
            ps.executeUpdate();
        }
    }

    public void hapus(String kode) throws SQLException {
        String sql = "DELETE FROM pc WHERE kode_pc = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            ps.executeUpdate();
        }
    }
}
