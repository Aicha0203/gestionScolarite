package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Paiement;
import services.PaiementService;

@WebServlet("/PaiementServlet")
public class PaiementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaiementService paiementService;

    public PaiementServlet() {
        super();
        paiementService = new PaiementService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int etudiantId = Integer.parseInt(request.getParameter("etudiantId"));
        List<Paiement> paiements = paiementService.getPaiementsByEtudiant(etudiantId);
        request.setAttribute("paiements", paiements);
        request.getRequestDispatcher("/views/etudiant/finances.jsp").forward(request, response);
    }
}
