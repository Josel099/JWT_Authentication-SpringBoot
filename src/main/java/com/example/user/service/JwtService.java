package com.example.user.service;

import com.example.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // Define a constant variable for the secret key used to sign the JWT
    private final String SECRET_KEY = "7818599feb1541c4c28d9f5883945c50c14995a2b49ccffa14705fc3f841b014";

    /*
     * Extracts the user name from a JWT token
     * @param token The JWT token
     * @return The extracted user name
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /*
     * Checks if a JWT token is valid for the given user
     * @param token The JWT token
     * @param user The UserDetails object representing the user
     * @return True if the token is valid for the user, otherwise false
     */

    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }


    /*
     * Checks if a JWT token is expired
     * @param token The JWT token
     * @return True if the token is expired, otherwise false
     */

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /*
     * Extracts the expiration date from a JWT token
     * @param token The JWT token
     * @return The expiration date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /*
     * Extracts a specific claim from a JWT token using a resolver function
     * @param token The JWT token
     * @param resolver The resolver function for extracting the claim
     * @param <T> The type of the claim
     * @return The extracted claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    /*
     * Extracts all claims from a JWT token
     * @param token The JWT token
     * @return The extracted claims
     */
    private final Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token)
            .getPayload();
    }

    /*
     * Generates a JWT token for the provided user
     * @param user The User object for which the token is generated
     * @return The generated JWT token
     */
    public String generateToken(User user) {
        // Build a JWT token using the builder pattern
        String token = Jwts.builder()
            .subject(user.getUsername()) // Set the subject of the JWT to the username of the user
            .issuedAt(new Date(
                System.currentTimeMillis())) // Set the issued at time of the JWT to the current time
            .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60
                * 1000))  // Set the expiration time of the JWT to 24 hours from the current time
            .signWith(getSigningKey()) // Sign the JWT with the signing key
            .compact(); // Compact the JWT into a string format
        return token;
    }

    /*Gets the signing key used to sign the JWT
     * @return The signing key
     */
    private SecretKey getSigningKey() {
        // Decode the secret key from Base64 URL encoding
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        // Generate a SecretKey object using HMAC SHA algorithm
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
