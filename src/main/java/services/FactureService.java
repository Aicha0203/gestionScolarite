package services;

import java.util.List;

import dao.FactureDAO;
import models.Facture;

public class FactureService {
    private FactureDAO factureDAO;

    public FactureService() {
        factureDAO = new FactureDAO();
    }

    public List<Facture> getFacturesByEtudiant(int etudiantId) {
        return factureDAO.getFacturesByEtudiant(etudiantId);
    }

    public boolean ajouterFacture(Facture facture) {
        return factureDAO.ajouterFacture(facture);
    }
}
