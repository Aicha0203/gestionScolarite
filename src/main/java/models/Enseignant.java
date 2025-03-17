package models;

public class Enseignant extends Utilisateur {
	private String matricule;
	private String prenom;
	private String nom;
	private String telephone;
	private String specialite;

	public Enseignant() {}

	public Enseignant(int id, String username, String email, String motDePasse, Role role, 
			String matricule, String prenom, String nom, String telephone, String specialite) {
		super(id, username, email, motDePasse, role);
		this.matricule = matricule;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.specialite = specialite;
	}

	public Enseignant(int id, String username, String email, String motDePasse, Role role, 
			String matricule, String prenom, String nom, String telephone, 
			String specialite, String photo) {
		super(id, username, email, motDePasse, role, photo);
		this.matricule = matricule;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.specialite = specialite;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}


}
