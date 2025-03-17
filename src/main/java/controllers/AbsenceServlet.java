package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Absence;
import services.AbsenceService;

@WebServlet("/AbsenceServlet")
public class AbsenceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AbsenceService absenceService;

    public AbsenceServlet() {
        super();
        absenceService = new AbsenceService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int etudiantId = Integer.parseInt(request.getParameter("etudiantId"));
        List<Absence> absences = absenceService.getAbsencesByEtudiant(etudiantId);
        request.setAttribute("absences", absences);
        request.getRequestDispatcher("/views/etudiant/absences.jsp").forward(request, response);
    }
}
