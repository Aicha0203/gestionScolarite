document.addEventListener('DOMContentLoaded', function() {
    // Configuration du graphique de performance par semestre
    const ctx = document.getElementById('semesterChart').getContext('2d');
    
    const semesterChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['S1', 'S2', 'S3', 'S4'],
            datasets: [{
                label: 'Moyenne par semestre',
                data: [12.5, 13.2, 14.8, 15.1],
                backgroundColor: 'rgba(79, 70, 229, 0.1)',
                borderColor: 'rgba(79, 70, 229, 1)',
                borderWidth: 2,
                tension: 0.4,
                fill: true,
                pointBackgroundColor: 'rgba(79, 70, 229, 1)',
                pointBorderColor: '#fff',
                pointRadius: 4,
                pointHoverRadius: 6
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: false,
                    min: 8,
                    max: 20,
                    ticks: {
                        stepSize: 2
                    },
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)'
                    }
                },
                x: {
                    grid: {
                        display: false
                    }
                }
            },
            plugins: {
                legend: {
                    display: false
                },
                tooltip: {
                    backgroundColor: 'rgba(255, 255, 255, 0.9)',
                    titleColor: '#1f2937',
                    bodyColor: '#1f2937',
                    borderColor: '#e5e7eb',
                    borderWidth: 1,
                    displayColors: false,
                    callbacks: {
                        label: function(context) {
                            return context.parsed.y + '/20';
                        }
                    }
                }
            }
        }
    });

    // Gestion des boutons de filtre
    const filterButtons = document.querySelectorAll('.filter-btn');
    
    filterButtons.forEach(button => {
        button.addEventListener('click', function() {
            // Retirer la classe active de tous les boutons
            filterButtons.forEach(btn => btn.classList.remove('active'));
            
            // Ajouter la classe active sur le bouton cliqué
            this.classList.add('active');
            
            // Simuler un chargement des données
            simulateDataLoading();
        });
    });

    // Fonction pour simuler le chargement des données
    function simulateDataLoading() {
        const tableBody = document.querySelector('.results-table tbody');
        const originalContent = tableBody.innerHTML;
        
        // Afficher un message de chargement
        tableBody.innerHTML = `
            <tr>
                <td colspan="6" class="empty-results">
                    <i class="fas fa-spinner fa-spin"></i> Chargement des données...
                </td>
            </tr>
        `;
        
        // Simuler un délai de chargement
        setTimeout(() => {
            // Remettre le contenu original
            tableBody.innerHTML = originalContent;
            
            // Mettre à jour le graphique avec de nouvelles données aléatoires
            semesterChart.data.datasets[0].data = [
                Math.random() * 5 + 10, 
                Math.random() * 5 + 10, 
                Math.random() * 5 + 10, 
                Math.random() * 5 + 10
            ];
            semesterChart.update();
            
            // Mettre à jour les barres de distribution
            updateDistributionBars();
        }, 800);
    }

    // Fonction pour mettre à jour les barres de distribution de façon aléatoire
    function updateDistributionBars() {
        const distributionBars = document.querySelectorAll('.distribution-bar');
        const distributionValues = document.querySelectorAll('.distribution-value');
        
        distributionBars.forEach((bar, index) => {
            const randomValue = Math.floor(Math.random() * 30) + 5;
            bar.style.width = `${randomValue}%`;
            distributionValues[index].textContent = `${randomValue}%`;
        });
    }

    // Gestion des boutons d'action
    const actionButtons = document.querySelectorAll('.action-btn');
    
    actionButtons.forEach(button => {
        button.addEventListener('click', function() {
            const action = this.querySelector('i').classList.contains('fa-download') ? 'télécharger' : 'imprimer';
            
            // Simuler une action
            showNotification(`Action: ${action} le relevé de notes`);
        });
    });

    // Fonction pour afficher une notification
    function showNotification(message) {
        // Créer un élément de notification
        const notification = document.createElement('div');
        notification.className = 'notification-toast';
        notification.innerHTML = `
            <div class="notification-content">
                <i class="fas fa-info-circle"></i>
                <span>${message}</span>
            </div>
        `;
        
        // Ajouter le style de la notification
        notification.style.position = 'fixed';
        notification.style.bottom = '20px';
        notification.style.right = '20px';
        notification.style.backgroundColor = 'var(--primary-color)';
        notification.style.color = 'white';
        notification.style.padding = '10px 15px';
        notification.style.borderRadius = 'var(--radius-md)';
        notification.style.boxShadow = 'var(--shadow-md)';
        notification.style.zIndex = '9999';
        notification.style.display = 'flex';
        notification.style.alignItems = 'center';
        notification.style.gap = '10px';
        notification.style.transform = 'translateY(100px)';
        notification.style.opacity = '0';
        notification.style.transition = 'all 0.3s ease';
        
        // Ajouter la notification au body
        document.body.appendChild(notification);
        
        // Animer l'apparition
        setTimeout(() => {
            notification.style.transform = 'translateY(0)';
            notification.style.opacity = '1';
        }, 10);
        
        // Supprimer la notification après 3 secondes
        setTimeout(() => {
            notification.style.transform = 'translateY(100px)';
            notification.style.opacity = '0';
            
            setTimeout(() => {
                document.body.removeChild(notification);
            }, 300);
        }, 3000);
    }

    // Gérer l'interactivité des services académiques
    const serviceItems = document.querySelectorAll('.service-item');
    
    serviceItems.forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault();
            const serviceName = this.querySelector('span').textContent;
            showNotification(`Service sélectionné: ${serviceName}`);
        });
    });

    // Gestion de la notification
    const notificationIcon = document.querySelector('.notifications');
    
    notificationIcon.addEventListener('click', function() {
        showNotification('Vous avez 1 nouvelle notification');
    });

    // Gestion des options supplémentaires
    const moreOptions = document.querySelector('.more-options');
    
    moreOptions.addEventListener('click', function() {
        showNotification('Options supplémentaires');
    });

    // Initialiser le graphique avec des données aléatoires
    updateDistributionBars();
});