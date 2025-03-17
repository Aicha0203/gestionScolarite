package services;

import java.util.List;

import dao.AbsenceDAO;
import models.Absence;

public class AbsenceService {
    private AbsenceDAO absenceDAO;

    public AbsenceService() {
        absenceDAO = new AbsenceDAO();
    }

    public List<Absence> getAbsencesByEtudiant(int etudiantId) {
        return absenceDAO.getAbsencesByEtudiant(etudiantId);
    }
}
