package br.com.tudonamala.backend.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.tudonamala.backend.exception.auth.LoginException;
import br.com.tudonamala.backend.exception.auth.TokenInvalidException;
import br.com.tudonamala.backend.repository.UserRepository;
import br.com.tudonamala.backend.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = recoverToken(request);

            if (token != null) {
                String email = tokenService.validateToken(token);

                if (email != null) {
                    UserDetails user = userRepository.findByEmail(email);

                    if (user != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        throw new LoginException("Usuário não encontrado.");
                    }
                } else {
                    throw new TokenInvalidException("Token de acesso fornecido é inválido.");
                }
            }

            filterChain.doFilter(request, response);
        } catch (LoginException | TokenInvalidException ex) {
            throw new ServletException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ServletException("Erro durante o filtro de segurança.", ex);
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        } else {
            return authHeader.replace("Bearer ", "");
        }
    }
}
