<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page
	import="java.sql.*, java.util.*, dao.DatabaseConnection, jakarta.servlet.http.HttpSession"%>

<%
HttpSession sessionUser = request.getSession();
String role = (String) sessionUser.getAttribute("role");
Integer userId = (Integer) sessionUser.getAttribute("userId");

if (userId == null) {
	response.sendRedirect(request.getContextPath()
	+ "/views/utilisateur/login.jsp?error=Session expirée. Veuillez vous reconnecter.");
	return;
}

Connection conn = DatabaseConnection.getConnection();
%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Compléter votre profil</title>
<link rel="stylesheet" href="../../assets/css/completer_profil.css">
</head>
<body>
	<div class="profil-container">
		<h2>Compléter votre profil</h2>

		<form action="<%=request.getContextPath()%>/ProfilServlet"
			method="post">
			<input type="hidden" name="userId" value="<%=userId%>">

			<%
			if ("ETUDIANT".equals(role)) {
			%>
			<label>Genre :</label> <select name="genre" required>
				<option value="">Sélectionner</option>
				<%
				Statement stmt = conn.createStatement();
				ResultSet rsGenre = stmt.executeQuery("SELECT id, libelle FROM genres");
				while (rsGenre.next()) {
				%>
				<option value="<%=rsGenre.getInt("id")%>"><%=rsGenre.getString("libelle")%></option>
				<%
				}
				%>
			</select> <label>Date de naissance :</label> <input type="date"
				name="date_naissance" required> <label>Pays de
				naissance :</label> <select name="pays_naissance" required>
				<option value="">Sélectionner</option>
				<%
				ResultSet rsPays = stmt.executeQuery("SELECT id, nom FROM pays");
				while (rsPays.next()) {
				%>
				<option value="<%=rsPays.getInt("id")%>"><%=rsPays.getString("nom")%></option>
				<%
				}
				%>
			</select> <label>Nationalité :</label> <select name="nationalite" required>
				<option value="">Sélectionner</option>
				<%
				ResultSet rsNationalite = stmt.executeQuery("SELECT id, libelle FROM nationalites");
				while (rsNationalite.next()) {
				%>
				<option value="<%=rsNationalite.getInt("id")%>"><%=rsNationalite.getString("libelle")%></option>
				<%
				}
				%>
			</select> <label>Téléphone :</label> <input type="text" name="telephone"
				required> <label>Adresse complète :</label>
			<textarea name="adresse" required></textarea>

			<label>Dernier diplôme :</label> <select name="diplome" required>
				<option value="">Sélectionner</option>
				<%
				ResultSet rsDiplome = stmt.executeQuery("SELECT id, libelle FROM diplomes");
				while (rsDiplome.next()) {
				%>
				<option value="<%=rsDiplome.getInt("id")%>"><%=rsDiplome.getString("libelle")%></option>
				<%
				}
				%>
			</select>

			<%
			} else if ("ENSEIGNANT".equals(role)) {
			%>
			<label>Matricule :</label> <input type="text" name="matricule"
				required> <label>Téléphone :</label> <input type="text"
				name="telephone" required> <label>Spécialité :</label> <input
				type="text" name="specialite" required>
			<%
			}
			%>

			<button type="submit">Mettre à jour</button>
		</form>

		<%
		if ("ETUDIANT".equals(role)) {
		%>
		<a href="<%=request.getContextPath()%>/views/etudiant/dashboard.jsp"
			class="btn-secondary">Plus tard</a>
		<%
		} else if ("ENSEIGNANT".equals(role)) {
		%>
		<a
			href="<%=request.getContextPath()%>/views/enseignant/dashboard.jsp"
			class="btn-secondary">Plus tard</a>
		<%
		}
		%>


	</div>
</body>
</html>

<%
conn.close();
%>
