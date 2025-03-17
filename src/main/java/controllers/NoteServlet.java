package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Note;
import services.NoteService;

@WebServlet("/NoteServlet")
public class NoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NoteService noteService;

    public NoteServlet() {
        super();
        noteService = new NoteService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int etudiantId = Integer.parseInt(request.getParameter("etudiantId"));
        List<Note> notes = noteService.getNotesByEtudiant(etudiantId);
        request.setAttribute("notes", notes);
        request.getRequestDispatcher("/views/etudiant/notes.jsp").forward(request, response);
    }
}

