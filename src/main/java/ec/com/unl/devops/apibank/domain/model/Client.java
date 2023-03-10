package ec.com.unl.devops.apibank.domain.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@Entity(name = "client")
@SuperBuilder
public class Client extends Person {

    @Size(min=4, message = "Password should have at least 4 characters")
    private String password;
    @NotNull
    private Boolean state;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;

    public Client() {
        super();
    }

}
