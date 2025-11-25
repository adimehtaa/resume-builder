package cloud.devyard.rbapi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(String userId){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(expiryDate)
                .setIssuedAt(now)
                .signWith(getSignKey())
                .compact();

        return token;
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String getUserIdFromToken(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build()
                    .parseClaimsJws(token);
            return  true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean iSTokenExpired(String token)
    {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build()
                    .parseClaimsJws(token).getBody();

            return claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

}
