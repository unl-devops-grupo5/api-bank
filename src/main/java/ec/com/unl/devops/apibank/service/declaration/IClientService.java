package ec.com.unl.devops.apibank.service.declaration;


import ec.com.unl.devops.apibank.domain.dto.ClientDto;

import java.util.List;

/**
 * CRUD client
 *
 * @author Francisco Riofrio <francisco.riofrio@unl.edu.ec>
 * @version 0.0.1
 */
public interface IClientService {
    /**
     * Get client by id
     * @param id ID client to search
     * @return Client
     */
    ClientDto getById(long id);

    /**
     * Get all clients
     *
     * @return List of clients
     */
    List<ClientDto> getAll();

    /**
     * Create client
     * @param data Object with information of client
     * @return Client
     */
    ClientDto create(ClientDto data);

    /**
     * Update client
     * @param id ID client
     * @param data Object with information of client
     * @return Client
     */
    ClientDto update(long id, ClientDto data);

    /**
     * Delete client
     * @param id ID client
     * @return ID
     */
    long deleteById(long id);
}
