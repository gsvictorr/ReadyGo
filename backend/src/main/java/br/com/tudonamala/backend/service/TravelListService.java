package br.com.tudonamala.backend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.dto.travelList.TravelListCompleteResponse;
import br.com.tudonamala.backend.dto.travelList.TravelListRequest;
import br.com.tudonamala.backend.dto.travelList.TravelListResponse;
import br.com.tudonamala.backend.enums.ListPlan;
import br.com.tudonamala.backend.exception.sharedAccess.SharedAccessUnauthorized;
import br.com.tudonamala.backend.exception.system.SystemException;
import br.com.tudonamala.backend.exception.travelList.TravelListLimitException;
import br.com.tudonamala.backend.exception.travelList.TravelListNotFoundException;
import br.com.tudonamala.backend.exception.user.UserNotFoundException;
import br.com.tudonamala.backend.model.TravelList;
import br.com.tudonamala.backend.model.User;
import br.com.tudonamala.backend.repository.SharedAccessRepository;
import br.com.tudonamala.backend.repository.TravelListRepository;
import br.com.tudonamala.backend.repository.UserRepository;

@Service
public class TravelListService {

    @Autowired
    private TravelListRepository travelListRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SharedAccessRepository sharedAccessRepository;

    public TravelListResponse createListFree(TravelListRequest travelListRequest, String token) {

        User user = validateAndGetUser(token);
        validateListLimit(user);

        try {
            TravelList list = new TravelList();
            list.setName(travelListRequest.name());
            list.setPlan(ListPlan.FREE);
            list.setOwner(user);
            list.setCreatedAt(LocalDate.now());

            travelListRepository.save(list);
            return new TravelListResponse(list);

        } catch (Exception e) {
            throw new SystemException(
                    "Ocorreu um erro ao executar a ação. Verifique sua conexão com a internet e tente novamente mais tarde ou entre em contato com o suporte.");
        }
    }

    public TravelListCompleteResponse findByIdWithAccessCheck(Long listId, String token) {

        User user = validateAndGetUser(token);

        // Buscar a lista pelo ID
        TravelList travelList = travelListRepository.findById(listId)
                .orElseThrow(() -> new TravelListNotFoundException("Lista não encontrada."));

        // Verificar se o usuário é o proprietário da lista
        if (travelList.getOwner().getId().equals(user.getId())) {
            return new TravelListCompleteResponse(travelList);
        }

        // Verificar se o usuário possui acesso compartilhado
        boolean hasSharedAccess = sharedAccessRepository.findBySharedListAndUser(travelList, user)
                .isPresent();
        if (!hasSharedAccess) {
            throw new SharedAccessUnauthorized("Você não tem permissão para acessar esta lista.");
        }

        return new TravelListCompleteResponse(travelList);
    }

    private User validateAndGetUser(String token) {
        Long userId = tokenService.getUserFromToken(token);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
    }

    private void validateListLimit(User user) {
        long existingListsCount = travelListRepository.findByOwner(user).size();
        if (existingListsCount >= 3) {
            throw new TravelListLimitException(
                    "Você atingiu o limite de 3 listas. Exclua alguma para criar outra.");
        }
    }

}
