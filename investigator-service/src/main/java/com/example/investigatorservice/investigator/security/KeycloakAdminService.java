package com.example.investigatorservice.investigator.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakAdminService {

    //Traemos todos estos valosres del application.yaml

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    // traemos el token de administrador para llamar a  Keycloak Admin API
    private String getAdminToken() {
        String url = serverUrl + "/realms/master/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "admin-cli");
        body.add("username", "admin");
        body.add("password", "admin");
        body.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return (String) response.getBody().get("access_token");
    }

    // Vamos a crear el usuario desde keycloak, los usuarios van a ser los investigadores ya que ello usaran este metodo
    public String createUser(String email, String password, String name, String role) {
        String adminToken = getAdminToken();
        String url = serverUrl + "/admin/realms/" + realm + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminToken);

        Map<String, Object> userBody = Map.of(
                "username", email,
                "email", email,
                "firstName", name,
                "enabled", true,
                "credentials", List.of(Map.of(
                        "type", "password",
                        "value", password,
                        "temporary", false
                ))
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userBody, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

        // Keycloak returns the new user's URL in the Location header
        // http://localhost:8881/admin/realms/murdercase/users/some-uuid
        String location = response.getHeaders().getLocation().toString();
        String keycloakId = location.substring(location.lastIndexOf("/") + 1);

        // Step 3 — assign the role to the user
        assignRole(keycloakId, role, adminToken);

        return keycloakId;
    }

    private void assignRole(String keycloakUserId, String roleName, String adminToken) {
        // First get the role representation from Keycloak
        String roleUrl = serverUrl + "/admin/realms/" + realm + "/roles/" + roleName;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);

        ResponseEntity<Map> roleResponse = restTemplate.exchange(
                roleUrl, HttpMethod.GET, new HttpEntity<>(headers), Map.class);
        Map<String, Object> role = roleResponse.getBody();

        // Then assign it to the user
        String assignUrl = serverUrl + "/admin/realms/" + realm +
                "/users/" + keycloakUserId + "/role-mappings/realm";
        HttpEntity<List<Map<String, Object>>> assignRequest =
                new HttpEntity<>(List.of(role), headers);
        restTemplate.postForEntity(assignUrl, assignRequest, Void.class);
    }
}