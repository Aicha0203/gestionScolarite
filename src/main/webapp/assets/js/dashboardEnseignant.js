document.addEventListener('DOMContentLoaded', function() {
    // Initialisation du graphique d'accomplissements
    initAchievementChart();
    
    // Gestion des boutons du calendrier
    initCalendarButtons();
    
    // Initialisation des interactions pour les cartes de cours
    initCourseCards();
});

/**
 * Initialise le graphique de réalisations hebdomadaires
 */
function initAchievementChart() {
    const ctx = document.getElementById('achievement-chart').getContext('2d');
    
    // Configuration des données
    const labels = ['1 Août', '2 Août', '3 Août', '4 Août', '5 Août', '6 Août', '7 Août', '8 Août'];
    
    // Génération de données ondulatoires pour les 3 étudiants
    const michaelData = generateWaveData(50, 100, 8, 0);
    const ingaData = generateWaveData(30, 80, 8, 2);
    const benData = generateWaveData(40, 90, 8, 4);
    
    const data = {
        labels: labels,
        datasets: [
            {
                label: 'Michaël L.',
                data: michaelData,
                borderColor: getComputedStyle(document.documentElement).getPropertyValue('--green'),
                backgroundColor: 'transparent',
                tension: 0.4,
                borderWidth: 2,
                pointRadius: 0,
                pointHoverRadius: 4
            },
            {
                label: 'Inga K.',
                data: ingaData,
                borderColor: getComputedStyle(document.documentElement).getPropertyValue('--blue'),
                backgroundColor: 'transparent',
                tension: 0.4,
                borderWidth: 2,
                pointRadius: 0,
                pointHoverRadius: 4
            },
            {
                label: 'Ben T.',
                data: benData,
                borderColor: getComputedStyle(document.documentElement).getPropertyValue('--purple'),
                backgroundColor: 'transparent',
                tension: 0.4,
                borderWidth: 2,
                pointRadius: 0,
                pointHoverRadius: 4
            }
        ]
    };
    
    // Configuration du graphique
    const config = {
        type: 'line',
        data: data,
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    backgroundColor: 'rgba(255, 255, 255, 0.9)',
                    titleColor: getComputedStyle(document.documentElement).getPropertyValue('--text-color'),
                    bodyColor: getComputedStyle(document.documentElement).getPropertyValue('--text-color'),
                    borderColor: getComputedStyle(document.documentElement).getPropertyValue('--border-color'),
                    borderWidth: 1,
                    cornerRadius: 4,
                    displayColors: true,
                    padding: 10,
                    callbacks: {
                        title: function(tooltipItems) {
                            return tooltipItems[0].label;
                        }
                    }
                }
            },
            scales: {
                x: {
                    grid: {
                        display: false
                    },
                    ticks: {
                        font: {
                            size: 10
                        }
                    }
                },
                y: {
                    beginAtZero: true,
                    max: 100,
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)'
                    },
                    ticks: {
                        font: {
                            size: 10
                        },
                        callback: function(value) {
                            return value + '%';
                        }
                    }
                }
            }
        }
    };
    
    // Création du graphique
    new Chart(ctx, config);
}

/**
 * Génère des données ondulantes pour simuler les performances
 * @param {number} min - Valeur minimale
 * @param {number} max - Valeur maximale
 * @param {number} count - Nombre de points
 * @param {number} offset - Décalage pour créer des ondes différentes
 * @returns {Array} Tableau de valeurs
 */
function generateWaveData(min, max, count, offset) {
    const data = [];
    const range = max - min;
    
    for (let i = 0; i < count; i++) {
        // Génère une onde sinusoïdale avec un décalage
        const value = min + range * (0.5 + 0.5 * Math.sin(i / 2 + offset));
        data.push(Math.round(value * 10) / 10); // Arrondir à 1 décimale
    }
    
    return data;
}

/**
 * Initialise les interactions des boutons du calendrier
 */
function initCalendarButtons() {
    const prevBtn = document.querySelector('.btn-prev');
    const nextBtn = document.querySelector('.btn-next');
    const listBtn = document.querySelector('.btn-list');
    const calendarMonth = document.querySelector('.calendar-header h4');
    
    // Mois en français
    const months = [
        'Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin',
        'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'
    ];
    
    let currentDate = new Date(2021, 7); // Août 2021 (mois indexés à partir de 0)
    
    // Événement pour le bouton précédent
    if (prevBtn) {
        prevBtn.addEventListener('click', function() {
            currentDate.setMonth(currentDate.getMonth() - 1);
            updateCalendarHeader();
        });
    }
    
    // Événement pour le bouton suivant
    if (nextBtn) {
        nextBtn.addEventListener('click', function() {
            currentDate.setMonth(currentDate.getMonth() + 1);
            updateCalendarHeader();
        });
    }
    
    // Événement pour le bouton liste
    if (listBtn) {
        listBtn.addEventListener('click', function() {
            toggleCalendarView();
        });
    }
    
    // Mise à jour de l'en-tête du calendrier
    function updateCalendarHeader() {
        const month = months[currentDate.getMonth()];
        const year = currentDate.getFullYear();
        calendarMonth.textContent = `${month} ${year}`;
    }
    
    // Fonction pour basculer entre la vue calendrier et liste
    function toggleCalendarView() {
        const calendarView = document.querySelector('.calendar');
        
        if (calendarView.classList.contains('list-view')) {
            calendarView.classList.remove('list-view');
            listBtn.textContent = 'Liste';
        } else {
            calendarView.classList.add('list-view');
            listBtn.textContent = 'Calendrier';
        }
    }
    
    // Initialisation des dates cliquables
    initDateClicks();
}

/**
 * Initialise les interactions sur les dates du calendrier
 */
function initDateClicks() {
    const dates = document.querySelectorAll('.date');
    
    dates.forEach(date => {
        date.addEventListener('click', function() {
            // Retirer la classe active de toutes les dates
            dates.forEach(d => d.classList.remove('active'));
            
            // Ajouter la classe active à la date cliquée
            this.classList.add('active');
        });
    });
}

/**
 * Initialise les interactions pour les cartes de cours
 */
function initCourseCards() {
    const courseCards = document.querySelectorAll('.course-card');
    const moreButtons = document.querySelectorAll('.btn-more');
    
    // Effet de survol pour les cartes
    courseCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-2px)';
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
    
    // Événements pour les boutons "plus"
    moreButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.stopPropagation();
            
            // Ici, on pourrait ajouter un menu contextuel
            console.log('Menu des options ouvert');
            
            // Exemple d'alerte simple pour démonstration
            alert('Options du cours');
        });
    });
    
    // Événements pour les boutons d'ajout
    const addButtons = document.querySelectorAll('.btn-add');
    addButtons.forEach(button => {
        button.addEventListener('click', function() {
            console.log('Ajout d\'un nouvel élément');
            // Logique d'ajout ici
        });
    });
}