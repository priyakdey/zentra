package com.priyakdey.com.zentra.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.priyakdey.com.zentra.exception.InvalidCredentialsException;
import com.priyakdey.com.zentra.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * @author Priyak Dey
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final Set<String> WHITELIST_PATHS = Set.of("/v1/signup", "/v1/login");

    private final TokenService tokenService;

    public AuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (WHITELIST_PATHS.contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new InvalidCredentialsException();
        }

        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        try {
            DecodedJWT jwt = tokenService.verifyToken(token);
            String accountId = jwt.getSubject();
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(accountId, null, Set.of());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            throw new InvalidCredentialsException();
        }
    }
}
