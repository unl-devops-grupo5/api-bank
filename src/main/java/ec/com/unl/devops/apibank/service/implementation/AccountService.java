package ec.com.unl.devops.apibank.service.implementation;

import ec.com.unl.devops.apibank.domain.dto.AccountDto;
import ec.com.unl.devops.apibank.domain.model.Account;
import ec.com.unl.devops.apibank.domain.model.Client;
import ec.com.unl.devops.apibank.domain.model.Movement;
import ec.com.unl.devops.apibank.exceptions.EntityNotFoundException;
import ec.com.unl.devops.apibank.repository.AccountRepository;
import ec.com.unl.devops.apibank.repository.MovementRepository;
import ec.com.unl.devops.apibank.service.declaration.IAccountService;
import ec.com.unl.devops.apibank.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/** {@inheritDoc} */
@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final MovementRepository movementRepository;

    /** {@inheritDoc} */
    @Override
    public AccountDto getById(long id) {
        return convertEntityToDto(getEntityById(id));
    }

    /** {@inheritDoc} */
    @Override
    public AccountDto getByNumber(String number) {
        return convertEntityToDto(getEntityByNumber(number));
    }


    /** {@inheritDoc} */
    @Override
    public List<AccountDto> getAll() {
        return this.accountRepository.findAll().stream().map(this::convertEntityToDto).toList();
    }

    /** {@inheritDoc} */
    @Override
    public List<AccountDto> getByClient(long idClient) {
        Client client = new Client();
        client.setId(idClient);
        return this.accountRepository.findByClient(client).stream().map(this::convertEntityToDto).toList();
    }

    /** {@inheritDoc} */
    @Transactional
    @Override
    public AccountDto create(AccountDto data) {
        data.setInitialBalance(data.getInitialBalance()==null?BigDecimal.ZERO:data.getInitialBalance());
        Account account = convertDtoToEntity(data);
        AccountDto accountDto = convertEntityToDto(this.accountRepository.save(account));
        if (data.getInitialBalance().compareTo(BigDecimal.ZERO) > 0) {
            Movement movement = new Movement();
            movement.setType(Movement.Type.CREDIT);
            movement.setBalance(data.getInitialBalance());
            account.setId(accountDto.getId());
            movement.setAccount(account);
            movement.setDate(LocalDate.now());
            movement.setAmount(data.getInitialBalance());
            movementRepository.save(movement);
        }
        return accountDto;
    }

    /** {@inheritDoc} */
    @Override
    public AccountDto update(long id, AccountDto data) {
        Account account = getEntityById(id);
        account.setNumber(data.getNumber());
        account.setType(data.getType());
        account.getClient().setId(data.getClientId());
        account.setState(data.getState());
        return convertEntityToDto(this.accountRepository.save(account));
    }

    /** {@inheritDoc} */
    @Override
    public long deleteById(long id) {
        getEntityById(id);
        this.accountRepository.deleteById(id);
        return id;
    }

    private Account getEntityById(long id) {
        Optional<Account> optional = this.accountRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException("Cuenta no encontrada id: " + id);
        }
        return optional.get();
    }

    private Account getEntityByNumber(String number) {
        Optional<Account> optional = this.accountRepository.findByNumber(number);
        if(optional.isEmpty()){
            throw new EntityNotFoundException("Cuenta no encontrada número: " + number);
        }
        return optional.get();
    }

    private AccountDto convertEntityToDto(Account entity) {
        return Mapper.modelMapper().map(entity, AccountDto.class);
    }

    private Account convertDtoToEntity(AccountDto entity) {
        return Mapper.modelMapper().map(entity, Account.class);
    }

}
