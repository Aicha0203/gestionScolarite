package services;

import java.util.List;

import dao.CoursDAO;
import models.Matiere;

public class CoursService {
    private CoursDAO coursDAO;

    public CoursService() {
        coursDAO = new CoursDAO();
    }

    public List<Matiere> getAllCours() {
        return coursDAO.getAllCours();
    }

    public List<Matiere> getCoursByProgramme(int programmeId) {
        return coursDAO.getCoursByProgramme(programmeId);
    }

    public boolean ajouterCours(Matiere matiere) {
        return coursDAO.ajouterCours(matiere);
    }

    public boolean mettreAJourCours(Matiere matiere) {
        return coursDAO.mettreAJourCours(matiere);
    }

    public boolean supprimerCours(int id) {
        return coursDAO.supprimerCours(id);
    }
}
