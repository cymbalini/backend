package com.app.backend.config.fiter;


import com.app.backend.service.JWTService;
import com.app.backend.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String authHeader = request.getHeader("Authorization");
            System.out.println(authHeader);
            String token = null;
            String username = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }
            System.out.println(username);
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

                if(jwtService.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (io.jsonwebtoken.ExpiredJwtException ex) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Token expired");
        return;

        } catch (io.jsonwebtoken.security.SignatureException ex) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid token signature");
        return;

        } catch (io.jsonwebtoken.JwtException ex) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid JWT token");
        return;

        } catch (Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("Internal authentication error");
        return;
        }
        }
}
