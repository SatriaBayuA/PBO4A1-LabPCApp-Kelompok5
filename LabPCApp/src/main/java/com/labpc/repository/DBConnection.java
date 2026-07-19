package com.labpc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // =====================================================================
    // SESUAIKAN 3 BARIS INI DENGAN KONFIGURASI MYSQL KAMU
    // =====================================================================
    private static final String URL      = "jdbc:mysql://localhost:3307/labpc_db?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "GANTI_PASSWORD_ANDA_DISINI"; // <-- ganti dengan password MySQL kamu
    // Jika password kosong, isi: ""
    // =====================================================================

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver tidak ditemukan: " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
