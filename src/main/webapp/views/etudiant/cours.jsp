<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="jakarta.servlet.http.HttpSession, java.util.List, models.Matiere"%>

<%
HttpSession sessionUser = request.getSession();
Integer etudiantId = (Integer) sessionUser.getAttribute("userId");

if (etudiantId == null) {
	response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
	return;
}

List<Matiere> coursDisponibles = (List<Matiere>) request.getAttribute("coursDisponibles");
List<Matiere> coursInscrits = (List<Matiere>) request.getAttribute("coursInscrits");

if (coursDisponibles == null) {
	coursDisponibles = List.of(); // ✅ Empêcher l'erreur NullPointerException
}

if (coursInscrits == null) {
	coursInscrits = List.of(); // ✅ Empêcher l'erreur NullPointerException
}

String etudiantNom = (String) sessionUser.getAttribute("userName");
if (etudiantNom == null) {
	etudiantNom = "Étudiant";
}
%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Portail Étudiant - Cours</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/cours.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
	<div class="layout">
		<!-- Barre latérale -->
		<aside class="sidebar">
			<div class="logo-container">
				<img src="<%=request.getContextPath()%>/assets/images/logo.png"
					alt="Logo" class="logo-img">
				<h2 class="logo">Portail Étudiant</h2>
			</div>

			<div class="search-container">
				<input type="text" placeholder="Rechercher..." class="search-input">
				<i class="fas fa-search search-icon"></i>
			</div>

			<ul class="menu">
				<li><a href="<%=request.getContextPath()%>/EtudiantServlet">
						<i class="fas fa-th-large"></i> Tableau de bord
				</a></li>
				<li class="active"><a
					href="<%=request.getContextPath()%>/cours"><i
						class="fas fa-book"></i> Cours</a></li>
				<li><a
					href="<%=request.getContextPath()%>/views/etudiant/emploi_du_temps.jsp">
						<i class="fas fa-calendar-alt"></i> Emploi du temps
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/FactureServlet?etudiantId=<%=session.getAttribute("userId")%>">
						<i class="fas fa-wallet"></i> Finances
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/NoteServlet?etudiantId=<%=session.getAttribute("userId")%>">
						<i class="fas fa-chart-bar"></i> Notes
				</a></li>
				<li><a href="<%=request.getContextPath()%>/ProfilServlet">
						<i class="fas fa-user"></i> Profil
				</a></li>
				<li class="logout"><a
					href="<%=request.getContextPath()%>/AuthServlet?action=logout">
						<i class="fas fa-sign-out-alt"></i> Déconnexion
				</a></li>

			</ul>
		</aside>


		<!-- Contenu principal -->
		<main class="main-content">
			<!-- En-tête -->
			<header class="header">
				<h1>Cours</h1>
				<div class="user-actions">
					<div class="notifications">
						<i class="fas fa-bell"></i> <span class="badge">0</span>
					</div>
					<div class="user-info">
						<img src="<%=request.getContextPath()%>/assets/images/avatar.png"
							alt="Avatar"> <span><%=etudiantNom%></span>
					</div>
					<div class="more-options">
						<i class="fas fa-ellipsis-v"></i>
					</div>
				</div>
			</header>

			<!-- Filtres de cours -->
			<div class="course-filters">
				<button class="filter-btn active">Tous</button>
				<button class="filter-btn">Tronc commun</button>
				<button class="filter-btn">Obligatoire</button>
				<button class="filter-btn">Optionnel</button>
			</div>

			<!-- Liste des cours -->
			<div class="course-container">
				<!-- Colonne de gauche: Liste des cours disponibles -->
				<div class="course-list">
					<%
					if (coursDisponibles.isEmpty() && coursInscrits.isEmpty()) {
					%>
					<div class="empty-course">
						<p>Aucun cours disponible actuellement.</p>
					</div>
					<%
					} else {
					%>
					<%
					// Combiner les deux listes pour afficher tous les cours
					List<Matiere> tousLesCours = new java.util.ArrayList<>();
					tousLesCours.addAll(coursDisponibles);
					tousLesCours.addAll(coursInscrits);

					for (Matiere matiere : tousLesCours) {
						String typeCours = matiere.getTypeCours();
						String couleurClass = "";

						if (typeCours.equalsIgnoreCase("obligatoire")) {
							couleurClass = "course-orange";
						} else if (typeCours.equalsIgnoreCase("tronc commun")) {
							couleurClass = "course-blue";
						} else if (typeCours.equalsIgnoreCase("optionnel")) {
							couleurClass = "course-purple";
						}
					%>
					<div class="course-card <%=couleurClass%>">
						<div class="course-code"><%=matiere.getCode() != null ? matiere.getCode() : "MAT " + matiere.getId()%></div>
						<div class="course-details">
							<h3><%=matiere.getNom()%></h3>
							<p><%=typeCours%></p>
						</div>
					</div>
					<%
					}
					%>
					<%
					}
					%>
				</div>

				<div class="course-details-panel">
					<div class="course-info">
						<div class="course-icon">N</div>
						<div>
							<h2>Réseaux Neuronaux</h2>
							<p class="course-info-code">HA1 · Cours Magistral</p>
							<p class="course-instructor">Dr. Ndiaye</p>
						</div>
					</div>

					<!-- À propos du cours -->
					<div class="about-course">
						<h3>À propos de ce cours</h3>
						<p>Ce cours couvrira les architectures de base des réseaux
							neuronaux et les algorithmes d'apprentissage, pour des
							applications en reconnaissance de formes, traitement d'images, et
							vision par ordinateur.</p>
					</div>

					<!-- Emploi du temps du cours -->
					<div class="course-schedule">
						<h3>Emploi du temps</h3>
						<p>Trois (3) heures par semaine</p>

						<div class="schedule-details">
							<div class="schedule-item">
								<i class="fas fa-calendar"></i> <span>Lundi, 14h-15h30</span>
							</div>
							<div class="schedule-item">
								<i class="fas fa-map-marker-alt"></i> <span>HA1</span>
							</div>
						</div>

						<div class="schedule-details">
							<div class="schedule-item">
								<i class="fas fa-calendar"></i> <span>Mercredi, 8h-10h</span>
							</div>
							<div class="schedule-item">
								<i class="fas fa-map-marker-alt"></i> <span>SES</span>
							</div>
						</div>

						<button class="reminder-btn">
							<i class="fas fa-bell"></i> Ajouter un rappel
						</button>
					</div>

					<!-- Matériel de cours -->
					<div class="course-material">
						<div class="material-header">
							<h3>Matériel de cours</h3>
							<a href="#" class="view-all">Voir tout</a>
						</div>

						<div class="material-items">
							<div class="material-item">
								<div class="material-icon pdf">PDF</div>
								<span>Introduction aux Réseaux Neuronaux</span>
							</div>
							<div class="material-item">
								<div class="material-icon pdf">PDF</div>
								<span>Modèles de neurones et architectures</span>
							</div>
							<div class="material-item">
								<div class="material-icon pdf">PDF</div>
								<span>Apprentissage Perceptif</span>
							</div>
						</div>
					</div>
				</div>

				<!-- Panneau latéral d'inscription -->
				<div class="registration-panel">
					<div class="registration-section">
						<h3>Accéder aux TP et TD</h3>
						<p>Consultez vos séances de travaux dirigés (TD) et de travaux
							pratiques (TP) pour approfondir vos connaissances et réussir vos
							cours.</p>
						<button class="register-btn">ACCEDER MAINTENANT</button>
					</div>

					<div class="rectification-section">
						<h3>Rectification de Cours</h3>
						<p>Soulevez des préoccupations et résolvez les plaintes
							concernant vos notes d'examens et de tests pour les cours
							affectés.</p>
						<a href="#" class="rectify-link">Rectifier les cours <i
							class="fas fa-arrow-right"></i></a>
					</div>

					<div class="edit-section">
						<h3>Modifier les Cours Enregistrés</h3>
						<p>Effectuez des corrections à votre formulaire de cours:
							ajoutez ou supprimez des cours, ou inscrivez-vous à de nouveaux
							cours pour des crédits supplémentaires.</p>
						<a href="#" class="edit-link">Modifier les cours <i
							class="fas fa-arrow-right"></i></a>
					</div>

					<div class="library-section">
						<h3>Bibliothèque</h3>
						<p>Parcourez les textes pertinents, empruntez des livres
							depuis le catalogue en ligne.</p>
						<a href="#" class="library-link">Accéder à la bibliothèque <i
							class="fas fa-arrow-right"></i></a>
					</div>
				</div>
			</div>
		</main>
	</div>

	<script src="<%=request.getContextPath()%>/assets/js/cours.js"></script>
</body>
</html>