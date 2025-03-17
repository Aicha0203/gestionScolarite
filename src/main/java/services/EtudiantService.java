package services;

import java.util.List;

import dao.EtudiantDAO;
import models.Etudiant;

public class EtudiantService {
    private EtudiantDAO etudiantDAO;

    public EtudiantService() {
        etudiantDAO = new EtudiantDAO();
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantDAO.getAllEtudiants();
    }

    public boolean enregistrerOuMettreAJourEtudiant(int utilisateurId, Etudiant etudiant) {
        return etudiantDAO.enregistrerOuMettreAJourEtudiant(utilisateurId, etudiant);
    }

    public boolean updateEtudiant(int id, String telephone, String adresse) {
        return etudiantDAO.updateEtudiant(id, telephone, adresse);
    }
    
    public Etudiant getEtudiantById(int id) {
        return etudiantDAO.getEtudiantById(id);
    }

}
