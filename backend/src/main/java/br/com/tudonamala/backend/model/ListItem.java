package br.com.tudonamala.backend.model;

import br.com.tudonamala.backend.enums.CategoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "listItem")
@Table(name = "listItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 
    private CategoryType category;
    private boolean isCompleted = false;

    @ManyToOne
    @JoinColumn(name = "travel_list_id", nullable = false)
    private TravelList travelList;
}
