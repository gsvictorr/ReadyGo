package br.com.tudonamala.backend.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.dto.travelList.TravelListRequest;
import br.com.tudonamala.backend.dto.travelList.TravelListResponse;
import br.com.tudonamala.backend.enums.ListPlan;
import br.com.tudonamala.backend.exception.system.SystemException;
import br.com.tudonamala.backend.exception.travelList.TravelListLimitException;
import br.com.tudonamala.backend.exception.user.UserNotFoundException;
import br.com.tudonamala.backend.model.TravelList;
import br.com.tudonamala.backend.model.User;
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

    public TravelListResponse createListFree(TravelListRequest travelListRequest, String token) {

        Long userId = tokenService.getUserFromToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        long existingListsCount = travelListRepository.findByOwner(user).size();
        if (existingListsCount >= 3) {
            throw new TravelListLimitException(
                    "Você atingiu o limite de 3 listas. Exclua alguma para poder criar outra!");
        }

        try {
            TravelList list = new TravelList();
            list.setName(travelListRequest.name());
            list.setPlan(ListPlan.FREE);
            list.setOwner(user);
            list.setCreatedAt(LocalDateTime.now());

            travelListRepository.save(list);
            TravelListResponse travelListResponse = new TravelListResponse(list);
            return travelListResponse;

        } catch (Exception e) {
            throw new SystemException(
                    "Ocorreu um erro ao executar a ação. Verifique sua conexão com a internet e tente novamente mais tarde ou entre em contato com o nosso suporte.");
        }

    }

}
