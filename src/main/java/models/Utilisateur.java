package models;

public class Utilisateur {
    private int id;
    private String username;
    private String email;
    private String motDePasse;
    private Role role;
    private String photo; 

    public Utilisateur() {}

    public Utilisateur(int id, String username, String email, String motDePasse, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    
    
	public Utilisateur(int id, String username, String email, String motDePasse, Role role, String photo) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.motDePasse = motDePasse;
		this.role = role;
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
