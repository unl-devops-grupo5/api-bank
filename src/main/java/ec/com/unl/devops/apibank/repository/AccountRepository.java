package ec.com.unl.devops.apibank.repository;

import ec.com.unl.devops.apibank.domain.model.Account;
import ec.com.unl.devops.apibank.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByClient(Client client);

    Optional<Account> findByNumber(String number);

}
