<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LabPC - Data Peminjam</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav class="navbar">
    <a class="brand" href="${pageContext.request.contextPath}/"><span>Lab</span>PC</a>
    <a href="${pageContext.request.contextPath}/pc">Data PC</a>
    <a href="${pageContext.request.contextPath}/peminjam" class="active">Data Peminjam</a>
    <a href="${pageContext.request.contextPath}/transaksi">Transaksi</a>
</nav>

<div class="container">
    <h1 class="page-title">Data Peminjam</h1>

    <c:if test="${not empty sessionScope.error}">
        <div class="alert alert-danger">${sessionScope.error}</div>
        <c:remove var="error" scope="session"/>
    </c:if>
    <c:if test="${not empty sessionScope.sukses}">
        <div class="alert alert-success">${sessionScope.sukses}</div>
        <c:remove var="sukses" scope="session"/>
    </c:if>

    <!-- Form Tambah Peminjam -->
    <div class="card">
        <h3>Tambah Peminjam</h3>
        <form method="post" action="${pageContext.request.contextPath}/peminjam">
            <div class="form-grid">
                <div class="form-group">
                    <label>NIM <span style="color:red">*</span></label>
                    <input type="text" name="nim" class="form-control"
                           placeholder="Contoh: 2300000001" required maxlength="20">
                </div>
                <div class="form-group">
                    <label>Nama Lengkap <span style="color:red">*</span></label>
                    <input type="text" name="nama" class="form-control"
                           placeholder="Contoh: Budi Santoso" required maxlength="100">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Tambah Peminjam</button>
        </form>
    </div>

    <!-- Tabel Daftar Peminjam -->
    <div class="card">
        <h3>Daftar Peminjam Terdaftar</h3>
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>NIM</th>
                        <th>Nama</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty daftarPeminjam}">
                            <c:forEach var="p" items="${daftarPeminjam}" varStatus="loop">
                                <tr>
                                    <td style="color:#aaa">${loop.index + 1}</td>
                                    <td><strong>${p.nim}</strong></td>
                                    <td>${p.nama}</td>
                                    <td>
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/peminjam"
                                              style="display:inline;"
                                              onsubmit="return confirm('Hapus peminjam ${p.nama}?')">
                                            <input type="hidden" name="action" value="hapus">
                                            <input type="hidden" name="nim" value="${p.nim}">
                                            <button type="submit" class="btn btn-danger btn-sm">Hapus</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="empty-state">Belum ada data peminjam.</td>
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
