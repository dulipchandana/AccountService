package com.igreendata.account.repository;

import com.igreendata.account.dto.TransactionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DataJpaTest
@ActiveProfiles("test")
public class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void findTransactions_if_repository_is_empty() {
        List<TransactionDto> transactionDtoList = transactionRepository.findTransactionByAccountId(7L);
        assertThat(transactionDtoList.isEmpty()).isTrue();
    }


    @Test
    public void findTransaction_with_Data() {
        List<TransactionDto> transactionDtoList = transactionRepository.findTransactionByAccountId(2L);
        assertThat(transactionDtoList.size()).isEqualTo(1);
        assertThat(transactionDtoList.stream().anyMatch(b -> (b.getAccountNumber() == 2D) &&
                b.getAccountName().equals("SGSavings2334") &&
                b.getCurrency().equals("SGD") &&
                b.getCreditAmount().equals(4545.09) &&
                (b.getUserId() == 2D))).isTrue();

    }
}
