<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, dao.DatabaseConnection, jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionUser = request.getSession();
    String role = (String) sessionUser.getAttribute("role");
    Integer userId = (Integer) sessionUser.getAttribute("userId");

    if (userId == null || !"ADMIN".equals(role)) {
        response.sendRedirect("../utilisateur/login.jsp?error=Accès non autorisé !");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord Administrateur</title>
    <link rel="stylesheet" href="../../assets/css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <h2>Bienvenue Administrateur</h2>
        <a href="../utilisateur/gestion_utilisateurs.jsp">Gérer les utilisateurs</a>
        <a href="../etudiant/liste_etudiants.jsp">Gérer les étudiants</a>
        <a href="../enseignant/liste_enseignants.jsp">Gérer les enseignants</a>
        <a href="../../AuthServlet?action=logout">Se déconnecter</a>
    </div>
</body>
</html>
