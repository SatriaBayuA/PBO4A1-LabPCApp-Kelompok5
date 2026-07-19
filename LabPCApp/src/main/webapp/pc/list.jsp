<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LabPC - Data PC</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav class="navbar">
    <a class="brand" href="${pageContext.request.contextPath}/"><span>Lab</span>PC</a>
    <a href="${pageContext.request.contextPath}/pc" class="active">Data PC</a>
    <a href="${pageContext.request.contextPath}/peminjam">Data Peminjam</a>
    <a href="${pageContext.request.contextPath}/transaksi">Transaksi</a>
</nav>

<div class="container">
    <h1 class="page-title">Data PC</h1>

    <c:if test="${not empty sessionScope.error}">
        <div class="alert alert-danger">${sessionScope.error}</div>
        <c:remove var="error" scope="session"/>
    </c:if>
    <c:if test="${not empty sessionScope.sukses}">
        <div class="alert alert-success">${sessionScope.sukses}</div>
        <c:remove var="sukses" scope="session"/>
    </c:if>

    <!-- Form Tambah PC -->
    <div class="card">
        <h3>Tambah PC Baru</h3>
        <form method="post" action="${pageContext.request.contextPath}/pc">
            <div class="form-grid">
                <div class="form-group">
                    <label>Kode PC <span style="color:red">*</span></label>
                    <input type="text" name="kodePC" class="form-control"
                           placeholder="Contoh: PC-01" required maxlength="20">
                </div>
                <div class="form-group">
                    <label>Status Awal <span style="color:red">*</span></label>
                    <select name="status" class="form-control">
                        <option value="TERSEDIA">TERSEDIA</option>
                        <option value="MAINTENANCE">MAINTENANCE</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Tambah PC</button>
        </form>
    </div>

    <!-- Tabel Daftar PC -->
    <div class="card">
        <h3>Daftar PC Terdaftar</h3>
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Kode PC</th>
                        <th>Status</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty daftarPC}">
                            <c:forEach var="pc" items="${daftarPC}" varStatus="loop">
                                <tr>
                                    <td style="color:#aaa">${loop.index + 1}</td>
                                    <td><strong>${pc.kodePC}</strong></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${pc.status == 'TERSEDIA'}">
                                                <span class="badge badge-tersedia">TERSEDIA</span>
                                            </c:when>
                                            <c:when test="${pc.status == 'DIPINJAM'}">
                                                <span class="badge badge-dipinjam">DIPINJAM</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-maintenance">MAINTENANCE</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/pc"
                                              style="display:inline;"
                                              onsubmit="return confirm('Hapus PC ${pc.kodePC}? Pastikan tidak ada transaksi aktif.')">
                                            <input type="hidden" name="action" value="hapus">
                                            <input type="hidden" name="kodePC" value="${pc.kodePC}">
                                            <button type="submit" class="btn btn-danger btn-sm">Hapus</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="empty-state">Belum ada data PC. Tambahkan PC baru di atas.</td>
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
