package br.com.tudonamala.backend.dto.sharedAccess;

import java.time.LocalDate;

import br.com.tudonamala.backend.model.SharedAccess;
import br.com.tudonamala.backend.model.User;

public record SharedAccessResponse (Long id, User user, LocalDate createdAt) {

    public SharedAccessResponse(SharedAccess sharedAccess){
        this(sharedAccess.getId(), sharedAccess.getUser(), sharedAccess.getCreatedAt());
    }
    
}
