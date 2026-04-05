package com.user.service.filter;

import com.user.service.service.CustomUserDetailService;
import com.user.service.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    public JWTFilter(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String token=null;
        String username=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            username= jwtUtil.extractUsername(token);

        }
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
            if (jwtUtil.validate(username, userDetails.getUsername(),token)) {
                    UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
