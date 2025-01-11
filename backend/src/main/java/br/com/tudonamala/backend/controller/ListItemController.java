package br.com.tudonamala.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tudonamala.backend.dto.listItem.ListItemCompleteRequest;
import br.com.tudonamala.backend.dto.listItem.ListItemRequest;
import br.com.tudonamala.backend.dto.listItem.ListItemResponse;
import br.com.tudonamala.backend.dto.listItem.ListItemUpdateRequest;
import br.com.tudonamala.backend.service.ListItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/item")
public class ListItemController {

    @Autowired
    private ListItemService listItemService;

    @PostMapping("/create")
    public ResponseEntity<Void> createListItem(@Valid @RequestBody ListItemRequest listItemRequest,
            @RequestHeader("Authorization") String token) {
        listItemService.addItemToList(listItemRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update")
    public ResponseEntity<ListItemResponse> updateListItem(
            @Valid @RequestBody ListItemUpdateRequest listItemUpdateRequest,
            @RequestHeader("Authorization") String token) {
        ListItemResponse listItemResponse = listItemService.updateItemlist(listItemUpdateRequest);
        return ResponseEntity.ok().body(listItemResponse);
    }

    @PutMapping("/complete")
    public ResponseEntity<ListItemResponse> completeItem(
            @Valid @RequestBody ListItemCompleteRequest listItemCompleteRequest,
            @RequestHeader("Authorization") String token) {
        ListItemResponse listItemResponse = listItemService.completeItem(listItemCompleteRequest);
        return ResponseEntity.ok().body(listItemResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteListItem(@PathVariable("id") Long id,
            @RequestHeader("Authorization") String token) {
        listItemService.deleteItemList(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
