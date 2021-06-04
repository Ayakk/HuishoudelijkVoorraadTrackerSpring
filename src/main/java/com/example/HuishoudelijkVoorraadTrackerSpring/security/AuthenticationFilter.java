package com.example.HuishoudelijkVoorraadTrackerSpring.security;
import com.example.HuishoudelijkVoorraadTrackerSpring.controller.AuthenticationResource;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private static final Logger LOG = LogManager.getLogger(AuthenticationFilter.class);
    @Override
    public void filter(ContainerRequestContext requestCtx) {

        String scheme = requestCtx.getUriInfo().getRequestUri().getScheme();
        // Users are treated as guests, unless a valid JWT is provided
        MySecurityContext msc = new MySecurityContext(null, scheme);
        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring("Bearer".length()).trim();

            try {
                // Validate the token
                JwtParser parser = Jwts.parser().setSigningKey(AuthenticationResource.key);
                Claims claims = parser.parseClaimsJws(token).getBody();

                String user = claims.getSubject();
                msc = new MySecurityContext(SecurityManager.getInstance().getUserByName(user), scheme);

            } catch (JwtException | IllegalArgumentException e) {
                LOG.log(Level.INFO, "Login failed, processing as guest");
            }
        }

        requestCtx.setSecurityContext(msc);
    }
}