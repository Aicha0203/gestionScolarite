package models;

public class Matiere {
    private int id;
    private String code;
    private String nom;
    private String description;
    private int programmeId;
    private int specialiteId;
    private String typeCours; // "THEORIQUE", "PRATIQUE", "MIXTE"

    public Matiere() {}

    public Matiere(int id, String code, String nom, String description, int programmeId, int specialiteId, String typeCours) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.programmeId = programmeId;
        this.specialiteId = specialiteId;
        this.typeCours = typeCours;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getTypeCours() {
		return typeCours;
	}

	public void setTypeCours(String typeCours) {
		this.typeCours = typeCours;
	}
    
    

}