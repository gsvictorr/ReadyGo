
package br.com.tudonamala.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tudonamala.backend.model.SharedAccess;
import br.com.tudonamala.backend.model.TravelList;
import br.com.tudonamala.backend.model.User;

public interface SharedAccessRepository extends JpaRepository<SharedAccess, Long> {

    List<SharedAccess> findBySharedList(TravelList travelList);

    List<SharedAccess> findByUser(User user);

    Optional<SharedAccess> findBySharedListAndUser(TravelList travelList, User user);

    boolean existsBySharedListAndUser(TravelList travelList, User user);
}
