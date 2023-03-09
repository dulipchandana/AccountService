package com.igreendata.account.service;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.entity.Account;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.mapper.AccountMapper;
import com.igreendata.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AccountServiceImpl define business logics for account .
 *
 * @author Dulip Chandana
 */
@Service
@Qualifier("com.igreendata.account.service.AccountServiceImpl")
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    /**
     * Get AccountDto list with filtering User id
     *
     * @param id - userId
     * @return List<AccountDto> - account list
     */
    @Override
    public List<AccountDto> getAccountsByUserId(final Long id) {

        try {
            List<Account> accounts = accountRepository.findByUser_Id(id);
            if (!accounts.isEmpty()) {
                return accounts.stream()
                        .map(accountMapper::accountToAccountDto)
                        .collect(Collectors.toList());
            } else {
                throw new ResourceNotFoundException("Account", "userId", id);
            }
        } catch (ResourceNotFoundException re) {
            throw re;
        } catch (Exception ex) {
            throw new ServiceException("Account", "userId", id);
        }
    }

}
