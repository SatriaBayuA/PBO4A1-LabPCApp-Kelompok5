<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LabPC - Transaksi Peminjaman</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav class="navbar">
    <a class="brand" href="${pageContext.request.contextPath}/"><span>Lab</span>PC</a>
    <a href="${pageContext.request.contextPath}/pc">Data PC</a>
    <a href="${pageContext.request.contextPath}/peminjam">Data Peminjam</a>
    <a href="${pageContext.request.contextPath}/transaksi" class="active">Transaksi</a>
</nav>

<div class="container">
    <h1 class="page-title">Transaksi Peminjaman</h1>

    <c:if test="${not empty sessionScope.error}">
        <div class="alert alert-danger">${sessionScope.error}</div>
        <c:remove var="error" scope="session"/>
    </c:if>
    <c:if test="${not empty sessionScope.sukses}">
        <div class="alert alert-success">${sessionScope.sukses}</div>
        <c:remove var="sukses" scope="session"/>
    </c:if>

    <!-- Form Buat Peminjaman -->
    <div class="card">
        <h3>Buat Peminjaman Baru</h3>
        <form method="post" action="${pageContext.request.contextPath}/transaksi">
            <div class="form-grid">
                <div class="form-group">
                    <label>Kode PC <span style="color:red">*</span></label>
                    <input type="text" name="kodePC" class="form-control"
                           placeholder="Contoh: PC-01" required maxlength="20">
                </div>
                <div class="form-group">
                    <label>Durasi (Jam) <span style="color:red">*</span></label>
                    <input type="number" name="durasiJam" class="form-control"
                           placeholder="Contoh: 2" min="1" required>
                </div>
                <div class="form-group">
                    <label>NIM Peminjam <span style="color:red">*</span></label>
                    <input type="text" name="nim" class="form-control"
                           placeholder="Contoh: 2300000001" required maxlength="20">
                </div>
                <div class="form-group">
                    <label>Nama Peminjam <span style="color:red">*</span></label>
                    <input type="text" name="nama" class="form-control"
                           placeholder="Contoh: Budi Santoso" required maxlength="100">
                </div>
                <div class="form-group span-2">
                    <label>Keperluan</label>
                    <input type="text" name="keperluan" class="form-control"
                           placeholder="Contoh: Praktikum Pemrograman Web" maxlength="200">
                </div>
            </div>
            <button type="submit" class="btn btn-success">Pinjam Sekarang</button>
        </form>
    </div>

    <!-- Riwayat Transaksi -->
    <div class="card">
        <h3>Riwayat Transaksi</h3>
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>ID Transaksi</th>
                        <th>Kode PC</th>
                        <th>NIM</th>
                        <th>Nama</th>
                        <th>Waktu Mulai</th>
                        <th>Durasi</th>
                        <th>Keperluan</th>
                        <th>Status</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty daftarTransaksi}">
                            <c:forEach var="tr" items="${daftarTransaksi}" varStatus="loop">
                                <tr>
                                    <td style="color:#aaa">${loop.index + 1}</td>
                                    <td style="font-size:0.75rem; color:#999; font-family:monospace;">
                                        ${tr.idTransaksi.substring(0,8)}...
                                    </td>
                                    <td><strong>${tr.pc.kodePC}</strong></td>
                                    <td>${tr.peminjam.nim}</td>
                                    <td>${tr.peminjam.nama}</td>
                                    <td style="font-size:0.85rem;">${tr.waktuMulai}</td>
                                    <td>${tr.durasiJam} jam</td>
                                    <td style="font-size:0.85rem;">${tr.keperluan}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${tr.aktif}">
                                                <span class="badge badge-aktif">AKTIF</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-selesai">SELESAI</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${tr.aktif}">
                                            <form method="post"
                                                  action="${pageContext.request.contextPath}/transaksi"
                                                  style="display:inline;"
                                                  onsubmit="return confirm('Kembalikan PC ${tr.pc.kodePC}?')">
                                                <input type="hidden" name="action" value="kembali">
                                                <input type="hidden" name="idTransaksi" value="${tr.idTransaksi}">
                                                <button type="submit" class="btn btn-warning btn-sm">Kembalikan</button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="10" class="empty-state">Belum ada transaksi peminjaman.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
