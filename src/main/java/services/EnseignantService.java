package services;

import dao.EnseignantDAO;
import models.Enseignant;

public class EnseignantService {
    private EnseignantDAO enseignantDAO;

    public EnseignantService() {
        enseignantDAO = new EnseignantDAO();
    }

    public Enseignant getEnseignantById(int id) {
        return enseignantDAO.getEnseignantById(id);
    }

    public boolean mettreAJourEnseignant(int id, Enseignant enseignant) {
        return enseignantDAO.mettreAJourEnseignant(id, enseignant);
    }

    public boolean enregistrerOuMettreAJourEnseignant(int utilisateurId, Enseignant enseignant) {
        return enseignantDAO.enregistrerOuMettreAJourEnseignant(utilisateurId, enseignant);
    }
}
