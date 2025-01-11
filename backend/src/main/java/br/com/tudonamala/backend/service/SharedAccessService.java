package br.com.tudonamala.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.dto.sharedAccess.SharedAccessRemove;
import br.com.tudonamala.backend.dto.sharedAccess.SharedAccessRequest;
import br.com.tudonamala.backend.dto.sharedAccess.SharedAccessResponse;
import br.com.tudonamala.backend.exception.sharedAccess.SharedAccessException;
import br.com.tudonamala.backend.exception.sharedAccess.SharedAccessNotFoundException;
import br.com.tudonamala.backend.exception.sharedAccess.SharedAccessUnauthorized;
import br.com.tudonamala.backend.exception.travelList.TravelListNotFoundException;
import br.com.tudonamala.backend.exception.user.UserNotFoundException;
import br.com.tudonamala.backend.model.SharedAccess;
import br.com.tudonamala.backend.model.TravelList;
import br.com.tudonamala.backend.model.User;
import br.com.tudonamala.backend.repository.SharedAccessRepository;
import br.com.tudonamala.backend.repository.TravelListRepository;
import br.com.tudonamala.backend.repository.UserRepository;

@Service
public class SharedAccessService {

        @Autowired
        private SharedAccessRepository sharedAccessRepository;

        @Autowired
        private TravelListRepository travelListRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private TokenService tokenService;

        public void shareAccess(SharedAccessRequest sharedAccessRequest, String token) {

                TravelList travelList = travelListRepository.findById(sharedAccessRequest.listId())
                                .orElseThrow(() -> new TravelListNotFoundException("Lista não encontrada."));

                User user = (User) userRepository.findByEmail(sharedAccessRequest.email());
                if (user == null) {
                        throw new UserNotFoundException("Usuário não encontrado.");
                }

                User owner = validateAndGetUser(token);

                boolean hasOwner = travelListRepository.findByOwnerId(owner.getId()).isPresent();

                if (!hasOwner) {
                        throw new SharedAccessUnauthorized(
                                        "Você não tem permissão pra adicionar acessos a esta lista.");
                }

                // Verificar se já existe um compartilhamento
                if (sharedAccessRepository.existsBySharedListAndUser(travelList, user)) {
                        throw new SharedAccessException("Acesso já foi compartilhado com esse usuário.");
                }

                SharedAccess sharedAccess = new SharedAccess();
                sharedAccess.setSharedList(travelList);
                sharedAccess.setUser(user);
                sharedAccess.setCreatedAt(LocalDate.now());

                sharedAccessRepository.save(sharedAccess);
        }

        public List<SharedAccessResponse> getSharedAccessForList(Long listId, String token) {

                User user = validateAndGetUser(token);

                TravelList travelList = travelListRepository.findById(listId)
                                .orElseThrow(() -> new TravelListNotFoundException("Lista não encontrada."));

                boolean hasSharedAccess = sharedAccessRepository.findBySharedListAndUser(travelList, user)
                                .isPresent();

                if (travelList.getOwner().getId().equals(user.getId()) || hasSharedAccess == true) {
                        List<SharedAccessResponse> sharedAccess = sharedAccessRepository.findBySharedList(travelList)
                                        .stream()
                                        .map(SharedAccessResponse::new).toList();
                        return sharedAccess;
                } else {
                        throw new SharedAccessUnauthorized(
                                        "Você não tem permissão para acessar com quem o(a) dono(a) da lista compartilhou os acessos.");
                }

        }

        public void removeAccess(SharedAccessRemove sharedAccessRemove, String token) {

                User owner = validateAndGetUser(token);

                TravelList travelList = travelListRepository.findById(sharedAccessRemove.listId())
                                .orElseThrow(() -> new TravelListNotFoundException("Lista não encontrada."));

                if (travelList.getOwner().getId() != owner.getId()) {
                        throw new SharedAccessUnauthorized(
                                        "Você não tem permissão para remover o acesso de outros usuários");
                }

                User userRemove = userRepository.findById(sharedAccessRemove.userId())
                                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

                SharedAccess sharedAccess = sharedAccessRepository.findBySharedListAndUser(travelList, userRemove)
                                .orElseThrow(() -> new SharedAccessNotFoundException(
                                                "Acesso compartilhado não encontrado."));

                sharedAccessRepository.delete(sharedAccess);
        }

        private User validateAndGetUser(String token) {
                Long userId = tokenService.getUserFromToken(token);
                return userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
        }

}
