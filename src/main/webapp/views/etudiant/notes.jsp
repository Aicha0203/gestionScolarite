<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession, java.util.List, models.Note" %>

<%
HttpSession sessionUser = request.getSession();
Integer etudiantId = (Integer) sessionUser.getAttribute("userId");

if (etudiantId == null) {
    response.sendRedirect(request.getContextPath() + "/views/utilisateur/login.jsp");
    return;
}

List<Note> notes = (List<Note>) request.getAttribute("notes");

if (notes == null) {
    notes = List.of(); // ✅ Empêcher l'erreur NullPointerException
}

String etudiantNom = (String) sessionUser.getAttribute("userName");
if (etudiantNom == null) {
    etudiantNom = "Étudiant";
}

// Calculer la moyenne générale
double moyenneGenerale = 0;
int totalCredits = 0;
int totalReussis = 0;

if (!notes.isEmpty()) {
    double sommeNotePonderee = 0;
    for (Note note : notes) {
        int credit = note.getCredit() != null ? note.getCredit() : 1;
        double valeur = note.getValeur() != null ? note.getValeur() : 0;
        
        sommeNotePonderee += valeur * credit;
        totalCredits += credit;
        
        if (valeur >= 10) {
            totalReussis += credit;
        }
    }
    
    if (totalCredits > 0) {
        moyenneGenerale = sommeNotePonderee / totalCredits;
    }
}
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portail Étudiant - Notes</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/note.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="layout">
        <!-- Barre latérale -->
        <aside class="sidebar">
            <div class="logo">
                <img src="<%=request.getContextPath()%>/assets/images/logo.png" alt="Logo">
                <h1>Portail Étudiant</h1>
            </div>
            
            <div class="search-bar">
                <i class="fas fa-search"></i>
                <input type="text" placeholder="Rechercher">
            </div>
            
            <nav class="menu">
                <ul>
                    <li><a href="<%=request.getContextPath()%>/dashboard"><i class="fas fa-th-large"></i> Tableau de bord</a></li>
                    <li><a href="<%=request.getContextPath()%>/cours"><i class="fas fa-book"></i> Cours</a></li>
                    <li><a href="<%=request.getContextPath()%>/emploi-du-temps"><i class="fas fa-calendar"></i> Emploi du temps</a></li>
                    <li><a href="<%=request.getContextPath()%>/finances"><i class="fas fa-wallet"></i> Finances</a></li>
                    <li class="active"><a href="<%=request.getContextPath()%>/resultats"><i class="fas fa-chart-bar"></i> Résultats</a></li>
                    <li><a href="<%=request.getContextPath()%>/aide"><i class="fas fa-question-circle"></i> Aide</a></li>
                </ul>
                <div class="logout">
                    <a href="<%=request.getContextPath()%>/deconnexion"><i class="fas fa-sign-out-alt"></i> Déconnexion</a>
                </div>
            </nav>
        </aside>
        
        <!-- Contenu principal -->
        <main class="main-content">
            <!-- En-tête -->
            <header class="header">
                <h1>Résultats Académiques</h1>
                <div class="user-actions">
                    <div class="notifications">
                        <i class="fas fa-bell"></i>
                        <span class="badge">1</span>
                    </div>
                    <div class="user-info">
                        <img src="<%=request.getContextPath()%>/assets/images/avatar.png" alt="Avatar">
                        <span><%= etudiantNom %></span>
                    </div>
                    <div class="more-options">
                        <i class="fas fa-ellipsis-v"></i>
                    </div>
                </div>
            </header>
            
            <!-- Filtres de semestre -->
            <div class="semester-filters">
                <button class="filter-btn active">Tous les semestres</button>
                <button class="filter-btn">Semestre 1</button>
                <button class="filter-btn">Semestre 2</button>
                <button class="filter-btn">Semestre 3</button>
                <button class="filter-btn">Semestre 4</button>
            </div>
            
            <!-- Contenu des résultats -->
            <div class="results-container">
                <!-- Colonne de gauche: Tableau des notes -->
                <div class="results-table-container">
                    <div class="table-header">
                        <h2>Relevé de Notes</h2>
                        <div class="table-actions">
                            <button class="action-btn"><i class="fas fa-download"></i> Télécharger</button>
                            <button class="action-btn"><i class="fas fa-print"></i> Imprimer</button>
                        </div>
                    </div>
                    
                    <div class="table-wrapper">
                        <table class="results-table">
                            <thead>
                                <tr>
                                    <th>Code</th>
                                    <th>Matière</th>
                                    <th>Note/20</th>
                                    <th>Crédits</th>
                                    <th>Statut</th>
                                    <th>Session</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (notes.isEmpty()) { %>
                                    <tr>
                                        <td colspan="6" class="empty-results">Aucune note disponible pour le moment.</td>
                                    </tr>
                                <% } else { %>
                                    <% for (Note note : notes) { %>
                                        <tr class="<%= note.getValeur() >= 10 ? "passed" : "failed" %>">
                                            <td><%= note.getCodeMatiere() != null ? note.getCodeMatiere() : "N/A" %></td>
                                            <td><%= note.getNomMatiere() != null ? note.getNomMatiere() : "N/A" %></td>
                                            <td class="note-value"><%= String.format("%.2f", note.getValeur()) %></td>
                                            <td><%= note.getCredit() != null ? note.getCredit() : "-" %></td>
                                            <td class="status">
                                                <span class="status-badge <%= note.getValeur() >= 10 ? "success" : "danger" %>">
                                                    <%= note.getValeur() >= 10 ? "Réussi" : "Échoué" %>
                                                </span>
                                            </td>
                                            <td><%= note.getSession() != null ? note.getSession() : "Normale" %></td>
                                        </tr>
                                    <% } %>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
                
                <!-- Colonne de droite: Statistiques et informations -->
                <div class="results-info-panel">
                    <!-- Carte de résumé académique -->
                    <div class="academic-summary">
                        <h3>Résumé Académique</h3>
                        <div class="summary-stats">
                            <div class="stat-item">
                                <div class="stat-value"><%= String.format("%.2f", moyenneGenerale) %></div>
                                <div class="stat-label">Moyenne Générale</div>
                            </div>
                            <div class="stat-item">
                                <div class="stat-value"><%= totalCredits %></div>
                                <div class="stat-label">Crédits Tentés</div>
                            </div>
                            <div class="stat-item">
                                <div class="stat-value"><%= totalReussis %></div>
                                <div class="stat-label">Crédits Réussis</div>
                            </div>
                        </div>
                        
                        <div class="progress-container">
                            <div class="progress-label">
                                <span>Progression du Diplôme</span>
                                <span><%= totalReussis %>/180 crédits</span>
                            </div>
                            <div class="progress-bar">
                                <div class="progress-fill" style="width: <%= Math.min(100, totalReussis * 100 / 180) %>%;"></div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Graphique de performance -->
                    <div class="performance-chart">
                        <h3>Performance Par Semestre</h3>
                        <div class="chart-container" id="semesterChart"></div>
                    </div>
                    
                    <!-- Répartition des notes -->
                    <div class="grade-distribution">
                        <h3>Répartition des Notes</h3>
                        <div class="distribution-container">
                            <div class="distribution-item">
                                <div class="distribution-range">18-20</div>
                                <div class="distribution-bar-container">
                                    <div class="distribution-bar" style="width: 15%;"></div>
                                    <span class="distribution-value">15%</span>
                                </div>
                            </div>
                            <div class="distribution-item">
                                <div class="distribution-range">16-18</div>
                                <div class="distribution-bar-container">
                                    <div class="distribution-bar" style="width: 25%;"></div>
                                    <span class="distribution-value">25%</span>
                                </div>
                            </div>
                            <div class="distribution-item">
                                <div class="distribution-range">14-16</div>
                                <div class="distribution-bar-container">
                                    <div class="distribution-bar" style="width: 30%;"></div>
                                    <span class="distribution-value">30%</span>
                                </div>
                            </div>
                            <div class="distribution-item">
                                <div class="distribution-range">12-14</div>
                                <div class="distribution-bar-container">
                                    <div class="distribution-bar" style="width: 20%;"></div>
                                    <span class="distribution-value">20%</span>
                                </div>
                            </div>
                            <div class="distribution-item">
                                <div class="distribution-range">10-12</div>
                                <div class="distribution-bar-container">
                                    <div class="distribution-bar" style="width: 5%;"></div>
                                    <span class="distribution-value">5%</span>
                                </div>
                            </div>
                            <div class="distribution-item">
                                <div class="distribution-range">0-10</div>
                                <div class="distribution-bar-container">
                                    <div class="distribution-bar" style="width: 5%;"></div>
                                    <span class="distribution-value">5%</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Services académiques -->
                    <div class="academic-services">
                        <h3>Services Académiques</h3>
                        <div class="services-list">
                            <a href="#" class="service-item">
                                <i class="fas fa-file-alt"></i>
                                <span>Demander une attestation</span>
                            </a>
                            <a href="#" class="service-item">
                                <i class="fas fa-exclamation-circle"></i>
                                <span>Contestation de note</span>
                            </a>
                            <a href="#" class="service-item">
                                <i class="fas fa-user-graduate"></i>
                                <span>Consulter un conseiller</span>
                            </a>
                            <a href="#" class="service-item">
                                <i class="fas fa-calendar-check"></i>
                                <span>Planifier un rendez-vous</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/note.js"></script>
</body>
</html>