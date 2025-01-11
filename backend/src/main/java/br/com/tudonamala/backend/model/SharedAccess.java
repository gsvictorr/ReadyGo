package br.com.tudonamala.backend.model;

import java.time.LocalDate;

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

@Entity(name = "sharedAccess")
@Table(name = "sharedAccess")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedAccess {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;

    @ManyToOne
    @JoinColumn(name = "travel_list_id")
    private TravelList sharedList;

    private LocalDate createdAt;
}
