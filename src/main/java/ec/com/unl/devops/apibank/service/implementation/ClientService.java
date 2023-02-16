package ec.com.unl.devops.apibank.service.implementation;

import ec.com.unl.devops.apibank.domain.dto.ClientDto;
import ec.com.unl.devops.apibank.domain.model.Client;
import ec.com.unl.devops.apibank.exceptions.EntityNotFoundException;
import ec.com.unl.devops.apibank.repository.ClientRepository;
import ec.com.unl.devops.apibank.service.declaration.IClientService;
import ec.com.unl.devops.apibank.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** {@inheritDoc} */
@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    /** {@inheritDoc} */
    @Override
    public ClientDto getById(long id) {
        return convertEntityToDto(getEntityById(id));
    }

    /** {@inheritDoc} */
    @Override
    public List<ClientDto> getAll() {
        return this.clientRepository.findAll().stream().map(this::convertEntityToDto).toList();
    }

    /** {@inheritDoc} */
    @Override
    public ClientDto create(ClientDto data) {
        Client client = convertDtoToEntity(data);
        return convertEntityToDto(this.clientRepository.save(client));
    }

    /** {@inheritDoc} */
    @Override
    public ClientDto update(long id, ClientDto data) {
        Client client = getEntityById(id);
        client.setPassword(data.getPassword());
        client.setState(data.getState());
        client.setAge(data.getAge());
        client.setAddress(data.getAddress());
        client.setDni(data.getDni());
        client.setName(data.getName());
        client.setGender(data.getGender());
        client.setPhone(data.getPhone());
        return convertEntityToDto(this.clientRepository.save(client));
    }

    /** {@inheritDoc} */
    @Override
    public long deleteById(long id) {
        getEntityById(id);
        this.clientRepository.deleteById(id);
        return id;
    }

    public Client getEntityById(long id) {
        Optional<Client> optional = this.clientRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException("Cliente no encontrado id: " + id);
        }
        return optional.get();
    }

    private ClientDto convertEntityToDto(Client entity) {
        return Mapper.modelMapper().map(entity, ClientDto.class);
    }

    private Client convertDtoToEntity(ClientDto entity) {
        return Mapper.modelMapper().map(entity, Client.class);
    }

}
