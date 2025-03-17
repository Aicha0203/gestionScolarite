document.addEventListener('DOMContentLoaded', function() {
    // Gestion de la soumission du formulaire
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', function(event) {
            console.log('Le formulaire est soumis !');
            
            // Validation des champs requis
            const nom = document.getElementById('nom').value;
            const prenom = document.getElementById('prenom').value;
            const email = document.getElementById('email').value;
            const motDePasse = document.getElementById('motDePasse').value;
            const terms = document.getElementById('terms').checked;
            
            if (!nom || !prenom || !email || !motDePasse) {
                event.preventDefault();
                showAlert('Veuillez remplir tous les champs obligatoires', 'error');
                return;
            }
            
            if (!terms) {
                event.preventDefault();
                showAlert('Vous devez accepter les conditions d\'utilisation', 'error');
                return;
            }
            
            // Validation de l'email
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                event.preventDefault();
                showAlert('Veuillez entrer une adresse email valide', 'error');
                return;
            }
            
            // Validation du mot de passe (minimum 6 caractères)
            if (motDePasse.length < 6) {
                event.preventDefault();
                showAlert('Le mot de passe doit contenir au moins 6 caractères', 'error');
                return;
            }
        });
    }
    
    // Gestion du bouton "voir le mot de passe"
    const togglePassword = document.getElementById('togglePassword');
    if (togglePassword) {
        togglePassword.addEventListener('click', function() {
            const passwordInput = document.getElementById('motDePasse');
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
    
    // Vérificateur de force du mot de passe
    const passwordInput = document.getElementById('motDePasse');
    if (passwordInput) {
        passwordInput.addEventListener('input', function() {
            checkPasswordStrength(this.value);
        });
    }
    
    function checkPasswordStrength(password) {
        const strengthBar = document.querySelector('.strength-bar').parentElement;
        const strengthText = document.querySelector('.strength-text');
        
        // Réinitialiser les classes
        strengthBar.classList.remove('strength-weak', 'strength-medium', 'strength-strong');
        
        // Évaluer la force du mot de passe
        if (password.length === 0) {
            strengthText.textContent = 'Force du mot de passe';
            return;
        }
        
        let strength = 0;
        
        // Au moins 6 caractères
        if (password.length >= 6) strength += 1;
        
        // Au moins 10 caractères
        if (password.length >= 10) strength += 1;
        
        // Contient des minuscules et des majuscules
        if (/[a-z]/.test(password) && /[A-Z]/.test(password)) strength += 1;
        
        // Contient des chiffres
        if (/\d/.test(password)) strength += 1;
        
        // Contient des caractères spéciaux
        if (/[^a-zA-Z0-9]/.test(password)) strength += 1;
        
        // Afficher la force du mot de passe
        if (strength <= 2) {
            strengthBar.classList.add('strength-weak');
            strengthText.textContent = 'Faible';
        } else if (strength <= 4) {
            strengthBar.classList.add('strength-medium');
            strengthText.textContent = 'Moyen';
        } else {
            strengthBar.classList.add('strength-strong');
            strengthText.textContent = 'Fort';
        }
    }
    
    // Fonction pour afficher des alertes
    function showAlert(message, type) {
        // Supprimer les alertes existantes
        const existingAlerts = document.querySelectorAll('.alert');
        existingAlerts.forEach(alert => alert.remove());
        
        // Créer une nouvelle alerte
        const alertContainer = document.createElement('div');
        alertContainer.className = `alert alert-${type}`;
        
        const icon = document.createElement('i');
        icon.className = type === 'error' ? 'fas fa-exclamation-circle' : 'fas fa-check-circle';
        
        const span = document.createElement('span');
        span.textContent = message;
        
        alertContainer.appendChild(icon);
        alertContainer.appendChild(span);
        
        // Insérer l'alerte avant le formulaire
        const form = document.getElementById('registerForm');
        form.parentNode.insertBefore(alertContainer, form);
        
        // Faire défiler jusqu'à l'alerte
        alertContainer.scrollIntoView({ behavior: 'smooth' });
        
        // Supprimer l'alerte après 5 secondes
        setTimeout(() => {
            alertContainer.style.opacity = '0';
            alertContainer.style.transition = 'opacity 0.5s ease';
            setTimeout(() => {
                alertContainer.remove();
            }, 500);
        }, 5000);
    }
    
    // Animation de transition pour les champs
    document.querySelectorAll('input, select').forEach(input => {
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
    
    // Vérifier si le message d'erreur est présent
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