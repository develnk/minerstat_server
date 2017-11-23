package net.minerstat.miner.security;

import net.minerstat.miner.model.security.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

  private final Logger logger = Logger.getLogger(this.getClass());

  @Value("${net.minerstat.miner.token.secret}")
  private String secret;

  @Value("${net.minerstat.miner.token.expiration}")
  private Long expiration;

  @Value("${net.minerstat.miner.token.header}")
  private String AUTH_HEADER;

  public String getUsernameFromToken(String token) {
    String username;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  private Date getCreatedDateFromToken(String token) {
    Date created;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      created = new Date((Long) claims.get("created"));
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  private Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  public String getAudienceFromToken(String token) {
    String audience;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      audience = (String) claims.get("audience");
    } catch (Exception e) {
      audience = null;
    }
    return audience;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
        .setSigningKey(this.secret)
        .parseClaimsJws(token)
        .getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  private Date generateCurrentDate() {
    return new Date(System.currentTimeMillis());
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.expiration * 1000);
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = this.getExpirationDateFromToken(token);
    return expiration.before(this.generateCurrentDate());
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("sub", userDetails.getUsername());
    claims.put("created", this.generateCurrentDate());
    return this.generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(this.generateCurrentDate())
      .setExpiration(this.generateExpirationDate())
      .signWith(SignatureAlgorithm.HS512, this.secret)
      .compact();
  }

  public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    final Date created = this.getCreatedDateFromToken(token);
    return (!this.isTokenExpired(token));
  }

  public String refreshToken(String token) {
    String refreshedToken;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      claims.put("created", this.generateCurrentDate());
      refreshedToken = this.generateToken(claims);
    } catch (Exception e) {
      refreshedToken = null;
    }
    return refreshedToken;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    SecurityUser user = (SecurityUser) userDetails;
    final String username = this.getUsernameFromToken(token);
    final Date created = this.getCreatedDateFromToken(token);
    final Date expiration = this.getExpirationDateFromToken(token);
    return (username.equals(user.getUsername()) && !this.isTokenExpired(token));
  }

  public String getToken( HttpServletRequest request ) {
    /**
     *  Getting the token from Authentication header
     *  e.g Bearer your_token
     */
    String authHeader = getAuthHeaderFromHeader( request );
    if ( authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(7);
    }

    return null;
  }

  public String getAuthHeaderFromHeader( HttpServletRequest request ) {
    return request.getHeader(AUTH_HEADER);
  }

}
