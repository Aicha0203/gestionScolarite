package models;

import java.util.Date;

public class Facture {
    private int id;
    private int etudiantId;
    private int typeFraisId;
    private double montant;
    private Date dateCreation;
    private Date dateEcheance;
    private String statut;

    public Facture() {}

    public Facture(int id, int etudiantId, int typeFraisId, double montant, Date dateCreation, Date dateEcheance, String statut) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.typeFraisId = typeFraisId;
        this.montant = montant;
        this.dateCreation = dateCreation;
        this.dateEcheance = dateEcheance;
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

	public int getTypeFraisId() {
		return typeFraisId;
	}

	public void setTypeFraisId(int typeFraisId) {
		this.typeFraisId = typeFraisId;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

}
