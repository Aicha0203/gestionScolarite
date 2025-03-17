package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.InscriptionCoursService;

@WebServlet("/InscriptionCoursServlet")
public class InscriptionCoursServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InscriptionCoursService inscriptionCoursService;

    public InscriptionCoursServlet() {
        super();
        this.inscriptionCoursService = new InscriptionCoursService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer etudiantId = (Integer) session.getAttribute("userId");

        if (etudiantId == null) {
            response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
            return;
        }

        int matiereId = Integer.parseInt(request.getParameter("matiere_id"));

        boolean success = inscriptionCoursService.inscrireEtudiant(etudiantId, matiereId);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/views/etudiant/cours.jsp?message=Inscription réussie !");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/etudiant/cours.jsp?error=Échec de l'inscription.");
        }
    }
}
