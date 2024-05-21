package NationalCountries.services.Security;

import NationalCountries.exceptions.JwtValidationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.crypto.SecretKey;
import java.util.Date;
import java.security.Key;

@Component
public class JwtTokenProviderService {
    @Value("${app.jwt-secret-key}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-duration-milliseconds}")
    private long jwtExpirationDuration;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+jwtExpirationDuration);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();
        return token;
    }

    public String generateToken(String username, Date expireDate){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();
    }

    public boolean validateToken(String token){
        try{
            if (isTokenBlacklisted(token)) {
                throw new MalformedJwtException("Blacklisted JWT Token");
            }
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException malformedJwtException){
            throw new JwtValidationException("Invalid JWT Token", HttpStatus.BAD_REQUEST);
        }catch (ExpiredJwtException expiredJwtException){
            throw new JwtValidationException("Expired JWT token", HttpStatus.BAD_REQUEST);
        }catch (UnsupportedJwtException unsupportedJwtException){
            throw new JwtValidationException("Unsupported JWT token", HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException illegalArgumentException){
            throw new JwtValidationException("Jwt claims string is null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isTokenBlacklisted(String token) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(token) != null;
    }

    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
