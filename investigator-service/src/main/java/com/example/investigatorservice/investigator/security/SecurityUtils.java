package com.example.investigatorservice.investigator.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;

public class SecurityUtils {

    private SecurityUtils() {}

    public static String getCurrentUserId() { //con este metodo puede conocer el usuario de la sesion actual, para saber que usuario es el que esta haciendo request. Toda la informacion del token se guarda en algo llamado context, con estos metodos sacamos informacion del context
        Jwt jwt = (Jwt) Objects.requireNonNull(SecurityContextHolder.getContext()
                .getAuthentication()).getPrincipal();
        assert jwt != null;
        return jwt.getSubject(); // this is the Keycloak user UUID
    }

    public static String getCurrentUserEmail() {  //revisamos el email del usuario que esta haciendo los request
        Jwt jwt = (Jwt) Objects.requireNonNull(SecurityContextHolder.getContext()
                .getAuthentication()).getPrincipal();
        assert jwt != null;
        return jwt.getClaim("email");
    }

    public static boolean hasRole(String role) {  //verificamos el role del usuario que esta haciendo los request
        return Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication())
                .getAuthorities()
                .stream()
                .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_" + role)); //asi se guardan los roles dentro del json del jwt ROLE_*
    }
}