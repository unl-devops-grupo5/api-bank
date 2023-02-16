package ec.com.unl.devops.apibank.controller.declaration;

import ec.com.unl.devops.apibank.domain.dto.AccountDto;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IAccountController {
    @GetMapping
    ResponseEntity<List<AccountDto>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<AccountDto> getById(@PathVariable long id);

    @GetMapping("/cliente/{idClient}")
    ResponseEntity<List<AccountDto>> getByIdClient(@PathVariable long idClient);

    @PostMapping
    ResponseEntity<AccountDto> create(@RequestBody @Valid AccountDto data);

    @PutMapping("/{id}")
    ResponseEntity<AccountDto> update(@PathVariable long id, @RequestBody @Valid AccountDto data);

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteById(@PathVariable long id);
}
