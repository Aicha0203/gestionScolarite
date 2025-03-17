package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Enseignant;
import services.EnseignantService;

@WebServlet("/EnseignantServlet")
public class EnseignantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EnseignantService enseignantService;

    public EnseignantServlet() {
        super();
        enseignantService = new EnseignantService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?error=Session expirée. Veuillez vous reconnecter.");
            return;
        }

        Enseignant enseignant = enseignantService.getEnseignantById(userId);
        request.setAttribute("enseignant", enseignant);
        request.getRequestDispatcher("/views/utilisateur/profil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?error=Session expirée. Veuillez vous reconnecter.");
            return;
        }

        Enseignant enseignant = new Enseignant();
        enseignant.setMatricule(request.getParameter("matricule"));
        enseignant.setPrenom(request.getParameter("prenom"));
        enseignant.setNom(request.getParameter("nom"));
        enseignant.setTelephone(request.getParameter("telephone"));
        enseignant.setSpecialite(request.getParameter("specialite"));

        boolean success = enseignantService.mettreAJourEnseignant(userId, enseignant);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/views/utilisateur/profil.jsp?success=1");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/utilisateur/profil.jsp?error=Échec de la mise à jour.");
        }
    }
}
