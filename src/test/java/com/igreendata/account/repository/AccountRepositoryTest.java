package com.igreendata.account.repository;

import com.igreendata.account.dto.AccountDto;
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
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository<AccountDto> accountRepository;

    @Autowired
    private AccountRepository<TransactionDto> transactionRepository;

    @Test
    public void findAccountByUserId_if_repository_is_empty() {
        List<AccountDto> accountDtos = accountRepository.findAccountByUserId(5L);
        assertThat(accountDtos.isEmpty()).isTrue();
    }

    @Test
    public void findTransactions_if_repository_is_empty() {
        List<TransactionDto> transactionDtoList = transactionRepository.findAccountAccountId(7L);
        assertThat(transactionDtoList.isEmpty()).isTrue();
    }

    @Test
    public void findAccountByUserId_with_Data() {
        List<AccountDto> accountDtos = accountRepository.findAccountByUserId(1L);
        assertThat(accountDtos.size()).isEqualTo(2);
        assertThat(accountDtos.stream().anyMatch(a -> ((a.getAccountNumber() == 1D) &&
                a.getAvailableBalance().equals(12.34) &&
                a.getAccountName().equals("AUSavings23") &&
                a.getCurrency().equals("AUD") ||
                (a.getAccountNumber() == 3D) &&
                        a.getAvailableBalance().equals(1234) &&
                        a.getAccountName().equals("USSavings33") &&
                        a.getCurrency().equals("USD"))
        )).isTrue();

    }

    @Test
    public void findTransaction_with_Data() {
        List<TransactionDto> transactionDtoList = transactionRepository.findAccountAccountId(2L);
        assertThat(transactionDtoList.size()).isEqualTo(1);
        assertThat(transactionDtoList.stream().anyMatch(b -> (b.getAccountNumber() == 2D) &&
                b.getAccountName().equals("SGSavings2334") &&
                b.getCurrency().equals("SGD") &&
                b.getCreditAmount().equals(4545.09) &&
                (b.getUserId() == 2D))).isTrue();

    }
}
