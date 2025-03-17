package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Genre;
import models.Pays;
import models.Nationalite;
import models.Diplome;
import services.ProfilService;

@WebServlet("/ProfilEtudiantServlet")
public class ProfilEtudiantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfilService profilService;

    public ProfilEtudiantServlet() {
        super();
        this.profilService = new ProfilService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre> genres = profilService.getAllGenres();
        List<Pays> paysList = profilService.getAllPays();
        List<Nationalite> nationalites = profilService.getAllNationalites();
        List<Diplome> diplomes = profilService.getAllDiplomes();

        request.setAttribute("genres", genres);
        request.setAttribute("paysList", paysList);
        request.setAttribute("nationalites", nationalites);
        request.setAttribute("diplomes", diplomes);

        request.getRequestDispatcher("/views/etudiant/profil_etudiant.jsp").forward(request, response);
    }
}
