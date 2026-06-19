package com.ecommerce.backend.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import java.util.Collection;
import java.util.HashSet;

public class FirebaseJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter defaultAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private final String adminEmail;

    public FirebaseJwtAuthenticationConverter(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new HashSet<>(defaultAuthoritiesConverter.convert(jwt));

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Boolean isAdminClaim = jwt.getClaimAsBoolean("admin");
        if (isAdminClaim == null) {
            isAdminClaim = jwt.getClaimAsBoolean("is_admin");
        }

        String roleClaim = jwt.getClaimAsString("role");
        String email = jwt.getClaimAsString("email");

        if ((isAdminClaim != null && isAdminClaim) || 
            "admin".equalsIgnoreCase(roleClaim) || 
            (email != null && adminEmail != null && email.equalsIgnoreCase(adminEmail))) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
    }
}
