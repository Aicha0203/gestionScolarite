package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Etudiant;
import services.EtudiantService;

@WebServlet("/EtudiantServlet")
public class EtudiantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EtudiantService etudiantService;

    public EtudiantServlet() {
        super();
        etudiantService = new EtudiantService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        request.setAttribute("etudiants", etudiants);
        request.getRequestDispatcher("/views/etudiant/dashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String telephone = request.getParameter("telephone");
            String adresse = request.getParameter("adresse");
            etudiantService.updateEtudiant(id, telephone, adresse);
        }

        response.sendRedirect("EtudiantServlet");
    }
}
