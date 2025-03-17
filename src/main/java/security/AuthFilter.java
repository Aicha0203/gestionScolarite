package security;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

		String requestURI = httpRequest.getRequestURI();
		boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);
		String role = (session != null) ? (String) session.getAttribute("role") : null;

		System.out.println("🔍 Vérification AuthFilter pour : " + requestURI);

		if (requestURI.contains("/assets/") || requestURI.contains("/AuthServlet") ||
				requestURI.contains("/views/utilisateur/login.jsp") || requestURI.contains("/views/utilisateur/inscription.jsp")) {
			System.out.println("✅ Accès libre : " + requestURI);
			chain.doFilter(request, response);
			return;
		}

		if (!isLoggedIn) {
			System.out.println("🚨 Utilisateur non connecté ! Redirection vers login.jsp");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/utilisateur/login.jsp?error=Accès non autorisé !");
			return;
		}

		if (requestURI.contains("/views/etudiant/") && !"ETUDIANT".equals(role) ||
				requestURI.contains("/views/admin/") && !"ADMIN".equals(role) ||
				requestURI.contains("/views/enseignant/") && !"ENSEIGNANT".equals(role)) {
			System.out.println("⛔ Accès refusé : rôle non autorisé pour cette ressource !");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/utilisateur/login.jsp?error=Accès interdit !");
			return;
		}

		System.out.println("✅ Accès autorisé pour : " + requestURI);
		chain.doFilter(request, response);
	}


	@Override
	public void destroy() {
	}
}
