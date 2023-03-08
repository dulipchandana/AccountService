package com.igreendata.account.service;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.repository.AccountRepository;
import com.igreendata.account.util.TransactionType;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    AccountRepository accountRepository;


    @Test
    public void getTransactionsByAccountIdWithResult() throws Exception {
        TransactionDto tansactionDto = new TransactionDto(1L, "test", "USD", new Date(), 12D
                , 3D, TransactionType.Credit, "NC", 1L);
        List<TransactionDto> transactionDtoList = Arrays.asList(tansactionDto);
        given(accountRepository.findAccountAccountId(1L)).willReturn(transactionDtoList);
        assertThat(transactionService.getDtoById(1L)).isEqualTo(transactionDtoList);
    }

    @Test
    void getTransactionWithNoResult() throws Exception {
        given(accountRepository.findAccountAccountId(1L)).willReturn(new ArrayList());
        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class,() ->transactionService.getDtoById(1L)) ;

    }

    @Test
    void getTransactionWithDbException() throws Exception {
        given(accountRepository.findAccountAccountId(1L)).willThrow(QueryTimeoutException.class);
        assertThrows(ServiceException.class,() ->transactionService.getDtoById(1L)) ;
    }
}