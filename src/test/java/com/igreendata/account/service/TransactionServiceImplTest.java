package com.igreendata.account.service;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.model.TransactionType;
import com.igreendata.account.repository.TransactionRepository;
import org.hibernate.QueryTimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
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
    TransactionRepository transactionRepository;


    @Test
    public void getTransactionsByAccountIdWithResult() throws Exception {
        TransactionDto tansactionDto = new TransactionDto(1L, "test", "USD", new Date(), 12D
                , 3D, TransactionType.Credit, "NC", 1L);
        List<TransactionDto> transactionDtoList = List.of(tansactionDto);
        given(transactionRepository.findTransactionByAccountId(1L)).willReturn(transactionDtoList);
        assertThat(transactionService.getTransactionDtoByAccountId(1L)).isEqualTo(transactionDtoList);
    }

    @Test
    void getTransactionWithNoResult() throws Exception {
        given(transactionRepository.findTransactionByAccountId(1L)).willReturn(new ArrayList());
        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> transactionService.getTransactionDtoByAccountId(1L));
        assertThat(exception.getMessage()).isEqualTo("Transaction not found with accountId : '1'");

    }

    @Test
    void getTransactionWithDbException() throws Exception {
        given(transactionRepository.findTransactionByAccountId(1L)).willThrow(QueryTimeoutException.class);
        ServiceException serviceException =
                assertThrows(ServiceException.class, () -> transactionService.getTransactionDtoByAccountId(1L));
        assertThat(serviceException.getMessage()).isEqualTo("Transaction Service Exception . accountId : '1'");
    }
}
