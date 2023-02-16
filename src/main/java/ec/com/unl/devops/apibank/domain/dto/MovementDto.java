package ec.com.unl.devops.apibank.domain.dto;

import ec.com.unl.devops.apibank.domain.model.Movement;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovementDto {
    private Long id;

    private LocalDate date;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private Movement.Type type;

    @NotNull(message = "Mount is required")
    private BigDecimal amount;

    private BigDecimal balance;

    @NotNull(message = "ID account is required")
    private Long accountId;

}
