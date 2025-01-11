package br.com.tudonamala.backend.dto.travelList;

import java.time.LocalDate;
import java.util.List;

import br.com.tudonamala.backend.enums.ListPlan;
import br.com.tudonamala.backend.model.ListItem;
import br.com.tudonamala.backend.model.TravelList;

public record TravelListCompleteResponse (Long id, String name, ListPlan plan, LocalDate createdAt, List<ListItem> items) {
    
    public TravelListCompleteResponse(TravelList travelList){
        this(travelList.getId(), travelList.getName(), travelList.getPlan(), travelList.getCreatedAt(), travelList.getItems());
    }
}