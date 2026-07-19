package com.labpc.ui;

import com.labpc.exception.DataTidakValidException;
import com.labpc.service.PCService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/pc")
public class PCServlet extends HttpServlet {
    private final PCService service = new PCService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("daftarPC", service.getDaftarPC());
        } catch (SQLException e) {
            req.getSession().setAttribute("error", "Gagal memuat data PC: " + e.getMessage());
        }
        req.getRequestDispatcher("/pc/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("hapus".equals(action)) {
            try {
                service.hapusPC(req.getParameter("kodePC"));
                req.getSession().setAttribute("sukses", "PC berhasil dihapus.");
            } catch (SQLException e) {
                req.getSession().setAttribute("error", "Gagal menghapus PC: " + e.getMessage());
            }
        } else {
            // action = tambah
            try {
                service.tambahPC(req.getParameter("kodePC"), req.getParameter("status"));
                req.getSession().setAttribute("sukses", "PC berhasil ditambahkan.");
            } catch (DataTidakValidException e) {
                req.getSession().setAttribute("error", e.getMessage());
            } catch (SQLException e) {
                req.getSession().setAttribute("error", "Gagal menyimpan PC: " + e.getMessage());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/pc");
    }
}
