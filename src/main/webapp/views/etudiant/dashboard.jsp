<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, models.Matiere, jakarta.servlet.http.HttpSession"%>

<%
HttpSession sessionEtudiant = request.getSession();
String etudiantNom = (String) sessionEtudiant.getAttribute("userName");
String role = (String) sessionEtudiant.getAttribute("role");

if (etudiantNom == null || role == null || !"ETUDIANT".equals(role)) {
	response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Portail Étudiant</title>

<!-- Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/dashboard.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css">
</head>
<body>
	<div class="dashboard-container">
		<!-- Sidebar -->
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
				<li class="active"><a
					href="<%=request.getContextPath()%>/EtudiantServlet"> <i
						class="fas fa-th-large"></i> Tableau de bord
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/CoursServlet?programmeId=1">
						<i class="fas fa-book"></i> Cours
				</a></li>
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

		<!-- Main Content -->
		<main class="content">
			<!-- Header -->
			<header class="header">
				<h1>Tableau de Bord</h1>
				<div class="user-info">
					<div class="notification">
						<i class="fas fa-bell"></i> <span class="notification-count">0</span>
					</div>
					<span class="user-name"><%= session.getAttribute("userName") %></span>
					<img
						src="<%= session.getAttribute("photo") != null ? session.getAttribute("photo") : request.getContextPath() + "/assets/images/An.jpg" %>"
						alt="Profile" class="user-avatar"> <i
						class="fas fa-ellipsis-v more-options"></i>
				</div>
			</header>

			<section class="dashboard">
				<div class="lectures-section">
					<h2>Le Matin</h2>
					<div class="lecture-tabs">
						<div class="tab active" data-tab="ongoing">En cours</div>
						<div class="tab" data-tab="upcoming">À venir</div>
					</div>

					<div class="lecture-content" id="ongoing">
						<div class="course-card">
							<div class="course-info">
								<h3 class="course-title">Mathématiques </h3>
								<p class="course-time">8h - 10h</p>
								<p class="course-professor">Dr. John Simon</p>
								<p class="course-location">Lieu : HA1</p>
							</div>
							<div class="course-code">MTS 241</div>
						</div>
					</div>

					<div class="lecture-content" id="upcoming" style="display: none;">
						<div class="course-card upcoming">
							<div class="course-info">
								<h3 class="course-title">Algorithmes et structures de données</h3>
								<p class="course-time">11h - 12h</p>
								<p class="course-professor">Dr. Ndiaye.</p>
								<p class="course-location">Lieu : HB1</p>
							</div>
							<div class="course-code">CSC 261</div>
						</div>
					</div>
				</div>

				<!-- Calendar Section -->
				<div class="calendar-section">
					<h2>Calendrier</h2>
					<div class="calendar-header">
						<div class="month-selector">
							<div class="month-arrows">
								<i class="fas fa-chevron-left"></i> <i
									class="fas fa-chevron-right"></i>
							</div>
						</div>
					</div>
					<div id="calendar"></div>
				</div>
			</section>

			<!-- Later Today Section -->
			<section class="happening-later">
				<h2>Le Soir</h2>
				<div class="later-courses">
					<div class="later-course">
						<div class="course-info">
							<h3 class="course-title">Réseaux Télécom</h3>
							<p class="course-time">14h - 15h</p>
							<p class="course-professor">Mr. Dia</p>
						</div>
						<div class="course-code">CSC 231</div>
					</div>

					<div class="later-course">
						<div class="course-info">
							<h3 class="course-title">Statistiques appliquées</h3>
							<p class="course-time">11h - 12h</p>
							<p class="course-professor">Dr. Sylla.</p>
						</div>
						<div class="course-code">CSC 231</div>
					</div>

					<div class="add-event">
						<i class="fas fa-plus"></i> <span>Ajouter un événement</span>
					</div>
				</div>
			</section>

			<!-- Courses Section -->
			<section class="courses-section">
				<h2>Cours</h2>
				<div class="course-registration-status">
					<i class="fas fa-exclamation-circle"></i>
					<p>Vous n’avez pas encore vous inscrit aux cours de cette année.</p>
				</div>

				<div class="available-courses">
					<div class="available-course">
						<div class="course-info">
							<h3>Mathématiques</h3>
							<p>4 heures - Deux fois par semaine</p>
						</div>
						<div class="course-platform">MATÉRIEL DE COURS</div>
					</div>

					<div class="available-course">
						<div class="course-info">
							<h3>Algorithmes et structures de données</h3>
							<p>3 heures - Deux fois par semaine</p>
						</div>
						<div class="course-platform">MATÉRIEL DE COURS</div>
					</div>
				</div>
			</section>

			<!-- Messages Section -->
			<section class="messages-section">
				<h2>Messages</h2>
				<div class="message">
					<div class="message-icon">P</div>
					<div class="message-content">
						<h3>Maintenance du portail</h3>
						<p>Le portail étudiant sera indisponible à partir de 16h le vendredi 22, Sept à 12h00...</p>
					</div>
				</div>
			</section>
		</main>
	</div>

	<!-- Scripts -->
	<script
		src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/dashboard.js"></script>
</body>
</html>