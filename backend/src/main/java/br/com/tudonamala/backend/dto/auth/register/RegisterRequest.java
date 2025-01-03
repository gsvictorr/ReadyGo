package br.com.tudonamala.backend.dto.auth.register;

import org.springframework.beans.BeanUtils;

import br.com.tudonamala.backend.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
                @NotBlank(message = "Nome não pode estar em branco ou ser nulo.") @Size(min = 3, message = "Nome não pode ter menos de 3 caracteres.") String name,
                @NotBlank(message = "Email não pode estar em branco ou ser nulo.") @Size(min = 10, message = "Email não pode ter menos de 10 caracteres.") @Email String email,
                @NotBlank(message = "Senha não pode estar em branco ou ser nula.") @Size(min = 8, message = "Senha não pode ter menos de 8 caracteres.") String password

) {

        public User convert(@Valid RegisterRequest registerRequest) {
                User user = new User();
                BeanUtils.copyProperties(registerRequest, user);
                return user;
        }

}
