<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inscription</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/inscription.css">
<!-- FontAwesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<div class="register-page">
		<!-- Partie gauche avec image -->
		<div class="register-image">
			<img src="<%=request.getContextPath()%>/assets/images/login.png"
				alt="Inscription">
			<div class="overlay"></div>
			<div class="image-text">
				<h1>Rejoignez notre plateforme universitaire</h1>
				<p>Créez votre compte pour accéder à tous nos services</p>
			</div>
		</div>

		<div class="register-form">
			<div class="form-container">
				<div class="form-header">
					<h2>Inscription</h2>
					<p>Complétez le formulaire pour créer votre compte</p>
				</div>

				<form id="registerForm"
					action="<%= request.getContextPath() %>/AuthServlet" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="action" value="register">

					<div class="form-row">
						<div class="form-group">
							<label for="nom">Nom</label>
							<div class="input-with-icon">
								<i class="fas fa-user"></i> <input type="text" id="nom"
									name="nom" placeholder="Votre nom" required>
							</div>
						</div>

						<div class="form-group">
							<label for="prenom">Prénom</label>
							<div class="input-with-icon">
								<i class="fas fa-user"></i> <input type="text" id="prenom"
									name="prenom" placeholder="Votre prénom" required>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="email">Email</label>
						<div class="input-with-icon">
							<i class="fas fa-envelope"></i> <input type="email" id="email"
								name="email" placeholder="votre.email@exemple.com" required>
						</div>
					</div>

					<div class="form-group">
						<label for="motDePasse">Mot de passe</label>
						<div class="input-with-icon">
							<i class="fas fa-lock"></i> <input type="password"
								id="motDePasse" name="motDePasse"
								placeholder="Créez un mot de passe sécurisé" required> <i
								class="fas fa-eye toggle-password" id="togglePassword"></i>
						</div>
					</div>

					<div class="form-group">
						<label for="photo">Photo de profil</label>
						<div class="input-with-icon">
							<i class="fas fa-image"></i> <input type="file" id="photo"
								name="photo" accept="images/*">
						</div>
					</div>

					<div class="form-group">
						<label for="role">Rôle</label>
						<div class="input-with-icon">
							<i class="fas fa-user-tag"></i> <select id="role" name="role"
								onchange="toggleAdditionalFields()">
								<option value="ETUDIANT">Étudiant</option>
								<option value="ENSEIGNANT">Enseignant</option>
							</select>
						</div>
					</div>

					<!-- 🔹 Champs spécifiques aux étudiants -->
					<div id="studentFields">
						<div class="form-group">
							<label for="matricule">Matricule</label>
							<div class="input-with-icon">
								<i class="fas fa-id-badge"></i> <input type="text"
									id="matricule" name="matricule"
									placeholder="Matricule étudiant">
							</div>
						</div>
						<div class="form-group">
							<label for="adresse">Adresse</label>
							<div class="input-with-icon">
								<i class="fas fa-map-marker-alt"></i> <input type="text"
									id="adresse" name="adresse" placeholder="Adresse complète">
							</div>
						</div>
					</div>

					<!-- 🔹 Champs spécifiques aux enseignants -->
					<div id="teacherFields" style="display: none;">
						<div class="form-group">
							<label for="specialite">Spécialité</label>
							<div class="input-with-icon">
								<i class="fas fa-chalkboard-teacher"></i> <input type="text"
									id="specialite" name="specialite" placeholder="Spécialité">
							</div>
						</div>
					</div>

					<div class="form-terms">
						<input type="checkbox" id="terms" name="terms" required> <label
							for="terms">J'accepte les <a href="#">conditions
								d'utilisation</a> et la <a href="#">politique de confidentialité</a></label>
					</div>

					<button type="submit" class="btn-register">
						<i class="fas fa-user-plus"></i> S'inscrire
					</button>
				</form>

				<div class="form-footer">
					<p>
						Déjà un compte ? <a href="login.jsp">Se connecter</a>
					</p>
				</div>
			</div>
		</div>
	</div>

	<script>
        function toggleAdditionalFields() {
            let role = document.getElementById("role").value;
            let studentFields = document.getElementById("studentFields");
            let teacherFields = document.getElementById("teacherFields");

            if (role === "ETUDIANT") {
                studentFields.style.display = "block";
                teacherFields.style.display = "none";
            } else {
                studentFields.style.display = "none";
                teacherFields.style.display = "block";
            }
        }
    </script>
</body>
</html>
