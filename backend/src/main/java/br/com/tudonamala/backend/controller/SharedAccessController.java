package br.com.tudonamala.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tudonamala.backend.dto.sharedAccess.SharedAccessList;
import br.com.tudonamala.backend.dto.sharedAccess.SharedAccessRemove;
import br.com.tudonamala.backend.dto.sharedAccess.SharedAccessRequest;
import br.com.tudonamala.backend.dto.sharedAccess.SharedAccessResponse;
import br.com.tudonamala.backend.service.SharedAccessService;

@RestController
@RequestMapping("/shared-access")
public class SharedAccessController {

    @Autowired
    private SharedAccessService sharedAccessService;

    @PostMapping("/create")
    public ResponseEntity<?> shareAccess(@RequestBody SharedAccessRequest sharedAccessRequest,
            @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        sharedAccessService.shareAccess(sharedAccessRequest, jwtToken);
        return ResponseEntity.ok("Acesso compartilhado com sucesso.");
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<SharedAccessList> getSharedAccess(@PathVariable("listId") Long listId,
            @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        List<SharedAccessResponse> sharedAccesses = sharedAccessService.getSharedAccessForList(listId, jwtToken);
        SharedAccessList sharedAccessList = new SharedAccessList(sharedAccesses);
        return ResponseEntity.ok().body(sharedAccessList);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeAccess(@RequestBody SharedAccessRemove sharedAccessRemove,
            @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        sharedAccessService.removeAccess(sharedAccessRemove, jwtToken);
        return ResponseEntity.ok("Acesso removido com sucesso.");
    }
}
