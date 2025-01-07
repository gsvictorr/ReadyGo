package br.com.tudonamala.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tudonamala.backend.model.TravelList;
import br.com.tudonamala.backend.model.User;

import java.util.List;


public interface TravelListRepository  extends JpaRepository<TravelList, Long>{

    List<TravelList> findByOwner(User owner);
    
}
