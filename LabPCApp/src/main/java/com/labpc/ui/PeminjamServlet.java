package com.labpc.ui;

import com.labpc.exception.DataTidakValidException;
import com.labpc.service.PeminjamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/peminjam")
public class PeminjamServlet extends HttpServlet {
    private final PeminjamService service = new PeminjamService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("daftarPeminjam", service.getDaftarPeminjam());
        } catch (SQLException e) {
            req.getSession().setAttribute("error", "Gagal memuat data peminjam: " + e.getMessage());
        }
        req.getRequestDispatcher("/peminjam/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("hapus".equals(action)) {
            try {
                service.hapusPeminjam(req.getParameter("nim"));
                req.getSession().setAttribute("sukses", "Peminjam berhasil dihapus.");
            } catch (SQLException e) {
                req.getSession().setAttribute("error", "Gagal menghapus peminjam: " + e.getMessage());
            }
        } else {
            // action = tambah
            try {
                service.tambahPeminjam(req.getParameter("nim"), req.getParameter("nama"));
                req.getSession().setAttribute("sukses", "Peminjam berhasil ditambahkan.");
            } catch (DataTidakValidException e) {
                req.getSession().setAttribute("error", e.getMessage());
            } catch (SQLException e) {
                req.getSession().setAttribute("error", "Gagal menyimpan peminjam: " + e.getMessage());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/peminjam");
    }
}
