package ec.com.unl.devops.apibank.controller.implementation;

import ec.com.unl.devops.apibank.controller.declaration.IClientController;
import ec.com.unl.devops.apibank.domain.dto.ClientDto;
import ec.com.unl.devops.apibank.service.declaration.IClientService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ClientController implements IClientController {
    private final IClientService clientService;

    @Override
    public ResponseEntity<List<ClientDto>> getAll() {
        log.info("Get all clients");
        return ResponseEntity.ok(this.clientService.getAll());
    }

    @Override
    public ResponseEntity<ClientDto> getById(@PathVariable long id) {
        log.info("Get client by id = " + id);
        return ResponseEntity.ok(this.clientService.getById(id));
    }

    @Override
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto data) {
        log.info("Create client = " + data);
        return new ResponseEntity<>(this.clientService.create(data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientDto> update(@PathVariable long id, @RequestBody @Valid ClientDto data) {
        log.info("Update client id = " + id + ", data = " + data);
        return new ResponseEntity<>(this.clientService.update(id, data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> deleteById(@PathVariable long id) {
        log.info("Delete client by id = " + id);
        return ResponseEntity.ok(this.clientService.deleteById(id));
    }
}
