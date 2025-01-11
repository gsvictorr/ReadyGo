package br.com.tudonamala.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tudonamala.backend.dto.travelList.TravelListCompleteResponse;
import br.com.tudonamala.backend.dto.travelList.TravelListRequest;
import br.com.tudonamala.backend.dto.travelList.TravelListResponse;
import br.com.tudonamala.backend.service.TravelListService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/list")
public class TravelListController {

    @Autowired
    private TravelListService travelListService;

    @PostMapping("/create/free")
    public ResponseEntity<TravelListResponse> createFreeList(@Valid @RequestBody TravelListRequest travelListRequest,
            @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        TravelListResponse response = travelListService.createListFree(travelListRequest, jwtToken);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{listId}")
    public ResponseEntity<TravelListCompleteResponse> getListAccessCheck(@Valid @PathVariable("listId") Long listId,
            @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        TravelListCompleteResponse response = travelListService.findByIdWithAccessCheck(listId, jwtToken);
        return ResponseEntity.ok().body(response);
    }
}
