<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tableau de Bord</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/dashboardEnseignant.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<div class="container">
		<!-- Barre latérale -->
		<div class="sidebar">
			<div class="logo">
				<h1>Portail Enseignant</h1>
			</div>

			<div class="profile">
				<div class="profile-image">
					<img src="https://via.placeholder.com/80" alt="photo">
				</div>
				<div class="profile-info">
					<h3>Babacar Diouf</h3>
					<p>Enseignant</p>
				</div>
			</div>

			<nav class="menu">
				<ul>
					<li class="active"><a href="#"><i class="fas fa-home"></i>
							Accueil</a></li>
					<li><a href="#"><i class="fas fa-calendar"></i> Planning</a></li>
					<li><a href="#"><i class="fas fa-video"></i> Cours en Ligne</a></li>
					<li><a href="#"><i class="fas fa-clipboard-list"></i>
							Évaluations</a></li>
					<li><a href="#"><i class="fas fa-user-graduate"></i> Mes
							étudiants</a></li>
					<li><a href="#"><i class="fas fa-chart-line"></i> Analyses</a>
					</li>
				</ul>
			</nav>

			<div class="sidebar-footer">
				<form action="<%= request.getContextPath() %>/AuthServlet"
					method="post" style="display: inline;">
					<input type="hidden" name="action" value="logout">
					<button type="submit" class="btn-logout">
						<i class="fas fa-sign-out-alt"></i> Déconnexion
					</button>
				</form>

				<button class="btn-help">
					<i class="fas fa-question-circle"></i> Aide
				</button>
			</div>
		</div>

		<!-- Contenu principal -->
		<div class="main-content">
			<header class="top-header">
				<h2>Bonjour, Diouf!</h2>
				<div class="header-actions">
					<button class="btn-notification">
						<i class="fas fa-bell"></i>
					</button>
					<button class="btn-settings">
						<i class="fas fa-cog"></i>
					</button>
				</div>
			</header>

			<div class="content-area">
				<!-- Section Mes cours -->
				<div class="section">
					<div class="section-header">
						<h3>Mes cours</h3>
						<div class="section-actions">
							<span class="badge">nouveau</span>
							<button class="btn-add">
								<i class="fas fa-plus"></i>
							</button>
						</div>
					</div>

					<div class="course-cards">
						<div class="course-card">
							<div class="course-icon">
								<img src="images/algebra-icon.png" alt="Algèbre">
							</div>
							<div class="course-info">
								<h4>Algèbre linéaire I</h4>
								<div class="course-stats">
									<span>Salle: HB1</span> <span>Classe: LTI1</span>
								</div>
							</div>
							<button class="btn-more">
								<i class="fas fa-ellipsis-v"></i>
							</button>
						</div>

						<div class="course-card">
							<div class="course-icon">
								<img src="images/geometry-icon.png" alt="Géométrie">
							</div>
							<div class="course-info">
								<h4>Bases de Donnee</h4>
								<div class="course-stats">
									<span>Salle: SES</span> <span>Classe: DAR</span>
								</div>
							</div>
							<button class="btn-more">
								<i class="fas fa-ellipsis-v"></i>
							</button>
						</div>

						<div class="course-card">
							<div class="course-icon">
								<img src="images/python-icon.png" alt="Python">
							</div>
							<div class="course-info">
								<h4>Python: fonctions</h4>
								<div class="course-stats">
									<span>Salle: SES</span> <span>Classe DAR</span>
								</div>
							</div>
							<button class="btn-more">
								<i class="fas fa-ellipsis-v"></i>
							</button>
						</div>
					</div>
				</div>

				<!-- Section Prochaines sessions -->
				<div class="section">
					<div class="section-header">
						<h3>Prochaines cours</h3>
						<div class="section-actions">
							<span class="badge">nouveau</span>
							<button class="btn-add">
								<i class="fas fa-plus"></i>
							</button>
						</div>
					</div>

					<div class="calendar-container">
						<div class="calendar-header">
							<button class="btn-prev">
								<i class="fas fa-chevron-left"></i>
							</button>
							<h4>Mars 2025</h4>
							<button class="btn-next">
								<i class="fas fa-chevron-right"></i>
							</button>
							<button class="btn-list">Liste</button>
						</div>

						<div class="calendar">
							<div class="calendar-days">
								<div class="day-header">D</div>
								<div class="day-header">L</div>
								<div class="day-header">M</div>
								<div class="day-header">M</div>
								<div class="day-header">J</div>
								<div class="day-header">V</div>
								<div class="day-header">S</div>
							</div>

							<div class="calendar-dates">
								<div class="date">1</div>
								<div class="date">2</div>
								<div class="date">3</div>
								<div class="date">4</div>
								<div class="date">5</div>
								<div class="date">6</div>
								<div class="date">7</div>
								<div class="date">8</div>
								<div class="date">9</div>
								<div class="date">10</div>
								<div class="date">11</div>
								<div class="date">12</div>
								<div class="date">13</div>
								<div class="date">14</div>
								<div class="date">15</div>
								<div class="date dot">16</div>
								<div class="date active">17</div>
								<div class="date">18</div>
								<div class="date">19</div>
								<div class="date">20</div>
								<div class="date">21</div>
								<div class="date">22</div>
								<div class="date">23</div>
								<div class="date">24</div>
								<div class="date">25</div>
								<div class="date">26</div>
								<div class="date">27</div>
								<div class="date">28</div>
								<div class="date">29</div>
								<div class="date">30</div>
								<div class="date">31</div>
								<div class="date next-month">1</div>
								<div class="date next-month">2</div>
								<div class="date next-month">3</div>
								<div class="date next-month">4</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Section Promotion -->
				<div class="section promotion-card">
					<div class="promotion-content">
						<h3>Mes Heurs cette semaine</h3>
						<div class="promotion-stats">
							<h2>20</h2>
							<div class="trend-icon">
								<i class="fas fa-arrow-up"></i>
							</div>
						</div>

						<button class="btn-promotion">En savoir plus</button>
					</div>
				</div>

				<!-- Section Réalisations hebdomadaires -->
				<div class="section full-width">
					<h3>Réalisations hebdomadaires des étudiants</h3>

					<div class="chart-container">
						<div class="chart">
							<!-- Zone du graphique -->
							<div id="achievement-chart"></div>
						</div>

						<div class="chart-legend">
							<div class="legend-item">
								<span class="color-dot green"></span> <span>Aichatou D.</span>
							</div>
							<div class="legend-item">
								<span class="color-dot blue"></span> <span>Khady K.</span>
							</div>
							<div class="legend-item">
								<span class="color-dot purple"></span> <span>Khalil S.</span>
							</div>
							<button class="btn-add-student">
								<i class="fas fa-plus"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/chart.js/3.7.0/chart.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/assets/js/dashboardEnseignant.js"></script>

</body>
</html>