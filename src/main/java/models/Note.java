package models;

public class Note {
	private int id;
	private int etudiantId;
	private int programmeId;
	private int specialiteId;
	private int matiereId; 
	private String anneeAcademique;
	private double moyenne;
	private int credit;
	private String codeMatiere; 
	private String nomMatiere; 
	private String session;  

	public Note() {}

	public Note(int id, int etudiantId, int programmeId, int specialiteId, int matiereId, String anneeAcademique, double moyenne, int credit, String codeMatiere, String nomMatiere, String session) {
		this.id = id;
		this.etudiantId = etudiantId;
		this.programmeId = programmeId;
		this.specialiteId = specialiteId;
		this.matiereId = matiereId;
		this.anneeAcademique = anneeAcademique;
		this.moyenne = moyenne;
		this.credit = credit;
		this.codeMatiere = codeMatiere;
		this.nomMatiere = nomMatiere;
		this.session = session;
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

	public int getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(int programmeId) {
		this.programmeId = programmeId;
	}

	public int getSpecialiteId() {
		return specialiteId;
	}

	public void setSpecialiteId(int specialiteId) {
		this.specialiteId = specialiteId;
	}

	public int getMatiereId() {
		return matiereId;
	}

	public void setMatiereId(int matiereId) {
		this.matiereId = matiereId;
	}

	public String getAnneeAcademique() {
		return anneeAcademique;
	}

	public void setAnneeAcademique(String anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getCodeMatiere() {
		return codeMatiere;
	}

	public void setCodeMatiere(String codeMatiere) {
		this.codeMatiere = codeMatiere;
	}

	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

}
