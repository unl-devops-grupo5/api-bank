package ec.com.unl.devops.apibank.service.declaration;

import ec.com.unl.devops.apibank.domain.dto.MovementDto;

import java.util.List;

/**
 * CRUD movement
 *
 * @author Pedro Aponte <pedro.aponte@unl.edu.ec>
 * @version 0.0.1
 */
public interface IMovementService {
    /**
     * Get movement by id
     * @param id ID movement to search
     * @return Movement
     */
    MovementDto getById(long id);

    /**
     * Get all movements
     *
     * @return List of movements
     */
    List<MovementDto> getAll();

    /**
     * Get all account movements
     * @param idAccount ID account
     * @return List of client accounts
     */
    List<MovementDto> getByAccount(long idAccount);

    /**
     * Create movement
     * @param data Object with information of movement
     * @return Movement
     */
    MovementDto create(MovementDto data);

    /**
     * Delete movement
     * @param id ID movement
     * @return ID
     */
    long deleteById(long id);
}
