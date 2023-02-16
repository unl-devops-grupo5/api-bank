package ec.com.unl.devops.apibank.controller.declaration;

import ec.com.unl.devops.apibank.domain.dto.MovementDto;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/movimientos", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IMovementController {
    @GetMapping
    ResponseEntity<List<MovementDto>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<MovementDto> getById(@PathVariable long id);

    @GetMapping("/cuenta/{idAccount}")
    ResponseEntity<List<MovementDto>> getByIdClient(@PathVariable long idAccount);

    @PostMapping
    ResponseEntity<MovementDto> create(@RequestBody @Valid MovementDto data);

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteById(@PathVariable long id);
}
