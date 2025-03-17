package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.Enseignant;
import models.Etudiant;
import models.Utilisateur;
import services.EnseignantService;
import services.EtudiantService;
import services.ProfilService;

@WebServlet("/ProfilServlet")
@MultipartConfig(maxFileSize = 16177215)
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfilService profilService;
	private EtudiantService etudiantService;
	private EnseignantService enseignantService;


	@Override
	public void init() {
		profilService = new ProfilService();
	    etudiantService = new EtudiantService();
	    enseignantService = new EnseignantService();
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Integer userId = (Integer) session.getAttribute("userId");
	    String role = (String) session.getAttribute("role");

	    if (userId == null || role == null) {
	        response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?error=Session expirée. Veuillez vous reconnecter.");
	        return;
	    }

	    Utilisateur utilisateur;
	    
	    if ("ETUDIANT".equals(role)) {
	        utilisateur = etudiantService.getEtudiantById(userId);
	    } else if ("ENSEIGNANT".equals(role)) {
	        utilisateur = enseignantService.getEnseignantById(userId);
	    } else {
	        response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?error=Accès non autorisé.");
	        return;
	    }

	    request.setAttribute("utilisateur", utilisateur);
	    request.getRequestDispatcher("/views/utilisateur/profil.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String role = (String) session.getAttribute("role");

		if (userId == null || role == null) {
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?error=Session expirée. Veuillez vous reconnecter.");
			return;
		}

		boolean success = false;

		if ("ETUDIANT".equals(role)) {
			Etudiant etudiant = new Etudiant();
			etudiant.setTelephone(request.getParameter("telephone"));
			etudiant.setAdresseComplete(request.getParameter("adresse"));
			success = profilService.updateEtudiantProfil(userId, etudiant);
		} else if ("ENSEIGNANT".equals(role)) {
			Enseignant enseignant = new Enseignant();
			enseignant.setTelephone(request.getParameter("telephone"));
			enseignant.setSpecialite(request.getParameter("specialite"));
			success = profilService.updateEnseignantProfil(userId, enseignant);
		}

		if (success) {
			response.sendRedirect(request.getContextPath() + "/ProfilServlet?success=1");
		} else {
			response.sendRedirect(request.getContextPath() + "/ProfilServlet?error=Échec de la mise à jour.");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?error=Session expirée. Veuillez vous reconnecter.");
			return;
		}

		Part filePart = request.getPart("photo");
		String fileName = filePart.getSubmittedFileName();

		if (fileName != null && !fileName.isEmpty()) {
			String uploadPath = "assets/uploads/" + fileName; 
			filePart.write(getServletContext().getRealPath("/") + uploadPath);

			boolean success = profilService.updatePhotoProfil(userId, uploadPath);

			if (success) {
				response.sendRedirect(request.getContextPath() + "/ProfilServlet?success=1");
			} else {
				response.sendRedirect(request.getContextPath() + "/ProfilServlet?error=Échec de la mise à jour de la photo.");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/ProfilServlet?error=Aucune photo sélectionnée.");
		}
	}
}
