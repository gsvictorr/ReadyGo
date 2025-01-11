package br.com.tudonamala.backend.dto.travelList;

import java.time.LocalDate;

import br.com.tudonamala.backend.enums.ListPlan;
import br.com.tudonamala.backend.model.TravelList;

public record TravelListResponse (Long id, String name, ListPlan plan, LocalDate createdAt) {
    
    public TravelListResponse(TravelList travelList){
        this(travelList.getId(), travelList.getName(), travelList.getPlan(), travelList.getCreatedAt());
    }
}
