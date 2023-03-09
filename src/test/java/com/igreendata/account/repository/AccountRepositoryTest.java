package com.igreendata.account.repository;

import com.igreendata.account.entity.Account;
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
class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    void findAccountByUserId_if_repository_is_empty() {
        List<Account> accounts = accountRepository.findByUser_Id(5L);
        assertThat(accounts).isEmpty();
    }

    @Test
    void findAccountByUserId_with_Data() {
        List<Account> accounts = accountRepository.findByUser_Id(1L);
        assertThat(accounts).hasSize(2);
        assertThat(accounts.stream().anyMatch(a -> ((a.getId() == 1D) &&
                a.getAvailableBalance().equals(12.34) &&
                a.getAccountName().equals("AUSavings23") &&
                a.getCurrencyType().getCurrency().equals("AUD") ||
                (a.getId() == 3D) &&
                        a.getAvailableBalance().equals(1234) &&
                        a.getAccountName().equals("USSavings33") &&
                        a.getCurrencyType().getCurrency().equals("USD"))
        )).isTrue();

    }
}
