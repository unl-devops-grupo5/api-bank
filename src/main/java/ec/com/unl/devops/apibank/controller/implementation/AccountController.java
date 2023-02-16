package ec.com.unl.devops.apibank.controller.implementation;

import ec.com.unl.devops.apibank.controller.declaration.IAccountController;
import ec.com.unl.devops.apibank.domain.dto.AccountDto;
import ec.com.unl.devops.apibank.service.declaration.IAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class AccountController implements IAccountController {
    private final IAccountService accountService;

    @Override
    public ResponseEntity<List<AccountDto>> getAll() {
        log.info("Get all accounts");
        return ResponseEntity.ok(this.accountService.getAll());
    }

    @Override
    public ResponseEntity<AccountDto> getById(@PathVariable long id) {
        log.info("Get by id account = " + id);
        return ResponseEntity.ok(this.accountService.getById(id));
    }

    @Override
    public ResponseEntity<List<AccountDto>> getByIdClient(@PathVariable long idClient) {
        log.info("Get account by id client");
        return ResponseEntity.ok(this.accountService.getByClient(idClient));
    }

    @Override
    public ResponseEntity<AccountDto> create(@RequestBody @Valid AccountDto data) {
        log.info("Create account = " + data);
        return new ResponseEntity<>(this.accountService.create(data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountDto> update(@PathVariable long id, @RequestBody @Valid AccountDto data) {
        log.info("Update account id = " + id + ", data = " + data);
        return new ResponseEntity<>(this.accountService.update(id, data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> deleteById(@PathVariable long id) {
        log.info("Delete account by id = " + id);
        return ResponseEntity.ok(this.accountService.deleteById(id));
    }
}
