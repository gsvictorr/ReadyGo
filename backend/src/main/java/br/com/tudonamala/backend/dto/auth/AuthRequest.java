package br.com.tudonamala.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Email não pode estar em branco ou ser nulo.") @Size(min = 10, message = "Email não pode ter menos de 10 caracteres") @Email String email,
        @NotBlank(message = "Senha não pode estar em branco ou ser nula.") @Size(min = 8, message = "Senha não pode ter menos de 8 caracteres.") String password) {

}