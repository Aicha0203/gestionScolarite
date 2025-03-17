package services;

import java.util.List;

import dao.NoteDAO;
import models.Note;

public class NoteService {
    private NoteDAO noteDAO;

    public NoteService() {
        noteDAO = new NoteDAO();
    }

    public List<Note> getNotesByEtudiant(int etudiantId) {
        return noteDAO.getNotesByEtudiant(etudiantId);
    }
}
