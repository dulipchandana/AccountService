package com.igreendata.account.service;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.exception.IncorrectParameterException;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AccountServiceImpl define business logics for account .
 *
 * @author Dulip Chandana
 */
@Service
@Qualifier("com.igreendata.account.service.AccountServiceImpl")
public class AccountServiceImpl implements BankService<AccountDto> {

    @Autowired
    AccountRepository<AccountDto> accountRepository;

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
                throw new ResourceNotFoundException("Account", "userId", id);
            }
        } catch (InvalidDataAccessApiUsageException invalidDataAccessApiUsageException) {
            throw new IncorrectParameterException("Account", "userId", id);

        }
    }
}
