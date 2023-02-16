package ec.com.unl.devops.apibank.service;

import ec.com.unl.devops.apibank.domain.dto.MovementDto;
import ec.com.unl.devops.apibank.domain.model.Movement;
import ec.com.unl.devops.apibank.repository.MovementRepository;
import ec.com.unl.devops.apibank.service.declaration.IMovementService;
import ec.com.unl.devops.apibank.service.implementation.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MovementServiceImplTest {

    private IMovementService movementService;
    private MovementRepository movementRepositoryMock;

    @BeforeEach
    void setUp() {
        this.movementRepositoryMock = Mockito.mock(MovementRepository.class);
        this.movementService = new MovementService(this.movementRepositoryMock);
    }

    @Test
    void getById() {
        long id = 1;
        Movement movementEntity = new Movement();
        movementEntity.setId(id);
        movementEntity.setType(Movement.Type.DEBIT);

        Mockito.when(this.movementRepositoryMock.findById(id)).thenReturn(Optional.of(movementEntity));
        MovementDto dto = this.movementService.getById(id);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
    }

}
