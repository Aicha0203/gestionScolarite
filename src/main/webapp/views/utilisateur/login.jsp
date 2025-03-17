<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login.css">
    <!-- FontAwesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="login-page">
        <!-- Partie gauche avec image -->
        <div class="login-image">
            <img src="<%=request.getContextPath()%>/assets/images/login.png" alt="Connexion">
            <div class="overlay"></div>
            <div class="image-text">
                <h1>Gestion de la Scolarité Universitaire</h1>
                <p>Connectez-vous pour accéder à votre espace personnel</p>
            </div>
        </div>
        
        <!-- Partie droite avec formulaire -->
        <div class="login-form">
            <div class="form-container">
                <div class="form-header">
                    <h2>Connexion</h2>
                    <p>Bienvenue sur votre portail universitaire</p>
                </div>
                
                <% 
                String error = (String) session.getAttribute("error");
                if (error != null) { 
                %>
                <div class="alert alert-error">
                    <i class="fas fa-exclamation-circle"></i>
                    <span><%=error%></span>
                </div>
                <% 
                    session.removeAttribute("error");
                } 
                %>
                
                <% 
                String message = (String) session.getAttribute("message");
                if (message != null) { 
                %>
                <div class="alert alert-success">
                    <i class="fas fa-check-circle"></i>
                    <span><%=message%></span>
                </div>
                <% 
                    session.removeAttribute("message");
                } 
                %>
                
                <form id="loginForm" action="<%=request.getContextPath()%>/AuthServlet" method="post">
                    <input type="hidden" name="action" value="login">
                    
                    <div class="form-group">
                        <label for="email">Email</label>
                        <div class="input-with-icon">
                            <i class="fas fa-envelope"></i>
                            <input type="email" id="email" name="email" placeholder="votre.email@exemple.com" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Mot de passe</label>
                        <div class="input-with-icon">
                            <i class="fas fa-lock"></i>
                            <input type="password" id="password" name="motDePasse" placeholder="Entrez votre mot de passe" required>
                            <i class="fas fa-eye toggle-password" id="togglePassword"></i>
                        </div>
                    </div>
                    
                    <div class="form-options">
                        <div class="remember-me">
                            <input type="checkbox" id="remember" name="remember">
                            <label for="remember">Se souvenir de moi</label>
                        </div>
                        <a href="#" class="forgot-password">Mot de passe oublié?</a>
                    </div>
                    
                    <button type="submit" class="btn-login">
                        <i class="fas fa-sign-in-alt"></i>
                        Se connecter
                    </button>
                </form>
                
                <div class="form-footer">
                    <p>Pas encore inscrit ? <a href="inscription.jsp">Créer un compte</a></p>
                </div>
            </div>
        </div>
    </div>
    
    <script src="<%=request.getContextPath()%>/assets/js/login.js"></script>
</body>
</html>