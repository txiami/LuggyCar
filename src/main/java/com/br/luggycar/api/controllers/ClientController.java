package com.br.luggycar.api.controllers;

import com.br.luggycar.api.entities.Client;
import com.br.luggycar.api.exceptions.ResourceNotFoundException;
import com.br.luggycar.api.requests.ClientResquest;
import com.br.luggycar.api.services.ClientService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAll(){
        return ResponseEntity.ok(clientService.getAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Client> client = clientService.findClientById(id);

        if (client.isEmpty())  {
            throw new ResourceNotFoundException("Cliente não encontrado!");
        }

        return ResponseEntity.ok().body(client.get());

    }

    @PostMapping("/registration")
    public ResponseEntity<Client> insert(@RequestBody ClientResquest clientResquest) {
        Client client = clientService.insert(clientResquest);

        return ResponseEntity.status(HttpStatus.CREATED).body(client);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody ClientResquest clientResquest) throws ResourceNotFoundException {

            Optional<Client> client = clientService.findClientById(id);

            if (client.isEmpty()) {
                  throw new ResourceNotFoundException("Cliente não encontrado!");
            }

            Client clientResponse = clientService.update(id, clientResquest);

            return ResponseEntity.ok().body(clientResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id){
       clientService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException (ResourceNotFoundException ex){
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("messege", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
    }

}
