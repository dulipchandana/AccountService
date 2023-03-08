package com.igreendata.account.service;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AccountServiceImpl define business logics for account .
 *
 * @author Dulip Chandana
 */
@Service
@Qualifier("com.igreendata.account.service.AccountServiceImpl")
@AllArgsConstructor
public class AccountServiceImpl implements BankService<AccountDto> {

    private final AccountRepository<AccountDto> accountRepository;

    /**
     * Get Accountdto Page list with filtering User Id
     *
     * @param id
     * @return Page<AccountDto>
     */
    @Override
    public List<AccountDto> getDtoById(final Long id) {

        try {
            List<AccountDto> accountDtoResults = accountRepository.findAccountByUserId(id);
            if (!accountDtoResults.isEmpty()) {
                return accountDtoResults;
            } else {
                throw new ResourceNotFoundException("Account not found", "userId", id);
            }
        } catch (ResourceNotFoundException re) {
            throw re;
        } catch (Exception ex) {
            throw new ServiceException("Account Service Error", "userId", id);
        }
    }
}
