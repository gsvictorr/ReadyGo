package br.com.tudonamala.backend.dto.listItem;

import br.com.tudonamala.backend.enums.CategoryType;
import br.com.tudonamala.backend.model.ListItem;

public record ListItemResponse(Long id, String name, CategoryType categoryType, boolean isCompleted, int quantity) {

    public ListItemResponse(ListItem listItem){
        this(listItem.getId(), listItem.getName(), listItem.getCategory(), listItem.isCompleted(), listItem.getQuantity());
    }

}
