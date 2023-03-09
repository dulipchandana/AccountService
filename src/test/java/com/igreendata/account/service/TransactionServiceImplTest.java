package com.igreendata.account.service;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.entity.Transaction;
import com.igreendata.account.entity.TransactionType;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.mapper.TransactionMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionService;
    @Mock
    TransactionRepository transactionRepository;

    @Mock
    TransactionMapper transactionMapper;


    @Test
    void getTransactionsByAccountIdWithResult() throws Exception {
        TransactionDto transactionDto = TransactionDto.builder().accountNumber(1L)
                .accountName("test").currency("USD").creditAmount(12D).build();
        List<TransactionDto> transactionDtoList = List.of(transactionDto);
        Transaction transaction = Transaction.builder().build();
        List<Transaction> transactions = List.of(transaction);
        given(transactionRepository.findByAccount_Id(1L)).willReturn(transactions);
        given(transactionMapper.transactionToTransactionDto(transaction)).willReturn(transactionDto);
        assertThat(transactionService.getTransactionDtoByAccountId(1L)).isEqualTo(transactionDtoList);
        verify(transactionRepository).findByAccount_Id(1L);
        verify(transactionMapper).transactionToTransactionDto(transaction);
    }

    @Test
    void getTransactionWithNoResult() throws Exception {
        given(transactionRepository.findByAccount_Id(1L)).willReturn(new ArrayList());
        ResourceNotFoundException exception =
                assertThrows(ResourceNotFoundException.class, () -> transactionService.getTransactionDtoByAccountId(1L));
        assertThat(exception.getMessage()).isEqualTo("Transaction not found with accountId : '1'");

    }

    @Test
    void getTransactionWithDbException() throws Exception {
        given(transactionRepository.findByAccount_Id(1L)).willThrow(QueryTimeoutException.class);
        ServiceException serviceException =
                assertThrows(ServiceException.class, () -> transactionService.getTransactionDtoByAccountId(1L));
        assertThat(serviceException.getMessage()).isEqualTo("Transaction Service Exception . accountId : '1'");
    }
}
