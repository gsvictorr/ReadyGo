package br.com.tudonamala.backend.dto.auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserForgotPasswordRequest(
        @NotNull(message = "O email não pode ser nulo.") @NotBlank(message = "O email não pode estar vazio.") @Email(message = "Informe um email válido.") @Size(min = 10, max = 200, message = "O email deve conter pelo menos 10 caracteres.") String email) {
}
