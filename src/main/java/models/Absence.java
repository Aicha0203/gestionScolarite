package models;

import java.util.Date;

public class Absence {
	private int id;
	private int etudiantId;
	private int programmeId;
	private int specialiteId;
	private Date dateAbsence;
	private boolean justifie;

	public Absence() {}

	public Absence(int id, int etudiantId, int programmeId, int specialiteId, Date dateAbsence, boolean justifie) {
		this.id = id;
		this.etudiantId = etudiantId;
		this.programmeId = programmeId;
		this.specialiteId = specialiteId;
		this.dateAbsence = dateAbsence;
		this.justifie = justifie;
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

	public Date getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public boolean isJustifie() {
		return justifie;
	}

	public void setJustifie(boolean justifie) {
		this.justifie = justifie;
	}

}
