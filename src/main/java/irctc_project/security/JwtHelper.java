package irctc_project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtHelper
{
    private static final long JWT_VALIDITY = 5 * 60 * 1000; // 5 min.

    private final String SECRET = "vbfbhavashvvbzbxnvbSdvhgfauufbsfvyybrybfhdghgfskahskhfsvvfrgfjgadssbgfvhgsfaeryhhhjfaghgs";

    private Key key;


    //It will assign after object create
    @PostConstruct
    public void init()
    {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    //👉👉 GENERATE TOKEN :

    public String generateToken(UserDetails userDetails)
    {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY))
                .signWith(key , SignatureAlgorithm.HS512)
                .compact();
    }

    //👉👉 This is like Map Structure
    //👉👉 This will return JWT Stander Claims names

    public Claims getClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    //👉👉 Get USERNAME from token

    public String getUsernameFromToken(String token)
    {
        return getClaims(token).getSubject();
    }


    //👉👉 VALIDATE TOKEN

    public boolean isTokenValid(String token , UserDetails userDetails)
    {
        String username = getUsernameFromToken(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    //👉👉 EXPIRE TOKEN ?

    private boolean isTokenExpired(String token)
    {
        return getClaims(token).getExpiration().before(new Date());
    }



    //👉👉 REFRESH TOKEN :
}
