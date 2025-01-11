package br.com.tudonamala.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tudonamala.backend.model.ListItem;
import br.com.tudonamala.backend.model.TravelList;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    
    long countByTravelList(TravelList list);
}
