package ec.com.unl.devops.apibank.controller.implementation;

import ec.com.unl.devops.apibank.controller.declaration.IMovementController;
import ec.com.unl.devops.apibank.domain.dto.MovementDto;
import ec.com.unl.devops.apibank.service.declaration.IMovementService;
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
public class MovementController implements IMovementController {
    private final IMovementService movementService;

    @Override
    public ResponseEntity<List<MovementDto>> getAll() {
        log.info("Get all movements");
        return ResponseEntity.ok(this.movementService.getAll());
    }

    @Override
    public ResponseEntity<MovementDto> getById(@PathVariable long id) {
        log.info("Get movement by id = " + id);
        return ResponseEntity.ok(this.movementService.getById(id));
    }

    @Override
    public ResponseEntity<List<MovementDto>> getByIdClient(@PathVariable long idAccount) {
        log.info("Get movements by account");
        return ResponseEntity.ok(this.movementService.getByAccount(idAccount));
    }

    @Override
    public ResponseEntity<MovementDto> create(@RequestBody @Valid MovementDto data) {
        log.info("Create movement = " + data);
        return new ResponseEntity<>(this.movementService.create(data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Long> deleteById(@PathVariable long id) {
        log.info("Delete movement by id = " + id);
        return ResponseEntity.ok(this.movementService.deleteById(id));
    }
}
