package br.com.tudonamala.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.repository.ListItemRepository;

@Service
public class ListItemService {

    @Autowired
    private ListItemRepository listItemRepository;
    
}
