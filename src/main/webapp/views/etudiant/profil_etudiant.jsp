<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Genre, models.Pays, models.Nationalite, models.Diplome" %>

<%
    HttpSession sessionUser = request.getSession();
    Integer userId = (Integer) sessionUser.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
        return;
    }

    List<Genre> genres = (List<Genre>) request.getAttribute("genres");
    List<Pays> paysList = (List<Pays>) request.getAttribute("paysList");
    List<Nationalite> nationalites = (List<Nationalite>) request.getAttribute("nationalites");
    List<Diplome> diplomes = (List<Diplome>) request.getAttribute("diplomes");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Profil Étudiant</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/profil.css">
</head>
<body>

<h1>Compléter Votre Profil Étudiant</h1>

<form action="<%= request.getContextPath() %>/ProfilEtudiantServlet" method="post">
    <input type="hidden" name="userId" value="<%= userId %>">

    <label>Matricule :</label>
    <input type="text" name="matricule" required>

    <label>Genre :</label>
    <select name="genre_id">
        <% if (genres != null) { 
            for (Genre genre : genres) { %>
                <option value="<%= genre.getId() %>"><%= genre.getLibelle() %></option>
        <%  } 
           } %>
    </select>

    <label>Date de Naissance :</label>
    <input type="date" name="date_naissance" required>

    <label>Pays de Naissance :</label>
    <select name="pays_naissance_id">
        <% if (paysList != null) { 
            for (Pays pays : paysList) { %>
                <option value="<%= pays.getId() %>"><%= pays.getNom() %></option>
        <%  } 
           } %>
    </select>

    <label>Nationalité :</label>
    <select name="nationalite_id">
        <% if (nationalites != null) { 
            for (Nationalite nationalite : nationalites) { %>
                <option value="<%= nationalite.getId() %>"><%= nationalite.getLibelle() %></option>
        <%  } 
           } %>
    </select>

    <label>Téléphone :</label>
    <input type="text" name="telephone" required>

    <label>Adresse :</label>
    <input type="text" name="adresse_complete" required>

    <label>Dernier Diplôme :</label>
    <select name="dernier_diplome_id">
        <% if (diplomes != null) { 
            for (Diplome diplome : diplomes) { %>
                <option value="<%= diplome.getId() %>"><%= diplome.getLibelle() %></option>
        <%  } 
           } %>
    </select>

    <button type="submit">Enregistrer</button>
</form>

</body>
</html>
