document.addEventListener('DOMContentLoaded', function() {
    // Gestion de la soumission du formulaire
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            console.log('Le formulaire est soumis !');
            
            // Vous pouvez ajouter ici des validations supplémentaires
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            
            if (!email || !password) {
                event.preventDefault();
                showAlert('Veuillez remplir tous les champs', 'error');
            }
        });
    }
    
    // Gestion du bouton "voir le mot de passe"
    const togglePassword = document.getElementById('togglePassword');
    if (togglePassword) {
        togglePassword.addEventListener('click', function() {
            const passwordInput = document.getElementById('password');
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                togglePassword.classList.remove('fa-eye');
                togglePassword.classList.add('fa-eye-slash');
            } else {
                passwordInput.type = 'password';
                togglePassword.classList.remove('fa-eye-slash');
                togglePassword.classList.add('fa-eye');
            }
        });
    }
    
    // Fonction pour afficher des alertes
    function showAlert(message, type) {
        const alertContainer = document.createElement('div');
        alertContainer.className = `alert alert-${type}`;
        
        const icon = document.createElement('i');
        icon.className = type === 'error' ? 'fas fa-exclamation-circle' : 'fas fa-check-circle';
        
        const span = document.createElement('span');
        span.textContent = message;
        
        alertContainer.appendChild(icon);
        alertContainer.appendChild(span);
        
        // Insérer l'alerte avant le formulaire
        const form = document.getElementById('loginForm');
        form.parentNode.insertBefore(alertContainer, form);
        
        // Supprimer l'alerte après 3 secondes
        setTimeout(() => {
            alertContainer.remove();
        }, 3000);
    }
    
    // Animation de transition
    document.querySelectorAll('input').forEach(input => {
        input.addEventListener('focus', function() {
            this.parentElement.classList.add('input-focused');
        });
        
        input.addEventListener('blur', function() {
            if (!this.value) {
                this.parentElement.classList.remove('input-focused');
            }
        });
    });
    
    // Animation d'entrée lors du chargement de la page
    function fadeIn() {
        const formContainer = document.querySelector('.form-container');
        const imageText = document.querySelector('.image-text');
        
        if (formContainer) {
            formContainer.style.opacity = '0';
            formContainer.style.transform = 'translateY(20px)';
            formContainer.style.transition = 'opacity 0.8s ease, transform 0.8s ease';
            
            setTimeout(() => {
                formContainer.style.opacity = '1';
                formContainer.style.transform = 'translateY(0)';
            }, 300);
        }
        
        if (imageText) {
            imageText.style.opacity = '0';
            imageText.style.transform = 'translateY(20px)';
            imageText.style.transition = 'opacity 0.8s ease, transform 0.8s ease';
            
            setTimeout(() => {
                imageText.style.opacity = '1';
                imageText.style.transform = 'translateY(0)';
            }, 600);
        }
    }
    
    // Lancer l'animation d'entrée
    fadeIn();
    
    // Vérifier si le message d'erreur ou de succès est présent
    const alertMessages = document.querySelectorAll('.alert');
    if (alertMessages.length > 0) {
        // Faire disparaître les messages après 5 secondes
        alertMessages.forEach(alert => {
            setTimeout(() => {
                alert.style.opacity = '0';
                alert.style.transition = 'opacity 0.5s ease';
                setTimeout(() => {
                    alert.remove();
                }, 500);
            }, 5000);
        });
    }
});