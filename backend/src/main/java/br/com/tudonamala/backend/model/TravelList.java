package br.com.tudonamala.backend.model;

import java.time.LocalDateTime;
import java.util.List;

import br.com.tudonamala.backend.enums.ListPlan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private ListPlan plan; // Define o plano da lista: FREE ou PREMIUM.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Dono da lista.

    @OneToMany(mappedBy = "travelList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListItem> items; // Itens da lista.

    @OneToMany(mappedBy = "sharedList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SharedAccess> sharedAccess; // Usuários com quem a lista foi compartilhada.

    private LocalDateTime createdAt;
}
