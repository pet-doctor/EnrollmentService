package com.petdoctor.enrollment.api;

import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/petdoctor/enrollment/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getClientById(@RequestParam Long clientId) {

        try {

            ClientDto clientDto = clientService.getClientById(clientId);

            if (clientDto == null) {

                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(clientDto);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody ClientDto clientDto) {

        try {

            ClientDto registeredClientDto = clientService.registerClient(clientDto);

            if (registeredClientDto == null) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> changeClientInfo(@RequestParam Long clientId, @RequestBody ClientDto clientDto) {

        try {

            ClientDto changedClientDto = clientService.changeClientInfo(clientId, clientDto);

            if (changedClientDto == null) {

                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(changedClientDto);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }
}
