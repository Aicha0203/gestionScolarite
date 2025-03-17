package security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {
	private static final String SECRET_KEY = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
	private static final long EXPIRATION_TIME = 86400000;

	private static Key getSigningKey() {
		return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
	}

	public static String generateToken(String email, String role) {
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public static Claims validateToken(String token) {
		JwtParser parser = Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build();

		return parser.parseClaimsJws(token).getBody();
	}

	public static String getEmailFromToken(String token) {
		return validateToken(token).getSubject();
	}

	public static String getRoleFromToken(String token) {
		return validateToken(token).get("role", String.class);
	}
}
