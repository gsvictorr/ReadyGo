package br.com.tudonamala.backend.dto.listItem;

import br.com.tudonamala.backend.enums.CategoryType;

public record ListItemUpdateRequest (Long listId, Long id, String name, CategoryType categoryType, int quantity) {
    
}
