package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Utilisateur;
import services.AuthService;

@WebServlet("/ProfilEnseignantServlet")
public class ProfilEnseignantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthService authService;

	@Override
	public void init() {
		this.authService = new AuthService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
			return;
		}

		String matricule = request.getParameter("matricule");
		String specialite = request.getParameter("specialite");
		String telephone = request.getParameter("telephone");

		Utilisateur utilisateur = authService.getUtilisateurById(userId);
		if (utilisateur == null) {
			response.sendRedirect(request.getContextPath() + "/views/enseignant/profil_enseignant.jsp?error=Erreur email introuvable.");
			return;
		}
		String email = utilisateur.getEmail();

		boolean success = authService.ajouterEnseignant(userId, matricule, "", "", email, telephone, specialite);

		if (success) {
			response.sendRedirect(request.getContextPath() + "/views/enseignant/dashboard.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/views/enseignant/profil_enseignant.jsp?error=Échec de l'enregistrement");
		}
	}


}
