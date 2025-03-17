package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Facture;
import services.FactureService;

@WebServlet("/FactureServlet")
public class FactureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FactureService factureService;

    public FactureServlet() {
        super();
        factureService = new FactureService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int etudiantId = Integer.parseInt(request.getParameter("etudiantId"));
        List<Facture> factures = factureService.getFacturesByEtudiant(etudiantId);
        request.setAttribute("factures", factures);
        request.getRequestDispatcher("/views/etudiant/finances.jsp").forward(request, response);
    }
}
