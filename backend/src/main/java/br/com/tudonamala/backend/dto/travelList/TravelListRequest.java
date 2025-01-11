package br.com.tudonamala.backend.dto.travelList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TravelListRequest(
       @NotNull @NotBlank(message = "O nome da lista não pode estar em branco ou ser nulo.") @Size(min = 3, max = 30, message = "O nome da lista deve ter entre 3 e 30 caracteres.") String name) {
}
