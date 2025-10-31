package com.example.demo.Controller;

import com.example.demo.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final WebClient webClient = WebClient.builder().build();
    @Value("${keycloak.application.client-id}")
    private String clientId;
    @Value("${keycloak.application.client-secret}")
    private String clientSecret;

    @PostMapping("/login")
    public ResponseEntity<Mono<Map<String, Object>>> login (@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok( webClient.post()
                .uri("http://localhost:9090/realms/demo-first/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("username", loginRequestDto.getUsername())
                        .with("client_secret", clientSecret)
                        .with("client_id", clientId)
                        .with("password", loginRequestDto.getPassword()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                }));
    }
}
