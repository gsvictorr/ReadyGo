package br.com.tudonamala.backend.dto.auth.token;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.tudonamala.backend.config.security.UserRole;

public record TokenValidateResponse (@JsonProperty("token_valid") boolean tokenValid, UserRole userRole){
    
}
