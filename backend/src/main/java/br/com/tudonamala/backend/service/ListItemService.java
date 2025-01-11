package br.com.tudonamala.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.dto.listItem.ListItemCompleteRequest;
import br.com.tudonamala.backend.dto.listItem.ListItemRequest;
import br.com.tudonamala.backend.dto.listItem.ListItemResponse;
import br.com.tudonamala.backend.dto.listItem.ListItemUpdateRequest;
import br.com.tudonamala.backend.enums.ListPlan;
import br.com.tudonamala.backend.exception.listItem.ListItemLimitException;
import br.com.tudonamala.backend.exception.listItem.ListItemNotFoundException;
import br.com.tudonamala.backend.exception.system.SystemException;
import br.com.tudonamala.backend.exception.travelList.TravelListNotFoundException;
import br.com.tudonamala.backend.model.ListItem;
import br.com.tudonamala.backend.model.TravelList;
import br.com.tudonamala.backend.repository.ListItemRepository;
import br.com.tudonamala.backend.repository.TravelListRepository;

@Service
public class ListItemService {

    @Autowired
    private ListItemRepository listItemRepository;

    @Autowired
    private TravelListRepository travelListRepository;

    public void addItemToList(ListItemRequest listItemRequest) {

        TravelList list = travelListRepository.findById(listItemRequest.listId())
                .orElseThrow(() -> new TravelListNotFoundException("Lista não encontrada."));

        validateItemListLimit(list);

        try {
            ListItem listItem = new ListItem();
            listItem.setName(listItemRequest.name());
            listItem.setCategory(listItemRequest.categoryType());
            listItem.setQuantity(listItemRequest.quantity());
            listItem.setTravelList(list);
            listItemRepository.save(listItem);

        } catch (Exception e) {
            throw new SystemException(
                    "Ocorreu um erro ao executar a ação. Verifique sua conexão com a internet e tente novamente mais tarde ou entre em contato com o suporte.");
        }
    }

    public ListItemResponse updateItemlist(ListItemUpdateRequest listItemUpdateRequest) {

        TravelList list = travelListRepository.findById(listItemUpdateRequest.listId())
                .orElseThrow(() -> new TravelListNotFoundException("Lista não encontrada."));

        ListItem item = listItemRepository.findById(listItemUpdateRequest.id())
                .orElseThrow(() -> new TravelListNotFoundException("Item não encontrado na lista."));

        try {
            item.setName(listItemUpdateRequest.name());
            item.setCategory(listItemUpdateRequest.categoryType());
            item.setQuantity(listItemUpdateRequest.quantity());
            item.setTravelList(list);
            listItemRepository.save(item);

            return new ListItemResponse(item);

        } catch (Exception e) {
            throw new SystemException(
                    "Ocorreu um erro ao executar a ação. Verifique sua conexão com a internet e tente novamente mais tarde ou entre em contato com o suporte.");
        }
    }

    public ListItemResponse completeItem(ListItemCompleteRequest listItemCompleteRequest) {

        TravelList list = travelListRepository.findById(listItemCompleteRequest.listId())
                .orElseThrow(() -> new TravelListNotFoundException("Lista não encontrada."));

        ListItem item = listItemRepository.findById(listItemCompleteRequest.id())
                .orElseThrow(() -> new TravelListNotFoundException("Item não encontrado na lista."));

        validateItemListLimit(list);

        try {
            item.setCompleted(listItemCompleteRequest.complete());
            listItemRepository.save(item);

            return new ListItemResponse(item);

        } catch (Exception e) {
            throw new SystemException(
                    "Ocorreu um erro ao executar a ação. Verifique sua conexão com a internet e tente novamente mais tarde ou entre em contato com o suporte.");
        }
    }

    public void deleteItemList(Long id) {

        try {
            if (id == null) {
                throw new SystemException("ID do item não pode ser nulo.");
            } else {
                listItemRepository.deleteById(id);
            }

        } catch (ListItemNotFoundException e) {
            throw new ListItemNotFoundException("Item com esse ID não foi localizado ou já foi excluido.");
        }

    }

    private void validateItemListLimit(TravelList travelList) {

        long currentItemCount = listItemRepository.countByTravelList(travelList);
        int itemLimit = travelList.getPlan() == ListPlan.FREE ? 2 : 5;

        String plano = travelList.getPlan().toString();

        switch (plano) {
            case "PREMIUM":
                plano = "PREMIUM";
                break;

            default:
                plano = "GRATUITO";
                break;
        }

        if (currentItemCount >= itemLimit) {
            throw new ListItemLimitException(
                    "O limite de " + itemLimit + " itens para o plano " + plano + " foi atingido.");
        }

    }

}
