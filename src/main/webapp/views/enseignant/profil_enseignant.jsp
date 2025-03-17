<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionUser = request.getSession();
    Integer userId = (Integer) sessionUser.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Profil Enseignant</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/profil.css">
</head>
<body>

<h1>Compléter Votre Profil Enseignant</h1>

<form action="<%= request.getContextPath() %>/ProfilEnseignantServlet" method="post">
    <input type="hidden" name="userId" value="<%= userId %>">

    <label>Matricule :</label>
    <input type="text" name="matricule" required>

    <label>Spécialité :</label>
    <input type="text" name="specialite" required>

    <label>Téléphone :</label> 
    <input type="text" name="telephone" required>

    <button type="submit">Enregistrer</button>
</form>

</body>
</html>
