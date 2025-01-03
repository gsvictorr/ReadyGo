package br.com.tudonamala.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "travelList")
@Table(name = "travelList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private boolean isPublic = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "travelList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "sharedList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SharedAccess> sharedAccess = new ArrayList<>();
}
