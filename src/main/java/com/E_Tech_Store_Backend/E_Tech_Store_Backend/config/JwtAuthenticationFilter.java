package com.E_Tech_Store_Backend.E_Tech_Store_Backend.config;

import com.E_Tech_Store_Backend.E_Tech_Store_Backend.service.JwtService;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.token.TokenRepository;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.token.Token;
import com.E_Tech_Store_Backend.E_Tech_Store_Backend.token.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override

    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        return request.getServletPath().startsWith("/api/v1/auth");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        String userEmail = null;
        String newJwt = null;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);

        try {
            userEmail = jwtService.extractUsername(jwt);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            Optional<Token> storedToken = tokenRepository.findByToken(jwt);
            if (storedToken.isPresent() && !storedToken.get().isExpired() && !storedToken.get().isRevoked()) {
                try {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtService.extractUsername(jwt));
                    newJwt = jwtService.generateToken(userDetails);

                    storedToken.get().setExpired(true);
                    storedToken.get().setRevoked(true);
                    tokenRepository.save(storedToken.get());

                    // Save new token
                    Token newTokenEntity = Token.builder()
                            .user(storedToken.get().getUser())
                            .token(newJwt)
                            .tokenType(TokenType.BEARER)
                            .expired(false)
                            .revoked(false)
                            .build();
                    tokenRepository.save(newTokenEntity);

                    request.setAttribute("newJwt", newJwt);
                    userEmail = jwtService.extractUsername(newJwt);
                } catch (UsernameNotFoundException | io.jsonwebtoken.ExpiredJwtException refreshException) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                String tokenToValidate = (newJwt != null) ? newJwt : jwt;

                var isTokenValid = tokenRepository.findByToken(tokenToValidate)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false);

                if (jwtService.isTokenValid(tokenToValidate, userDetails) && isTokenValid) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (UsernameNotFoundException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}