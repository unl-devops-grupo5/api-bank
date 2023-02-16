package ec.com.unl.devops.apibank.service.implementation;

import ec.com.unl.devops.apibank.domain.dto.MovementDto;
import ec.com.unl.devops.apibank.domain.model.Account;
import ec.com.unl.devops.apibank.domain.model.Movement;
import ec.com.unl.devops.apibank.exceptions.EntityNotFoundException;
import ec.com.unl.devops.apibank.exceptions.PreconditionFailedException;
import ec.com.unl.devops.apibank.repository.MovementRepository;
import ec.com.unl.devops.apibank.service.declaration.IMovementService;
import ec.com.unl.devops.apibank.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/** {@inheritDoc} */
@Service
@RequiredArgsConstructor
@Slf4j
public class MovementService implements IMovementService {

    private final MovementRepository movementRepository;

    /** {@inheritDoc} */
    @Override
    public MovementDto getById(long id) {
        return convertEntityToDto(getEntityById(id));
    }

    /** {@inheritDoc} */
    @Override
    public List<MovementDto> getAll() {
        return this.movementRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<MovementDto> getByAccount(long idAccount) {
        Account account = new Account();
        account.setId(idAccount);
        return this.movementRepository.findByAccount(account).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public MovementDto create(MovementDto data) {
        if(data.getType().compareTo(Movement.Type.DEBIT) == 0) {
            data.setAmount(data.getAmount().multiply(new BigDecimal(-1)));
            this.validateDebit(data);
        } else {
            this.validateCredit(data);
        }
        data.setDate(LocalDate.now());
        Movement movement = convertDtoToEntity(data);
        return convertEntityToDto(this.movementRepository.save(movement));
    }

    /** {@inheritDoc} */
    @Override
    public long deleteById(long id) {
        Movement movement = getEntityById(id);
        Optional<Movement> optional = this.movementRepository.findTopByAccountOrderByDateAndIdDesc(movement.getAccount().getId());
        if(optional.isPresent() && !Objects.equals(optional.get().getId(), movement.getId())){
            throw new PreconditionFailedException("Solo se puede eliminar el último movimiento");
        }
        this.movementRepository.deleteById(id);
        return id;
    }

    private Movement getEntityById(long id) {
        Optional<Movement> optional = this.movementRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException("Movimiento no encontrado id: " + id);
        }
        return optional.get();
    }

    private void validateDebit(MovementDto data) {
        BigDecimal balance = availableBalance(data.getAccountId());
        log.info("Balance " + balance + " of account " + data.getAccountId());
        if(balance.compareTo(BigDecimal.ZERO) <= 0 || balance.compareTo(data.getAmount().multiply(BigDecimal.valueOf(-1))) < 0) {
            throw new PreconditionFailedException("Saldo no disponible");
        }

        BigDecimal sum = dailyAmount(data.getAccountId());
        log.info("Daily amount " + sum + " of account " + data.getAccountId());
        if(sum.add(data.getAmount().multiply(BigDecimal.valueOf(-1))).compareTo(BigDecimal.valueOf(1000)) > 0) {
            throw new PreconditionFailedException("Cupo diario excedido");
        }

        data.setBalance(balance.add(data.getAmount()));
    }

    private void validateCredit(MovementDto data) {
        BigDecimal balance = availableBalance(data.getAccountId());
        data.setBalance(balance.add(data.getAmount()));
    }

    private BigDecimal dailyAmount(long accountId) {
        Account account = new Account();
        account.setId(accountId);
        List<Movement> listMovements = this.movementRepository.findByAccountAndDate(account, LocalDate.now());
        BigDecimal sum = listMovements
                .stream()
                .filter(x -> x.getType().equals(Movement.Type.DEBIT))
                .map(Movement::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.multiply(BigDecimal.valueOf(-1));
    }

    private BigDecimal availableBalance(long accountId) {
        Account account = new Account();
        account.setId(accountId);
        Optional<Movement> optional = this.movementRepository.findTopByAccountOrderByDateAndIdDesc(accountId);
        if (optional.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return optional.get().getBalance();
    }

    private MovementDto convertEntityToDto(Movement entity) {
        return Mapper.modelMapper().map(entity, MovementDto.class);
    }

    private Movement convertDtoToEntity(MovementDto entity) {
        return Mapper.modelMapper().map(entity, Movement.class);
    }

}
