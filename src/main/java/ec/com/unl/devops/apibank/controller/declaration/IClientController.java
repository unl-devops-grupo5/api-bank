package ec.com.unl.devops.apibank.controller.declaration;

import ec.com.unl.devops.apibank.domain.dto.ClientDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IClientController {
    @GetMapping
    ResponseEntity<List<ClientDto>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<ClientDto> getById(@PathVariable long id);

    @PostMapping
    ResponseEntity<ClientDto> create(@RequestBody @Valid ClientDto data);

    @PutMapping("/{id}")
    ResponseEntity<ClientDto> update(@PathVariable long id, @RequestBody @Valid ClientDto data);

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteById(@PathVariable long id);
}
