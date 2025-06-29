package com.interview.service.config;

import com.interview.service.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;
@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {
    @Autowired
    JWTUtil jwtUtil;
    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String token = servletRequest.getParameter("token");
//            System.out.println("Intercepted Token: " + token);

            if (token != null && jwtUtil.validateToken(token)) {
                Claims body = jwtUtil.extractToken(token);
                attributes.put("username", body.getSubject());
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority((String) body.get("role")));
                attributes.put("username", body.getSubject());
                attributes.put("authorities", authorities);
                return true;
            }
        }

        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }


}
