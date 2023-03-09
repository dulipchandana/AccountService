package com.igreendata.account.service;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.entity.Account;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.mapper.AccountMapper;
import com.igreendata.account.repository.AccountRepository;
import org.hibernate.QueryTimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountServiceImplTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountMapper accountMapper;

    @Test
    void getAccountsByUserIdWithResult() throws Exception {
        AccountDto accountDto = new AccountDto(100L, "test2", "USD", "Current", "08/03/2023", 6D);
        List<AccountDto> acList = List.of(accountDto);
        Account account = Account.builder().build();
        List<Account> accounts = Collections.singletonList(account);
        given(accountRepository.findByUser_Id(1L)).willReturn(accounts);
        given(accountMapper.accountToAccountDto(account)).willReturn(accountDto);
        assertThat(accountService.getAccountsByUserId(1L)).isEqualTo(acList);
        verify(accountRepository).findByUser_Id(1L);
        verify(accountMapper).accountToAccountDto(account);
    }

    @Test()
    void getAccountsByUserIdWithNoResult() {
        given(accountRepository.findByUser_Id(1L)).willReturn(new ArrayList());
        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> accountService.getAccountsByUserId(1L));
        assertThat(exception.getMessage()).isEqualTo("Account not found with userId : '1'");
    }

    @Test
    void getAccountsByUserIdWithDbException() {
        given(accountRepository.findByUser_Id(1L)).willThrow(QueryTimeoutException.class);
        ServiceException serviceException =
                assertThrows(ServiceException.class, () -> accountService.getAccountsByUserId(1L));
        assertThat(serviceException.getMessage()).isEqualTo("Account Service Exception . userId : '1'");
    }


}
