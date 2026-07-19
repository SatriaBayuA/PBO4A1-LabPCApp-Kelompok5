package com.labpc.ui;

import com.labpc.exception.*;
import com.labpc.service.TransaksiService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/transaksi")
public class TransaksiServlet extends HttpServlet {
    private final TransaksiService service = new TransaksiService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("daftarTransaksi", service.getRiwayat());
        } catch (SQLException e) {
            req.getSession().setAttribute("error", "Gagal memuat riwayat transaksi: " + e.getMessage());
        }
        req.getRequestDispatcher("/transaksi/riwayat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("kembali".equals(action)) {
            try {
                service.kembalikan(req.getParameter("idTransaksi"));
                req.getSession().setAttribute("sukses", "PC berhasil dikembalikan.");
            } catch (TransaksiTidakAktifException e) {
                req.getSession().setAttribute("error", e.getMessage());
            } catch (SQLException e) {
                req.getSession().setAttribute("error", "Gagal mengembalikan PC: " + e.getMessage());
            }
        } else {
            // action = pinjam
            try {
                int durasiJam = Integer.parseInt(req.getParameter("durasiJam"));
                service.buatPeminjaman(
                    req.getParameter("kodePC"),
                    req.getParameter("nim"),
                    req.getParameter("nama"),
                    durasiJam,
                    req.getParameter("keperluan")
                );
                req.getSession().setAttribute("sukses", "Peminjaman berhasil dibuat.");
            } catch (DataTidakValidException | PCTidakTersediaException | DoubleBookingException e) {
                req.getSession().setAttribute("error", e.getMessage());
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("error", "Durasi jam harus berupa angka bulat.");
            } catch (SQLException e) {
                req.getSession().setAttribute("error", "Gagal menyimpan transaksi: " + e.getMessage());
            }
        }
        resp.sendRedirect(req.getContextPath() + "/transaksi");
    }
}
