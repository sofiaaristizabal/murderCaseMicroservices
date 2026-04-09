package com.example.investigatorservice.investigator.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // allows @PreAuthorize on controllers
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disabled because we use JWT, not sessions (para evitar cross site request forgery )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //esto dice que cada seson debe venir con su propio token para que el servidor no tenga que recordar una sesion
                .authorizeHttpRequests(auth -> auth //aqui vamos a definir las reglas para las http request
                        // public endpoints — none for now, everything requires login
                        // DELETE solo permitido para usuarios con role de ADMIN (el role tiene que ser un string)
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**",
                                        "/v3/api-docs.yaml"
                                ).permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/api/cases/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/evidences/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/people/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/tasks/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/investigators/**").hasRole("ADMIN")
                        //aqui estamos diciendo que cualquier Http request a un endpoint necesita autorizacion
                        //.anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())) // aqui validamos tookens antes de que el request tan siquiera llegue al controlador. estamos diciendo "this app is a Resource Server — it accepts JWT tokens to authenticate requests."
                );

        return http.build();
    }

    // This converter extracts roles from the Keycloak token structure
    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Keycloak puts roles inside "realm_access.roles" in the token
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            if (realmAccess == null || !realmAccess.containsKey("roles")) {
                return List.of();
            }
            List<String> roles = (List<String>) realmAccess.get("roles"); //aqui extraemos como tal el role del token
            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        });
        return converter;
    }


}
