package ec.com.unl.devops.apibank.service;

import ec.com.unl.devops.apibank.domain.dto.ClientDto;
import ec.com.unl.devops.apibank.domain.model.Client;
import ec.com.unl.devops.apibank.repository.ClientRepository;
import ec.com.unl.devops.apibank.service.declaration.IClientService;
import ec.com.unl.devops.apibank.service.implementation.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private IClientService clientService;

    @Autowired
    private ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp() {
        this.clientRepositoryMock = Mockito.mock(ClientRepository.class);
        this.clientService = new ClientService(this.clientRepositoryMock);
    }

    @Test
    void getById() {
        long id = 1;
        Client clientEntity = new Client();
        clientEntity.setId(id);
        clientEntity.setName("Pedro");

        Mockito.when(this.clientRepositoryMock.findById(id)).thenReturn(Optional.of(clientEntity));
        ClientDto dto = this.clientService.getById(id);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
    }

}
