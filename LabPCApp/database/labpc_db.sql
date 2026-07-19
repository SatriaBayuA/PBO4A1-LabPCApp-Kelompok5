-- ============================================================
-- FILE   : labpc_db.sql
-- FUNGSI : Membuat database dan tabel untuk Aplikasi Peminjaman Lab PC
-- CARA PAKAI: Buka MySQL Workbench > File > Open SQL Script >
--             pilih file ini > klik tombol Run (petir)
-- ============================================================

-- Buat database jika belum ada
CREATE DATABASE IF NOT EXISTS labpc_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Gunakan database labpc_db
USE labpc_db;

-- ============================================================
-- TABEL 1: pc
-- Menyimpan data unit komputer di laboratorium
-- ============================================================
CREATE TABLE IF NOT EXISTS pc (
    kode_pc  VARCHAR(20)                              NOT NULL,
    status   ENUM('TERSEDIA','DIPINJAM','MAINTENANCE') NOT NULL DEFAULT 'TERSEDIA',
    PRIMARY KEY (kode_pc)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================
-- TABEL 2: peminjam
-- Menyimpan data mahasiswa yang pernah meminjam
-- ============================================================
CREATE TABLE IF NOT EXISTS peminjam (
    nim   VARCHAR(20)  NOT NULL,
    nama  VARCHAR(100) NOT NULL,
    PRIMARY KEY (nim)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================
-- TABEL 3: transaksi
-- Menyimpan semua riwayat peminjaman PC
-- ============================================================
CREATE TABLE IF NOT EXISTS transaksi (
    id_transaksi VARCHAR(50)  NOT NULL,
    kode_pc      VARCHAR(20)  NOT NULL,
    nim          VARCHAR(20)  NOT NULL,
    waktu_mulai  DATETIME     NOT NULL,
    durasi_jam   INT          NOT NULL,
    keperluan    VARCHAR(200)     NULL,
    aktif        BOOLEAN      NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id_transaksi),
    FOREIGN KEY (kode_pc) REFERENCES pc(kode_pc),
    FOREIGN KEY (nim)     REFERENCES peminjam(nim)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================
-- DATA AWAL (opsional, bisa dihapus)
-- ============================================================
INSERT IGNORE INTO pc (kode_pc, status) VALUES
    ('PC-01', 'TERSEDIA'),
    ('PC-02', 'TERSEDIA'),
    ('PC-03', 'TERSEDIA'),
    ('PC-04', 'MAINTENANCE'),
    ('PC-05', 'TERSEDIA');

-- ============================================================
-- Selesai. Jalankan aplikasi dan buka:
-- http://localhost:8080/LabPCApp/
-- ============================================================
