package models;

import java.util.Date;

public class Etudiant extends Utilisateur {
	private String matricule;
	private String prenom;
	private String nom;
	private int genreId;
	private Date dateNaissance;
	private int paysNaissanceId;
	private int nationaliteId;
	private String telephone;
	private String adresseComplete;
	private int dernierDiplomeId;
	private Date dateInscription;
	private int statutId;

	public Etudiant() {}

	public Etudiant(int id, String username, String email, String motDePasse, Role role, 
			String matricule, String prenom, String nom, int genreId, Date dateNaissance, 
			int paysNaissanceId, int nationaliteId, String telephone, String adresseComplete, 
			int dernierDiplomeId, Date dateInscription, int statutId) {
		super(id, username, email, motDePasse, role);
		this.matricule = matricule;
		this.prenom = prenom;
		this.nom = nom;
		this.genreId = genreId;
		this.dateNaissance = dateNaissance;
		this.paysNaissanceId = paysNaissanceId;
		this.nationaliteId = nationaliteId;
		this.telephone = telephone;
		this.adresseComplete = adresseComplete;
		this.dernierDiplomeId = dernierDiplomeId;
		this.dateInscription = dateInscription;
		this.statutId = statutId;
	}

	public Etudiant(int id, String username, String email, String motDePasse, Role role, 
			String matricule, String prenom, String nom, int genreId, Date dateNaissance, 
			int paysNaissanceId, int nationaliteId, String telephone, String adresseComplete, 
			int dernierDiplomeId, Date dateInscription, int statutId, String photo) {
		super(id, username, email, motDePasse, role, photo); 
		this.matricule = matricule;
		this.prenom = prenom;
		this.nom = nom;
		this.genreId = genreId;
		this.dateNaissance = dateNaissance;
		this.paysNaissanceId = paysNaissanceId;
		this.nationaliteId = nationaliteId;
		this.telephone = telephone;
		this.adresseComplete = adresseComplete;
		this.dernierDiplomeId = dernierDiplomeId;
		this.dateInscription = dateInscription;
		this.statutId = statutId;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public int getPaysNaissanceId() {
		return paysNaissanceId;
	}

	public void setPaysNaissanceId(int paysNaissanceId) {
		this.paysNaissanceId = paysNaissanceId;
	}

	public int getNationaliteId() {
		return nationaliteId;
	}

	public void setNationaliteId(int nationaliteId) {
		this.nationaliteId = nationaliteId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresseComplete() {
		return adresseComplete;
	}

	public void setAdresseComplete(String adresseComplete) {
		this.adresseComplete = adresseComplete;
	}

	public int getDernierDiplomeId() {
		return dernierDiplomeId;
	}

	public void setDernierDiplomeId(int dernierDiplomeId) {
		this.dernierDiplomeId = dernierDiplomeId;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public int getStatutId() {
		return statutId;
	}

	public void setStatutId(int statutId) {
		this.statutId = statutId;
	}



}
