package ec.com.unl.devops.apibank.service.declaration;

import ec.com.unl.devops.apibank.domain.dto.AccountDto;

import java.util.List;

/**
 * CRUD account
 *
 * @author Daniel Leon <daniel.e.leon@unl.edu.ec>
 * @version 0.0.1
 */
public interface IAccountService {
    /**
     * Get account by id
     * @param id ID account to search
     * @return Account
     */
    AccountDto getById(long id);

    /**
     * Get all accounts
     *
     * @return List of accounts
     */
    List<AccountDto> getAll();

    /**
     * Get all client accounts
     * @param idClient ID client
     * @return List of client accounts
     */
    List<AccountDto> getByClient(long idClient);

    /**
     * Get account by number
     * @param number Account number
     * @return List of client accounts
     */
    AccountDto getByNumber(String number);

    /**
     * Create account
     * @param data Object with information of account
     * @return Account
     */
    AccountDto create(AccountDto data);

    /**
     * Update account
     * @param id ID account
     * @param data Object with information of account
     * @return Account
     */
    AccountDto update(long id, AccountDto data);

    /**
     * Delete account
     * @param id ID account
     * @return ID
     */
    long deleteById(long id);
}
