package services;

import java.util.List;

import dao.PaiementDAO;
import models.Paiement;

public class PaiementService {
    private PaiementDAO paiementDAO;

    public PaiementService() {
        paiementDAO = new PaiementDAO();
    }

    public List<Paiement> getPaiementsByEtudiant(int etudiantId) {
        return paiementDAO.getPaiementsByEtudiant(etudiantId);
    }
}
