package models;

import java.util.Date;

public class Paiement {
    private int id;
    private int etudiantId;
    private int factureId;
    private double montant;
    private Date datePaiement;
    private int modePaiementId;
    private String transactionId;
    private String statut;

    public Paiement() {}

    public Paiement(int id, int etudiantId, int factureId, double montant, Date datePaiement, int modePaiementId, String transactionId, String statut) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.factureId = factureId;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.modePaiementId = modePaiementId;
        this.transactionId = transactionId;
        this.statut = statut;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEtudiantId() {
		return etudiantId;
	}

	public void setEtudiantId(int etudiantId) {
		this.etudiantId = etudiantId;
	}

	public int getFactureId() {
		return factureId;
	}

	public void setFactureId(int factureId) {
		this.factureId = factureId;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public int getModePaiementId() {
		return modePaiementId;
	}

	public void setModePaiementId(int modePaiementId) {
		this.modePaiementId = modePaiementId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

}
