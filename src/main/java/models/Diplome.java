package models;

public class Diplome {
    private int id;
    private String libelle;
    private String niveau;

    public Diplome() {}

    public Diplome(int id, String libelle, String niveau) {
        this.id = id;
        this.libelle = libelle;
        this.niveau = niveau;
    }
    
    

	public Diplome(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

}
