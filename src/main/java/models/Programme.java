package models;

public class Programme {
	private int id;
	private String code;
	private String nom;
	private String description;
	private String typeCours;

	public Programme() {}

	public Programme(int id, String code, String nom, String description, String typeCours) {
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.description = description;
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

	public String getTypeCours() {
		return typeCours;
	}

	public void setTypeCours(String typeCours) {
		this.typeCours = typeCours;
	}


}
