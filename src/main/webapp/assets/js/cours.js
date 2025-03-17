document.addEventListener('DOMContentLoaded', function() {
    // Gestion des filtres de cours
    const filterButtons = document.querySelectorAll('.filter-btn');
    const courseCards = document.querySelectorAll('.course-card');
    
    filterButtons.forEach(button => {
        button.addEventListener('click', function() {
            // Réinitialiser tous les boutons
            filterButtons.forEach(btn => btn.classList.remove('active'));
            
            // Activer le bouton actuel
            this.classList.add('active');
            
            const filter = this.textContent.toLowerCase();
            
            // Filtrer les cours
            courseCards.forEach(card => {
                const courseType = card.querySelector('.course-details p')?.textContent.toLowerCase() || '';
                
                if (filter === 'tous' || courseType.includes(filter)) {
                    card.style.display = 'flex';
                } else {
                    card.style.display = 'none';
                }
            });
        });
    });
    
    // Sélection d'un cours
    courseCards.forEach(card => {
        card.addEventListener('click', function() {
            // Supprimer la classe active de tous les cours
            courseCards.forEach(c => c.classList.remove('selected'));
            
            // Ajouter la classe active au cours sélectionné
            this.classList.add('selected');
            
            // Changement visuel
            this.style.borderLeftWidth = '6px';
        });
    });
    
    // Gestion des rappels
    const reminderBtn = document.querySelector('.reminder-btn');
    if (reminderBtn) {
        reminderBtn.addEventListener('click', function() {
            alert('Rappel ajouté pour ce cours!');
        });
    }
    
    // Gestion du bouton d'inscription
    const registerBtn = document.querySelector('.register-btn');
    if (registerBtn) {
        registerBtn.addEventListener('click', function() {
            window.location.href = 'inscription-cours';
        });
    }
    
    // Toggle sidebar pour mobile
    const menuToggle = document.createElement('button');
    menuToggle.classList.add('menu-toggle');
    menuToggle.innerHTML = '<i class="fas fa-bars"></i>';
    document.querySelector('.header')?.prepend(menuToggle);
    
    menuToggle.addEventListener('click', function() {
        document.querySelector('.sidebar')?.classList.toggle('open');
    });
    
    // Fermer la sidebar lors du clic en dehors
    document.addEventListener('click', function(event) {
        const sidebar = document.querySelector('.sidebar');
        const menuToggle = document.querySelector('.menu-toggle');
        
        if (sidebar && sidebar.classList.contains('open') && 
            !sidebar.contains(event.target) && 
            !menuToggle.contains(event.target)) {
            sidebar.classList.remove('open');
        }
    });
    
    // Animation pour les cartes de cours
    courseCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-5px)';
        });
        
        card.addEventListener('mouseleave', function() {
            if (!this.classList.contains('selected')) {
                this.style.transform = '';
            }
        });
    });
    
    // Gestion des liens dans le panneau latéral
    const actionLinks = document.querySelectorAll('.rectify-link, .edit-link, .library-link');
    actionLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const href = this.getAttribute('href');
            
            if (href === '#') {
                const actionType = this.classList.contains('rectify-link') ? 'rectification de cours' :
                                  this.classList.contains('edit-link') ? 'modification de cours' : 'bibliothèque';
                alert(`Redirection vers la page de ${actionType}...`);
            } else {
                window.location.href = href;
            }
        });
    });
    
    // Fonction pour créer des étoiles d'évaluation
    function createRatingStars(container, rating) {
        const starsContainer = document.createElement('div');
        starsContainer.classList.add('course-rating');
        
        for (let i = 1; i <= 5; i++) {
            const star = document.createElement('i');
            star.classList.add('fas', 'fa-star');
            if (i <= rating) star.classList.add('filled');
            starsContainer.appendChild(star);
        }
        
        container.appendChild(starsContainer);
    }
    
    // Initialiser la recherche de cours
    function initCourseSearch() {
        const searchInput = document.querySelector('.search-bar input');
        
        if (searchInput) {
            searchInput.addEventListener('input', function() {
                const searchTerm = this.value.toLowerCase();
                
                courseCards.forEach(card => {
                    const courseName = card.querySelector('.course-details h3')?.textContent.toLowerCase() || '';
                    const courseCode = card.querySelector('.course-code')?.textContent.toLowerCase() || '';
                    const courseType = card.querySelector('.course-details p')?.textContent.toLowerCase() || '';
                    
                    if (courseName.includes(searchTerm) || courseCode.includes(searchTerm) || courseType.includes(searchTerm)) {
                        card.style.display = 'flex';
                    } else {
                        card.style.display = 'none';
                    }
                });
            });
        }
    }
    
    // Initialiser le système de notifications
    function initNotifications() {
        const notificationIcon = document.querySelector('.notifications');
        
        if (notificationIcon) {
            notificationIcon.addEventListener('click', function(e) {
                e.stopPropagation();
                
                let notificationMenu = document.querySelector('.notification-menu');
                
                if (notificationMenu) {
                    notificationMenu.classList.toggle('hidden');
                } else {
                    notificationMenu = document.createElement('div');
                    notificationMenu.classList.add('notification-menu');
                    notificationMenu.innerHTML = `
                        <div class="notification-header">
                            <h3>Notifications</h3>
                            <button class="mark-all-read">Tout marquer comme lu</button>
                        </div>
                        <div class="notification-list">
                            <div class="notification-item unread">
                                <div class="notification-icon"><i class="fas fa-book"></i></div>
                                <div class="notification-content">
                                    <p>Nouveau document ajouté dans <strong>Réseaux Neuronaux</strong></p>
                                    <span class="notification-time">Il y a 2 heures</span>
                                </div>
                            </div>
                        </div>
                        <div class="notification-footer">
                            <a href="#">Voir toutes les notifications</a>
                        </div>
                    `;
                    document.body.appendChild(notificationMenu);
                }
            });
        }
    }
    
    initCourseSearch();
    initNotifications();
});
