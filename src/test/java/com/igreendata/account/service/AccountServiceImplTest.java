package com.igreendata.account.service;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
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
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceImplTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void getAccountsByUserIdWithResult() throws Exception {
        AccountDto accountDto1 = new AccountDto(99L, "test1", "AUD", "Savings", new Date(), 5D);
        AccountDto accountDto2 = new AccountDto(100L, "test2", "USD", "Current", new Date(), 6D);
        List<AccountDto> acList = Arrays.asList(accountDto1, accountDto2);
        given(accountRepository.findAccountByUserId(1L)).willReturn(acList);
        assertThat(accountService.getDtoById(1L)).isEqualTo(acList);
        verify(accountRepository).findAccountByUserId(1L);
    }

    @Test()
    void getAccountsByUserIdWithNoResult() {
        given(accountRepository.findAccountByUserId(1L)).willReturn(new ArrayList());
        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> accountService.getDtoById(1L));
        assertThat(exception.getMessage()).isEqualTo("Account not found with userId : '1'");
    }

    @Test
    void getAccountsByUserIdWithDbException() {
        given(accountRepository.findAccountByUserId(1L)).willThrow(QueryTimeoutException.class);
        ServiceException serviceException =
                assertThrows(ServiceException.class, () -> accountService.getDtoById(1L));
        assertThat(serviceException.getMessage()).isEqualTo("Account Service Exception . userId : '1'");
    }


}
