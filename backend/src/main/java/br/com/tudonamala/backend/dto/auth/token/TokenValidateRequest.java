package br.com.tudonamala.backend.dto.auth.token;

import jakarta.validation.constraints.NotBlank;

public record TokenValidateRequest (@NotBlank String token) {
    
}
