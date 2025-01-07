package br.com.tudonamala.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tudonamala.backend.model.ListItem;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    
}
