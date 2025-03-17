<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Utilisateur, models.Etudiant, models.Enseignant, models.Role" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession sessionUser = request.getSession();
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");

    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp?error=Utilisateur introuvable.");
        return;
    }

    boolean isEtudiant = utilisateur instanceof Etudiant;
    boolean isEnseignant = utilisateur instanceof Enseignant;
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Profil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/profil.css">
</head>
<body>

<div class="profil-container">
    <h1>Mon Profil</h1>

    <!-- 📌 Photo de profil -->
    <div class="profil-photo">
        <img src="<%= utilisateur.getPhoto() != null ? request.getContextPath() + utilisateur.getPhoto() : request.getContextPath() + "/assets/images/default-user.png" %>" 
             alt="Photo de profil" class="user-avatar">
        <form action="<%= request.getContextPath() %>/ProfilServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="updatePhoto">
            <input type="file" name="photo" accept="image/*">
            <button type="submit">Mettre à jour</button>
        </form>
    </div>

    <!-- 📌 Informations générales -->
    <form action="<%= request.getContextPath() %>/ProfilServlet" method="post">
        <input type="hidden" name="action" value="updateProfile">
        
        <label>Nom d'utilisateur :</label>
        <input type="text" value="<%= utilisateur.getUsername() %>" readonly>

        <label>Email :</label>
        <input type="email" value="<%= utilisateur.getEmail() %>" readonly>

        <label>Téléphone :</label>
        <input type="text" name="telephone" value="<%= isEtudiant ? ((Etudiant) utilisateur).getTelephone() : ((Enseignant) utilisateur).getTelephone() %>">

        <% if (isEtudiant) { %>
            <!-- 📌 Champs spécifiques aux étudiants -->
            <label>Matricule :</label>
            <input type="text" value="<%= ((Etudiant) utilisateur).getMatricule() %>" readonly>

            <label>Adresse :</label>
            <input type="text" name="adresse" value="<%= ((Etudiant) utilisateur).getAdresseComplete() %>">

            <label>Date de naissance :</label>
            <input type="date" value="<%= ((Etudiant) utilisateur).getDateNaissance() %>" readonly>

            <label>Pays de naissance :</label>
            <input type="text" value="<%= ((Etudiant) utilisateur).getPaysNaissanceId() %>" readonly>

            <label>Dernier diplôme :</label>
            <input type="text" value="<%= ((Etudiant) utilisateur).getDernierDiplomeId() %>" readonly>

        <% } else if (isEnseignant) { %>
            <label>Matricule :</label>
            <input type="text" value="<%= ((Enseignant) utilisateur).getMatricule() %>" readonly>

            <label>Spécialité :</label>
            <input type="text" name="specialite" value="<%= ((Enseignant) utilisateur).getSpecialite() %>">
        <% } %>

        <button type="submit">Mettre à jour</button>
    </form>

</div>

</body>
</html>
