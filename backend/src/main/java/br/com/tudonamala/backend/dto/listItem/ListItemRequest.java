package br.com.tudonamala.backend.dto.listItem;

import br.com.tudonamala.backend.enums.CategoryType;

public record ListItemRequest (Long listId, String name, CategoryType categoryType, int quantity) {
    
}
