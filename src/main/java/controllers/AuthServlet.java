package controllers;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Role;
import models.Utilisateur;
import services.AuthService;

@WebServlet("/AuthServlet")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB avant d'écrire sur disque
		maxFileSize = 1024 * 1024 * 10, // Taille max de fichier : 10MB
		maxRequestSize = 1024 * 1024 * 50 // Taille max de la requête : 50MB
		)
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthService authService;

	// 📌 Nouveau chemin pour stocker les images
	private static final String UPLOAD_DIRECTORY = "C:/gestionScolarite/uploads";

	@Override
	public void init() throws ServletException {
		this.authService = new AuthService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("login".equals(action)) {
			connecterUtilisateur(request, response);
		} else if ("logout".equals(action)) {
			deconnecterUtilisateur(request, response);
		} else if ("register".equals(action)) { 
			inscrireUtilisateur(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
		}
	}
	private void connecterUtilisateur(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");

		HttpSession session = request.getSession();
		Utilisateur utilisateur = authService.authentifier(email, motDePasse);

		if (utilisateur != null) {
			session.setAttribute("userId", utilisateur.getId());
			session.setAttribute("userName", utilisateur.getUsername());
			session.setAttribute("role", utilisateur.getRole().name());
			session.setAttribute("photo", utilisateur.getPhoto());

			System.out.println("✅ Connexion réussie : " + email + " - Rôle : " + utilisateur.getRole());

			String redirection;
			switch (utilisateur.getRole()) {
			case ADMIN:
				redirection = request.getContextPath() + "/views/admin/dashboard.jsp";
				break;
			case ENSEIGNANT:
				redirection = request.getContextPath() + "/views/enseignant/dashboard.jsp";
				break;
			case ETUDIANT:
				redirection = request.getContextPath() + "/views/etudiant/dashboard.jsp";
				break;
			default:
				session.setAttribute("error", "Rôle inconnu !");
				redirection = request.getContextPath() + "/views/utilisateur/login.jsp";
			}

			System.out.println("🚀 Redirection vers : " + redirection);
			response.sendRedirect(redirection);
		} else {
			System.out.println("🚨 Échec de connexion pour : " + email);
			session.setAttribute("error", "Email ou mot de passe incorrect !");
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
		}
	}

	private void inscrireUtilisateur(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String roleStr = request.getParameter("role");

		String matricule = request.getParameter("matricule");
		String adresse = request.getParameter("adresse");
		String specialite = request.getParameter("specialite");
		String telephone = request.getParameter("telephone");

		try {
			Role role = Role.valueOf(roleStr.toUpperCase());

			int userId = authService.inscrireUtilisateur(nom, prenom, email, motDePasse, role, "");

			if (userId > 0) {
				boolean insertionReussie = false;
				String redirection = "";

				if (role == Role.ETUDIANT) {
					insertionReussie = authService.ajouterEtudiant(userId, matricule, prenom, nom, email, 1, new java.sql.Date(System.currentTimeMillis()), 1, 1, telephone, adresse, 1);
					redirection = "/views/etudiant/profil_etudiant.jsp";
				}
				else if (role == Role.ENSEIGNANT) {
					insertionReussie = authService.ajouterEnseignant(userId, matricule, prenom, nom, email, telephone, specialite);
					redirection = "/views/enseignant/profil_enseignant.jsp";
				}

				if (insertionReussie) {
					System.out.println("✅ Redirection vers : " + request.getContextPath() + redirection);
					response.sendRedirect(request.getContextPath() + redirection);
					return;
				} else {
					System.out.println("🚨 Erreur lors de l'ajout dans la base de données !");
					response.sendRedirect(request.getContextPath() + "/views/utilisateur/inscription.jsp?error=" + URLEncoder.encode("Échec de l'inscription !", "UTF-8"));
					return;
				}
			}

			System.out.println("🚨 Échec de l'inscription, email déjà utilisé !");
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/inscription.jsp?error=" + URLEncoder.encode("Email déjà utilisé !", "UTF-8"));

		} catch (IllegalArgumentException e) {
			System.out.println("🚨 Erreur : Rôle invalide !");
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/inscription.jsp?error=" + URLEncoder.encode("Rôle invalide !", "UTF-8"));
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("logout".equals(action)) {
			deconnecterUtilisateur(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
		}
	}


	private void deconnecterUtilisateur(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			System.out.println("Session invalidée !");
		}
		response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?message=Déconnexion réussie !");
	}
}
