package services;

import java.sql.Date;
import java.util.List;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.ProfilDAO;
import dao.UtilisateurDAO;
import models.Diplome;
import models.Enseignant;
import models.Etudiant;
import models.Genre;
import models.Nationalite;
import models.Pays;
import models.Utilisateur;

public class ProfilService {
    private EtudiantDAO etudiantDAO;
    private EnseignantDAO enseignantDAO;
    private UtilisateurDAO utilisateurDAO;
    private ProfilDAO profilDAO;

    public ProfilService() {
        this.etudiantDAO = new EtudiantDAO();
        this.enseignantDAO = new EnseignantDAO();
        this.utilisateurDAO = new UtilisateurDAO();
        this.profilDAO = new ProfilDAO();
    }

    public List<Genre> getAllGenres() {
        return profilDAO.getGenres();
    }

    public List<Pays> getAllPays() {
        return profilDAO.getPays();
    }

    public List<Nationalite> getAllNationalites() {
        return profilDAO.getNationalites();
    }

    public List<Diplome> getAllDiplomes() {
        return profilDAO.getDiplomes();
    }

    public boolean ajouterEtudiant(int utilisateurId, String matricule, int genreId, Date dateNaissance, int paysNaissanceId, int nationaliteId, String telephone, String adresse, int dernierDiplomeId) {
        return profilDAO.ajouterEtudiant(utilisateurId, matricule, genreId, dateNaissance, paysNaissanceId, nationaliteId, telephone, adresse, dernierDiplomeId);
    }

    public boolean ajouterEnseignant(int utilisateurId, String matricule, String prenom, String nom, String email, String telephone, String specialite) {
        return profilDAO.ajouterEnseignant(utilisateurId, matricule, prenom, nom, email, telephone, specialite);
    }

    
    public Utilisateur getProfilById(int userId, String role) {
        if ("ETUDIANT".equals(role)) {
            return etudiantDAO.getEtudiantById(userId);
        } else if ("ENSEIGNANT".equals(role)) {
            return enseignantDAO.getEnseignantById(userId);
        }
        return null;
    }

    public boolean updateEtudiantProfil(int userId, Etudiant etudiant) {
        return etudiantDAO.mettreAJourEtudiant(userId, etudiant);
    }

    public boolean updateEnseignantProfil(int userId, Enseignant enseignant) {
        return enseignantDAO.mettreAJourEnseignant(userId, enseignant);
    }

    public boolean updatePhotoProfil(int userId, String photoPath) {
        return utilisateurDAO.updateUtilisateurPhoto(userId, photoPath);
    }
}
