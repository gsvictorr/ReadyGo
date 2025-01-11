package br.com.tudonamala.backend.dto.listItem;

public record ListItemCompleteRequest (Long id, Long listId, boolean complete) {
    
}
