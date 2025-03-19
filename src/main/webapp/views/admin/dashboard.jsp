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

// Récupérer les informations de l'administrateur connecté
String adminNom = "";
String adminPrenom = "";
try {
    Connection conn = DatabaseConnection.getConnection();
    PreparedStatement pstmt = conn.prepareStatement("SELECT nom, prenom FROM utilisateurs WHERE id = ?");
    pstmt.setInt(1, userId);
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
        adminNom = rs.getString("nom");
        adminPrenom = rs.getString("prenom");
    }
    rs.close();
    pstmt.close();
    conn.close();
} catch (Exception e) {
    e.printStackTrace();
}
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Administrateur</title>
    <link rel="stylesheet" href="../../assets/css/admin-dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="app-container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="sidebar-header">
                <img src="../../assets/images/logo.png" alt="Logo" class="logo">
                <h2>Portail Admin</h2>
            </div>
            
            <div class="sidebar-menu">
                <ul>
                    <li class="active">
                        <a href="dashboard.jsp">
                            <i class="fas fa-tachometer-alt"></i>
                            <span>Tableau de bord</span>
                        </a>
                    </li>
                    <li>
                        <a href="../utilisateur/gestion_utilisateurs.jsp">
                            <i class="fas fa-users-cog"></i>
                            <span>Utilisateurs</span>
                        </a>
                    </li>
                    <li>
                        <a href="../etudiant/liste_etudiants.jsp">
                            <i class="fas fa-user-graduate"></i>
                            <span>Étudiants</span>
                        </a>
                    </li>
                    <li>
                        <a href="../enseignant/liste_enseignants.jsp">
                            <i class="fas fa-chalkboard-teacher"></i>
                            <span>Enseignants</span>
                        </a>
                    </li>
                    <li>
                        <a href="../academique/gestion_academique.jsp">
                            <i class="fas fa-university"></i>
                            <span>Gestion Académique</span>
                        </a>
                    </li>
                    <li>
                        <a href="../inscription/gestion_inscriptions.jsp">
                            <i class="fas fa-clipboard-list"></i>
                            <span>Inscriptions</span>
                        </a>
                    </li>
                    <li>
                        <a href="../statistiques/rapports.jsp">
                            <i class="fas fa-chart-bar"></i>
                            <span>Statistiques</span>
                        </a>
                    </li>
                    <li>
                        <a href="../configuration/parametres.jsp">
                            <i class="fas fa-cogs"></i>
                            <span>Configuration</span>
                        </a>
                    </li>
                    <li>
                        <a href="../planification/calendrier.jsp">
                            <i class="fas fa-calendar-alt"></i>
                            <span>Calendrier</span>
                        </a>
                    </li>
                </ul>
            </div>
            
            <div class="sidebar-footer">
                <a href="../../AuthServlet?action=logout" class="logout-btn">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Déconnexion</span>
                </a>
            </div>
        </aside>
        
        <!-- Main Content -->
        <main class="main-content">
            <!-- Header -->
            <header class="header">
                <div class="header-left">
                    <button id="sidebar-toggle" class="sidebar-toggle">
                        <i class="fas fa-bars"></i>
                    </button>
                    <div class="search-container">
                        <i class="fas fa-search"></i>
                        <input type="text" placeholder="Rechercher...">
                    </div>
                </div>
                
                <div class="header-right">
                    <div class="notification-icon">
                        <i class="fas fa-bell"></i>
                        <span class="badge">3</span>
                    </div>
                    <div class="user-profile">
                        <img src="../../assets/images/admin-avatar.jpg" alt="Admin" class="avatar">
                        <span><%= adminPrenom %> <%= adminNom %></span>
                        <i class="fas fa-chevron-down"></i>
                    </div>
                </div>
            </header>
            
            <!-- Dashboard Content -->
            <div class="dashboard-content">
                <div class="page-header">
                    <h1>Tableau de bord</h1>
                    <div class="date-selector">
                        <span>Année académique: </span>
                        <select>
                            <option>2024-2025</option>
                            <option>2023-2024</option>
                            <option>2022-2023</option>
                        </select>
                    </div>
                </div>
                
                <!-- Stats Cards -->
                <div class="stats-cards">
                    <div class="stat-card" style="--accent-color: var(--bleu);">
                        <div class="stat-icon">
                            <i class="fas fa-user-graduate"></i>
                        </div>
                        <div class="stat-details">
                            <h3>2,458</h3>
                            <p>Étudiants Inscrits</p>
                        </div>
                        <div class="stat-footer">
                            <span class="positive">+12%</span> depuis l'année dernière
                        </div>
                    </div>
                    
                    <div class="stat-card" style="--accent-color: var(--violet);">
                        <div class="stat-icon">
                            <i class="fas fa-chalkboard-teacher"></i>
                        </div>
                        <div class="stat-details">
                            <h3>142</h3>
                            <p>Enseignants</p>
                        </div>
                        <div class="stat-footer">
                            <span class="positive">+5%</span> depuis l'année dernière
                        </div>
                    </div>
                    
                    <div class="stat-card" style="--accent-color: var(--orange);">
                        <div class="stat-icon">
                            <i class="fas fa-clipboard-check"></i>
                        </div>
                        <div class="stat-details">
                            <h3>87%</h3>
                            <p>Taux de Réussite</p>
                        </div>
                        <div class="stat-footer">
                            <span class="positive">+3%</span> depuis le semestre dernier
                        </div>
                    </div>
                    
                    <div class="stat-card" style="--accent-color: var(--vert);">
                        <div class="stat-icon">
                            <i class="fas fa-check-circle"></i>
                        </div>
                        <div class="stat-details">
                            <h3>95%</h3>
                            <p>Présence Enseignants</p>
                        </div>
                        <div class="stat-footer">
                            <span class="positive">+2%</span> depuis le mois dernier
                        </div>
                    </div>
                </div>
                
                <!-- Main Dashboard Sections -->
                <div class="dashboard-grid">
                    <!-- Left Column -->
                    <div class="dashboard-col">
                        <!-- Activités en cours -->
                        <div class="card">
                            <div class="card-header">
                                <h2>Activités en cours</h2>
                                <div class="card-actions">
                                    <button class="card-action-btn">
                                        <i class="fas fa-ellipsis-v"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="activity-item">
                                    <div class="activity-content">
                                        <div class="activity-title">Réunion du conseil</div>
                                        <div class="activity-details">
                                            <span>9h00 - 11h00</span>
                                            <span>|</span> 
                                            <span>Salle de conférence A</span>
                                        </div>
                                    </div>
                                    <div class="activity-course-code">ADM 101</div>
                                </div>
                                
                                <div class="activity-item">
                                    <div class="activity-content">
                                        <div class="activity-title">Évaluation des programmes</div>
                                        <div class="activity-details">
                                            <span>13h00 - 15h00</span>
                                            <span>|</span> 
                                            <span>Salle du département</span>
                                        </div>
                                    </div>
                                    <div class="activity-course-code">ADM 204</div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <a href="../planification/calendrier.jsp" class="view-all">Voir le calendrier complet</a>
                            </div>
                        </div>
                        
                        <!-- Demandes à traiter -->
                        <div class="card">
                            <div class="card-header">
                                <h2>Demandes à traiter</h2>
                                <div class="card-actions">
                                    <span class="badge danger">14</span>
                                    <button class="card-action-btn">
                                        <i class="fas fa-ellipsis-v"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="request-item">
                                    <div class="request-icon warning">
                                        <i class="fas fa-user-plus"></i>
                                    </div>
                                    <div class="request-content">
                                        <div class="request-title">Nouvelles inscriptions</div>
                                        <div class="request-count">8 en attente</div>
                                    </div>
                                    <a href="../inscription/validation_inscriptions.jsp" class="request-action-btn">
                                        <i class="fas fa-arrow-right"></i>
                                    </a>
                                </div>
                                
                                <div class="request-item">
                                    <div class="request-icon danger">
                                        <i class="fas fa-exclamation-triangle"></i>
                                    </div>
                                    <div class="request-content">
                                        <div class="request-title">Réclamations de notes</div>
                                        <div class="request-count">4 à traiter</div>
                                    </div>
                                    <a href="../reclamations/liste_reclamations.jsp" class="request-action-btn">
                                        <i class="fas fa-arrow-right"></i>
                                    </a>
                                </div>
                                
                                <div class="request-item">
                                    <div class="request-icon info">
                                        <i class="fas fa-file-alt"></i>
                                    </div>
                                    <div class="request-content">
                                        <div class="request-title">Demandes de documents</div>
                                        <div class="request-count">2 à valider</div>
                                    </div>
                                    <a href="../documents/validation_documents.jsp" class="request-action-btn">
                                        <i class="fas fa-arrow-right"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="card-footer">
                                <a href="../demandes/toutes_demandes.jsp" class="view-all">Voir toutes les demandes</a>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Right Column -->
                    <div class="dashboard-col">
                        <!-- Calendrier -->
                        <div class="card">
                            <div class="card-header">
                                <h2>Calendrier</h2>
                                <div class="card-actions">
                                    <div class="date-selector mini">
                                        <span>Juin 2024</span>
                                        <div class="date-nav">
                                            <button><i class="fas fa-chevron-left"></i></button>
                                            <button><i class="fas fa-chevron-right"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="calendar">
                                    <div class="calendar-header">
                                        <div>Lun</div>
                                        <div>Mar</div>
                                        <div>Mer</div>
                                        <div>Jeu</div>
                                        <div>Ven</div>
                                        <div>Sam</div>
                                        <div>Dim</div>
                                    </div>
                                    <div class="calendar-body">
                                        <div class="date muted">29</div>
                                        <div class="date muted">30</div>
                                        <div class="date muted">31</div>
                                        <div class="date">1</div>
                                        <div class="date">2</div>
                                        <div class="date weekend">3</div>
                                        <div class="date weekend">4</div>
                                        <div class="date">5</div>
                                        <div class="date">6</div>
                                        <div class="date">7</div>
                                        <div class="date">8</div>
                                        <div class="date">9</div>
                                        <div class="date weekend">10</div>
                                        <div class="date weekend">11</div>
                                        <div class="date">12</div>
                                        <div class="date">13</div>
                                        <div class="date">14</div>
                                        <div class="date">15</div>
                                        <div class="date has-event">16</div>
                                        <div class="date weekend">17</div>
                                        <div class="date weekend">18</div>
                                        <div class="date current">19</div>
                                        <div class="date has-event">20</div>
                                        <div class="date">21</div>
                                        <div class="date">22</div>
                                        <div class="date">23</div>
                                        <div class="date weekend">24</div>
                                        <div class="date weekend">25</div>
                                        <div class="date">26</div>
                                        <div class="date">27</div>
                                        <div class="date">28</div>
                                        <div class="date">29</div>
                                        <div class="date">30</div>
                                        <div class="date muted weekend">1</div>
                                        <div class="date muted weekend">2</div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <div class="add-event-btn">
                                    <i class="fas fa-plus"></i>
                                    <span>Ajouter un événement</span>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Statistiques rapides -->
                        <div class="card">
                            <div class="card-header">
                                <h2>Statistiques rapides</h2>
                                <div class="card-actions">
                                    <button class="card-action-btn">
                                        <i class="fas fa-download"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="stats-chart">
                                    <div class="stat-chart-info">
                                        <h3>Taux de réussite par filière</h3>
                                        <div class="chart-placeholder" id="success-rate-chart">
                                            <!-- Le graphique sera rendu ici par JS -->
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="stats-list">
                                    <div class="stat-list-item">
                                        <div class="stat-list-name">Informatique</div>
                                        <div class="stat-list-progress">
                                            <div class="progress-bar">
                                                <div class="progress-value" style="width: 92%"></div>
                                            </div>
                                            <div class="progress-text">92%</div>
                                        </div>
                                    </div>
                                    
                                    <div class="stat-list-item">
                                        <div class="stat-list-name">Gestion</div>
                                        <div class="stat-list-progress">
                                            <div class="progress-bar">
                                                <div class="progress-value" style="width: 87%"></div>
                                            </div>
                                            <div class="progress-text">87%</div>
                                        </div>
                                    </div>
                                    
                                    <div class="stat-list-item">
                                        <div class="stat-list-name">Communication</div>
                                        <div class="stat-list-progress">
                                            <div class="progress-bar">
                                                <div class="progress-value" style="width: 85%"></div>
                                            </div>
                                            <div class="progress-text">85%</div>
                                        </div>
                                    </div>
                                    
                                    <div class="stat-list-item">
                                        <div class="stat-list-name">Électronique</div>
                                        <div class="stat-list-progress">
                                            <div class="progress-bar">
                                                <div class="progress-value" style="width: 78%"></div>
                                            </div>
                                            <div class="progress-text">78%</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <a href="../statistiques/rapports.jsp" class="view-all">Rapports détaillés</a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Messages Section -->
                <div class="card messages-card">
                    <div class="card-header">
                        <h2>Messages</h2>
                        <div class="card-actions">
                            <button class="card-action-btn">
                                <i class="fas fa-plus"></i>
                                <span>Nouveau message</span>
                            </button>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="message-item">
                            <div class="message-icon system">
                                <i class="fas fa-cog"></i>
                            </div>
                            <div class="message-content">
                                <div class="message-title">Maintenance Planifiée</div>
                                <div class="message-text">
                                    Le système sera indisponible pour maintenance le vendredi 22 juin de 22h00 à 2h00 du matin.
                                </div>
                            </div>
                        </div>
                        
                        <div class="message-item">
                            <div class="message-icon">
                                <img src="../../assets/images/user-avatar.jpg" alt="User">
                            </div>
                            <div class="message-content">
                                <div class="message-title">Antoine Dupont, Directeur des études</div>
                                <div class="message-text">
                                    Veuillez préparer les salles pour la réunion du comité pédagogique prévue demain à 14h.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="../messages/boite_reception.jsp" class="view-all">Tous les messages</a>
                    </div>
                </div>
            </div>
        </main>
    </div>
    
    <script src="../../assets/js/admin-dashboard.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/chart.js/3.9.1/chart.min.js"></script>
</body>
</html>