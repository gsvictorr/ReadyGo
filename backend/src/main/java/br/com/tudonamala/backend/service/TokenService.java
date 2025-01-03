package br.com.tudonamala.backend.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.tudonamala.backend.exception.auth.LoginException;
import br.com.tudonamala.backend.model.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) throws RuntimeException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("tnm-token")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .withClaim("id", user.getId())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException jwtEx) {
            throw new LoginException("Erro ao gerar token: " + jwtEx.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("tnm-token")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException jwtvEx) {
            return null;
        }
    }

    public Long getUserFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("tnm-token")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("id").asLong();
        } catch (JWTVerificationException e) {
            throw new LoginException(e.getMessage());
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}