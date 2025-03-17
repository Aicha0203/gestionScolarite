package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Matiere;
import services.CoursService;
import services.InscriptionCoursService;

@WebServlet("/CoursServlet")
public class CoursServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CoursService coursService;
    private InscriptionCoursService inscriptionCoursService;

    public CoursServlet() {
        super();
        coursService = new CoursService();
        inscriptionCoursService = new InscriptionCoursService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer etudiantId = (Integer) session.getAttribute("userId");

        if (etudiantId == null) {
            response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
            return;
        }

        int programmeId = Integer.parseInt(request.getParameter("programmeId"));

        // 📌 Récupération des matières disponibles pour le programme de l'étudiant
        List<Matiere> coursDisponibles = coursService.getCoursByProgramme(programmeId);

        // 📌 Récupération des matières déjà inscrites
        List<Matiere> coursInscrits = inscriptionCoursService.getCoursInscrits(etudiantId);

        // 📌 Passage des données à la JSP
        request.setAttribute("coursDisponibles", coursDisponibles);
        request.setAttribute("coursInscrits", coursInscrits);

        request.getRequestDispatcher("/views/etudiant/cours.jsp").forward(request, response);
    }
}
