package com.igreendata.account.mapper;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.entity.Transaction;
import com.igreendata.account.entity.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransactionMapperTest {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    @InjectMocks
    private TransactionMapperImpl transactionMapper;

    @Test
    void test_transactionToTransactionDto_with_values() throws ParseException {
        Transaction transaction = Transaction.builder()
                .valueDate(formatter.parse("08/03/2023"))
                .creditAmount(12.4)
                .debitAmount(0.0)
                .transactionType(TransactionType.Credit)
                .transactionNarrative("test")
                .build();
        TransactionDto transactionDto = transactionMapper.transactionToTransactionDto(transaction);
        assertThat(transactionDto.getCreditAmount()).isEqualTo(12.4);
        assertThat(transactionDto.getDebitAmount()).isEqualTo(0.0);
        assertThat(transactionDto.getTransactionType()).isEqualTo(TransactionType.Credit);
        assertThat(transactionDto.getTransactionNarrative()).isEqualTo("test");

    }

    @Test
    void test_transactionToTransactionDto_with_null_values() {
        TransactionDto transactionDto = transactionMapper.transactionToTransactionDto(Transaction.builder().build());
        assertNull(transactionDto.getCreditAmount());
        assertNull(transactionDto.getDebitAmount());
        assertNull(transactionDto.getTransactionType());
        assertNull(transactionDto.getTransactionNarrative());
    }
}
