<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LabPC - Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav class="navbar">
    <a class="brand" href="${pageContext.request.contextPath}/"><span>Lab</span>PC</a>
    <a href="${pageContext.request.contextPath}/pc">Data pc</a>
    <a href="${pageContext.request.contextPath}/peminjam">Data Peminjam</a>
    <a href="${pageContext.request.contextPath}/transaksi">Transaksi</a>
</nav>

<div class="container">
    <h1 class="page-title">Dashboard</h1>

    <div class="stats-grid">
        <div class="stat-card blue">
            <div class="stat-label">Total PC Terdaftar</div>
            <div class="stat-value">—</div>
        </div>
        <div class="stat-card green">
            <div class="stat-label">PC Tersedia</div>
            <div class="stat-value">—</div>
        </div>
        <div class="stat-card orange">
            <div class="stat-label">Sedang Dipinjam</div>
            <div class="stat-value">—</div>
        </div>
        <div class="stat-card red">
            <div class="stat-label">Maintenance</div>
            <div class="stat-value">—</div>
        </div>
    </div>

    <div class="card">
        <h3>Selamat Datang di Aplikasi Peminjaman Lab PC</h3>
        <p style="color:#666; line-height:1.7; margin-bottom:1.2rem;">
            Sistem ini digunakan untuk mengelola peminjaman komputer di laboratorium.
            Gunakan menu navigasi di atas untuk mengakses fitur yang tersedia.
        </p>
        <div style="display:flex; gap:0.8rem; flex-wrap:wrap;">
            <a href="${pageContext.request.contextPath}/pc" class="btn btn-primary">Kelola Data PC</a>
            <a href="${pageContext.request.contextPath}/peminjam" class="btn btn-info">Kelola Peminjam</a>
            <a href="${pageContext.request.contextPath}/transaksi" class="btn btn-success">Transaksi Peminjaman</a>
        </div>
    </div>

    <div class="card">
        <h3>Panduan Singkat</h3>
        <ol style="padding-left:1.2rem; color:#555; line-height:2; font-size:0.9rem;">
            <li>Daftarkan unit PC di menu <strong>Data PC</strong> terlebih dahulu.</li>
            <li>Buat transaksi peminjaman di menu <strong>Transaksi</strong> dengan mengisi kode PC, NIM, nama, dan durasi.</li>
            <li>Setelah selesai digunakan, klik tombol <strong>Kembalikan</strong> di halaman riwayat transaksi.</li>
            <li>Status PC akan otomatis berubah kembali menjadi <strong>TERSEDIA</strong>.</li>
        </ol>
    </div>
</div>

</body>
</html>
