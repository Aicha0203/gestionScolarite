package services;

import java.util.List;
import dao.InscriptionCoursDAO;
import models.Matiere;

public class InscriptionCoursService {
    private InscriptionCoursDAO inscriptionCoursDAO;

    public InscriptionCoursService() {
        this.inscriptionCoursDAO = new InscriptionCoursDAO();
    }

    public boolean inscrireEtudiant(int etudiantId, int matiereId) {
        return inscriptionCoursDAO.inscrireEtudiant(etudiantId, matiereId);
    }

    public List<Matiere> getCoursInscrits(int etudiantId) {
        return inscriptionCoursDAO.getCoursInscrits(etudiantId);
    }
}
