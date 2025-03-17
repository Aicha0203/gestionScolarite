package services;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.UtilisateurDAO;
import models.Enseignant;
import models.Etudiant;
import models.Role;
import models.Utilisateur;

import java.sql.Date;

public class AuthService {
    private UtilisateurDAO utilisateurDAO;
    private EtudiantDAO etudiantDAO;
    private EnseignantDAO enseignantDAO;

    public AuthService() {
        this.utilisateurDAO = new UtilisateurDAO();
        this.etudiantDAO = new EtudiantDAO();
        this.enseignantDAO = new EnseignantDAO();
    }

    public int inscrireUtilisateur(String nom, String prenom, String email, String motDePasse, Role role, String photoPath) {
        if (utilisateurDAO.getUtilisateurParEmail(email) != null) {
            System.out.println("❌ Email déjà utilisé !");
            return -1;
        }

        String username = prenom + " " + nom;
        Utilisateur utilisateur = new Utilisateur(0, username, email, motDePasse, role, photoPath);

        return utilisateurDAO.ajouterUtilisateurRetourId(utilisateur);
    }

    public boolean ajouterEtudiant(int utilisateurId, String matricule, String prenom, String nom, String email, int genreId, Date dateNaissance, int paysNaissanceId, int nationaliteId, String telephone, String adresse, int dernierDiplomeId) {
        Etudiant etudiant = new Etudiant(utilisateurId, email, "", "", Role.ETUDIANT, matricule, prenom, nom, genreId, dateNaissance, paysNaissanceId, nationaliteId, telephone, adresse, dernierDiplomeId, null, 0);
        boolean result = etudiantDAO.ajouterEtudiant(utilisateurId, etudiant);
        System.out.println("📌 Ajout étudiant réussi ? " + result);
        return result;
    }

    public boolean ajouterEnseignant(int utilisateurId, String matricule, String prenom, String nom, String email, String telephone, String specialite) {
        Enseignant enseignant = new Enseignant(utilisateurId, "", email, "", Role.ENSEIGNANT, matricule, prenom, nom, telephone, specialite);
        boolean result = enseignantDAO.ajouterEnseignant(utilisateurId, enseignant);
        System.out.println("📌 Ajout enseignant réussi ? " + result);
        return result;
    }

    public Utilisateur getUtilisateurById(int userId) {
        return utilisateurDAO.getUtilisateurById(userId);
    }


    public Utilisateur authentifier(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurDAO.getUtilisateurParEmail(email);

        if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {
            return utilisateur;
        }

        System.out.println("Email ou mot de passe incorrect !");
        return null;
    }
}
