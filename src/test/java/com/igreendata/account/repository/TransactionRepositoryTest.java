package com.igreendata.account.repository;

import com.igreendata.account.entity.Transaction;
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
class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void findTransactions_if_repository_is_empty() {
        List<Transaction> transactions = transactionRepository.findByAccount_Id(7L);
        assertThat(transactions).isEmpty();
    }

    @Test
    void findTransaction_with_Data() {
        List<Transaction> transactions = transactionRepository.findByAccount_Id(2L);
        assertThat(transactions).hasSize(1);
        assertThat(transactions.stream().anyMatch(b -> (b.getAccount().getId() == 2D) &&
                b.getAccount().getAccountName().equals("SGSavings2334") &&
                b.getAccount().getCurrencyType().getCurrency().equals("SGD") &&
                b.getCreditAmount().equals(4545.09) &&
                (b.getAccount().getUser().getId() == 2D))).isTrue();

    }
}
