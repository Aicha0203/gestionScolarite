document.addEventListener('DOMContentLoaded', function() {
    // Toggle sidebar
    const sidebarToggle = document.getElementById('sidebar-toggle');
    const appContainer = document.querySelector('.app-container');
    
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', function() {
            appContainer.classList.toggle('sidebar-collapsed');
        });
    }
    
    // Initialiser les graphiques
    initializeCharts();
    
    // Notifications dropdown
    const notificationIcon = document.querySelector('.notification-icon');
    if (notificationIcon) {
        notificationIcon.addEventListener('click', function() {
            // Implémenter l'affichage du dropdown des notifications
            console.log('Afficher les notifications');
        });
    }
    
    // Profil utilisateur dropdown
    const userProfile = document.querySelector('.user-profile');
    if (userProfile) {
        userProfile.addEventListener('click', function() {
            // Implémenter l'affichage du dropdown du profil
            console.log('Afficher les options du profil');
        });
    }
    
    // Animation des cartes statistiques
    animateStatCards();
    
    // Ajouter des écouteurs d'événements pour les dates du calendrier
    addCalendarEventListeners();
});

// Initialisation des graphiques avec Chart.js
function initializeCharts() {
    // Vérifier si l'élément chart-container existe
    const successRateChart = document.getElementById('success-rate-chart');
    
    if (successRateChart && typeof Chart !== 'undefined') {
        // Créer le contexte du canvas
        successRateChart.innerHTML = '<canvas id="success-rate-canvas"></canvas>';
        const ctx = document.getElementById('success-rate-canvas').getContext('2d');
        
        // Données pour le graphique
        const data = {
            labels: ['Informatique', 'Gestion', 'Communication', 'Électronique', 'Mécanique'],
            datasets: [{
                label: 'Taux de réussite (%)',
                data: [92, 87, 85, 78, 82],
                backgroundColor: [
                    'rgba(79, 70, 229, 0.7)',
                    'rgba(129, 140, 248, 0.7)',
                    'rgba(139, 92, 246, 0.7)',
                    'rgba(59, 130, 246, 0.7)',
                    'rgba(16, 185, 129, 0.7)'
                ],
                borderColor: [
                    'rgba(79, 70, 229, 1)',
                    'rgba(129, 140, 248, 1)',
                    'rgba(139, 92, 246, 1)',
                    'rgba(59, 130, 246, 1)',
                    'rgba(16, 185, 129, 1)'
                ],
                borderWidth: 1
            }]
        };
        
        // Options du graphique
        const options = {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true,
                    max: 100,
                    ticks: {
                        callback: function(value) {
                            return value + '%';
                        }
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                }
            }
        };
        
        // Créer le graphique
        new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });
    }
}

// Animation des cartes statistiques
function animateStatCards() {
    const statCards = document.querySelectorAll('.stat-card');
    
    statCards.forEach((card, index) => {
        // Ajouter un délai progressif pour chaque carte
        setTimeout(() => {
            card.style.opacity = '0';
            card.style.transform = 'translateY(20px)';
            card.style.transition = 'opacity 0.5s, transform 0.5s';
            
            setTimeout(() => {
                card.style.opacity = '1';
                card.style.transform = 'translateY(0)';
            }, 50);
        }, index * 100);
    });
}

// Ajouter des écouteurs d'événements pour les dates du calendrier
function addCalendarEventListeners() {
    const dates = document.querySelectorAll('.calendar-body .date');
    
    dates.forEach(date => {
        date.addEventListener('click', function() {
            // Supprimer la classe 'selected' de toutes les dates
            dates.forEach(d => d.classList.remove('selected'));
            
            // Ajouter la classe 'selected' à la date cliquée
            this.classList.add('selected');
            
            // Récupérer le jour sélectionné (pour des actions futures)
            const selectedDay = this.textContent.trim();
            console.log(`Jour sélectionné: ${selectedDay}`);
            
            // Ici, vous pourriez charger les événements pour cette date
        });
    });
    
    // Boutons de navigation du calendrier
    const prevBtn = document.querySelector('.date-nav button:first-child');
    const nextBtn = document.querySelector('.date-nav button:last-child');
    const monthDisplay = document.querySelector('.date-selector.mini span');
    
    if (prevBtn && nextBtn && monthDisplay) {
        const months = ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'];
        let currentDate = new Date();
        
        updateCalendarHeader();
        
        prevBtn.addEventListener('click', function() {
            currentDate.setMonth(currentDate.getMonth() - 1);
            updateCalendarHeader();
            // Ici, vous rechargeriez les dates du mois précédent
        });
        
        nextBtn.addEventListener('click', function() {
            currentDate.setMonth(currentDate.getMonth() + 1);
            updateCalendarHeader();
            // Ici, vous rechargeriez les dates du mois suivant
        });
        
        function updateCalendarHeader() {
            const month = months[currentDate.getMonth()];
            const year = currentDate.getFullYear();
            monthDisplay.textContent = `${month} ${year}`;
        }
    }
}

// Fonction pour initialiser les tables de données (si nécessaire)
function initializeDataTables() {
    // Si vous utilisez des tableaux de données, vous pouvez les initialiser ici
}

// Gestionnaire pour les actions sur les cartes
document.addEventListener('click', function(e) {
    // Gérer les clics sur les boutons d'action des cartes
    if (e.target.matches('.card-action-btn') || e.target.closest('.card-action-btn')) {
        const button = e.target.matches('.card-action-btn') ? e.target : e.target.closest('.card-action-btn');
        const card = button.closest('.card');
        
        // Exemple: toggle du contenu de la carte
        const cardBody = card.querySelector('.card-body');
        if (cardBody) {
            if (cardBody.style.display === 'none') {
                cardBody.style.display = 'block';
            } else {
                cardBody.style.display = 'none';
            }
        }
    }
    
	// Gérer les clics sur le bouton "Ajouter un événement"
	    if (e.target.matches('.add-event-btn') || e.target.closest('.add-event-btn')) {
	        // Montrer un modal ou rediriger vers la page d'ajout d'événement
	        console.log('Ajouter un nouvel événement');
	        // Vous pourriez implémenter ici l'ouverture d'un modal
	        showAddEventModal();
	    }
	});

	// Fonction pour afficher le modal d'ajout d'événement
	function showAddEventModal() {
	    // Créer dynamiquement un modal si vous n'avez pas d'élément HTML existant
	    const modal = document.createElement('div');
	    modal.className = 'modal';
	    modal.innerHTML = `
	        <div class="modal-content">
	            <div class="modal-header">
	                <h2>Ajouter un événement</h2>
	                <button class="close-btn">&times;</button>
	            </div>
	            <div class="modal-body">
	                <form id="event-form">
	                    <div class="form-group">
	                        <label for="event-title">Titre de l'événement</label>
	                        <input type="text" id="event-title" name="event-title" required>
	                    </div>
	                    <div class="form-group">
	                        <label for="event-date">Date</label>
	                        <input type="date" id="event-date" name="event-date" required>
	                    </div>
	                    <div class="form-group">
	                        <label for="event-time-start">Heure de début</label>
	                        <input type="time" id="event-time-start" name="event-time-start" required>
	                    </div>
	                    <div class="form-group">
	                        <label for="event-time-end">Heure de fin</label>
	                        <input type="time" id="event-time-end" name="event-time-end" required>
	                    </div>
	                    <div class="form-group">
	                        <label for="event-location">Lieu</label>
	                        <input type="text" id="event-location" name="event-location">
	                    </div>
	                    <div class="form-group">
	                        <label for="event-description">Description</label>
	                        <textarea id="event-description" name="event-description"></textarea>
	                    </div>
	                    <div class="form-group">
	                        <label for="event-course">Code de cours (optionnel)</label>
	                        <input type="text" id="event-course" name="event-course">
	                    </div>
	                    <div class="form-actions">
	                        <button type="button" class="cancel-btn">Annuler</button>
	                        <button type="submit" class="submit-btn">Enregistrer</button>
	                    </div>
	                </form>
	            </div>
	        </div>
	    `;
	    
	    document.body.appendChild(modal);
	    
	    // Gérer la fermeture du modal
	    const closeBtn = modal.querySelector('.close-btn');
	    const cancelBtn = modal.querySelector('.cancel-btn');
	    
	    closeBtn.addEventListener('click', () => {
	        document.body.removeChild(modal);
	    });
	    
	    cancelBtn.addEventListener('click', () => {
	        document.body.removeChild(modal);
	    });
	    
	    // Gérer la soumission du formulaire
	    const form = modal.querySelector('#event-form');
	    form.addEventListener('submit', (e) => {
	        e.preventDefault();
	        
	        // Récupérer les valeurs du formulaire
	        const title = document.getElementById('event-title').value;
	        const date = document.getElementById('event-date').value;
	        const timeStart = document.getElementById('event-time-start').value;
	        const timeEnd = document.getElementById('event-time-end').value;
	        const location = document.getElementById('event-location').value;
	        const description = document.getElementById('event-description').value;
	        const course = document.getElementById('event-course').value;
	        
	        // Ici, vous implémenteriez la logique pour enregistrer l'événement
	        console.log('Nouvel événement:', { title, date, timeStart, timeEnd, location, description, course });
	        
	        // Exemple: Envoi des données au serveur
	        /* 
	        fetch('../planification/ajout_evenement.jsp', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded',
	            },
	            body: new URLSearchParams({
	                title: title,
	                date: date,
	                timeStart: timeStart,
	                timeEnd: timeEnd,
	                location: location,
	                description: description,
	                course: course
	            })
	        })
	        .then(response => response.json())
	        .then(data => {
	            console.log('Succès:', data);
	            // Fermer le modal après l'enregistrement
	            document.body.removeChild(modal);
	            // Rafraîchir le calendrier
	            refreshCalendar();
	        })
	        .catch((error) => {
	            console.error('Erreur:', error);
	        });
	        */
	        
	        // Pour l'instant, fermons simplement le modal
	        document.body.removeChild(modal);
	    });
	}

	// Fonction pour rafraîchir le calendrier (à implémenter selon vos besoins)
	function refreshCalendar() {
	    // Récupérer les événements du mois actuel depuis le serveur
	    // et mettre à jour l'affichage du calendrier
	    console.log('Rafraîchissement du calendrier');
	}

	// Fonction pour initialiser les notifications (peut être appelée au chargement)
	function initializeNotifications() {
	    // Récupérer les notifications depuis le serveur
	    fetch('../notification/get_notifications.jsp')
	        .then(response => response.json())
	        .then(data => {
	            // Mettre à jour le compteur de notifications
	            const badge = document.querySelector('.notification-icon .badge');
	            if (badge) {
	                badge.textContent = data.length;
	            }
	            
	            // Stocker les notifications pour affichage ultérieur
	            window.appNotifications = data;
	        })
	        .catch(error => {
	            console.error('Erreur lors de la récupération des notifications:', error);
	        });
	}

	// Appeler cette fonction au chargement si vous souhaitez initialiser les notifications
	// initializeNotifications();