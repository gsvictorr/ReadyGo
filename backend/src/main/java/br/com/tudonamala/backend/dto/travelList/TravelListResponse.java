package br.com.tudonamala.backend.dto.travelList;

import java.time.LocalDateTime;
import java.util.List;

import br.com.tudonamala.backend.enums.ListPlan;
import br.com.tudonamala.backend.model.ListItem;
import br.com.tudonamala.backend.model.SharedAccess;
import br.com.tudonamala.backend.model.TravelList;
import br.com.tudonamala.backend.model.User;

public record TravelListResponse (Long id, String name, ListPlan plan, User owner, List<ListItem> items, List<SharedAccess> sharedAccess, LocalDateTime createdAt) {
    
    public TravelListResponse(TravelList travelList){
        this(travelList.getId(), travelList.getName(), travelList.getPlan(), travelList.getOwner(), 
        travelList.getItems(), travelList.getSharedAccess(), travelList.getCreatedAt());
    }
}
