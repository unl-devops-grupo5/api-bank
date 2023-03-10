package ec.com.unl.devops.apibank.domain.dto;

import ec.com.unl.devops.apibank.domain.model.Account;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;

    @NotEmpty(message = "Number is required")
    private String number;

    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private Account.Type type;

    private BigDecimal initialBalance;

    @NotNull(message = "State is required")
    private Boolean state;

    @NotNull(message = "ID client is required")
    private Long clientId;
}
