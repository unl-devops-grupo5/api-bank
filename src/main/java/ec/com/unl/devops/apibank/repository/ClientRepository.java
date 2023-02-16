package ec.com.unl.devops.apibank.repository;

import ec.com.unl.devops.apibank.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByDni(String dni);

}
