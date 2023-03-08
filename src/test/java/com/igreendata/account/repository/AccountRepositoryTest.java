package com.igreendata.account.repository;

import com.igreendata.account.dto.AccountDto;
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
    private AccountRepository accountRepository;


    @Test
    public void findAccountByUserId_if_repository_is_empty() {
        List<AccountDto> accountDtoList = accountRepository.findAccountByUserId(5L);
        assertThat(accountDtoList.isEmpty()).isTrue();
    }

    @Test
    public void findAccountByUserId_with_Data() {
        List<AccountDto> accountDtos = accountRepository.findAccountByUserId(1L);
        assertThat(accountDtos.size()).isEqualTo(2);
        assertThat(accountDtos.stream().anyMatch(a -> ((a.getAccountNumber() == 1D) &&
                a.getAvailableBalance().equals(12.34) &&
                a.getAccountName().equals("AUSavings23") &&
                a.getCurrency().equals("AUD") &&
                a.getLinks().stream().findFirst().get().getHref().equals("/api/accounts/1/transactions/")||
                (a.getAccountNumber() == 3D) &&
                        a.getAvailableBalance().equals(1234) &&
                        a.getAccountName().equals("USSavings33") &&
                        a.getCurrency().equals("USD") &&
                        a.getLinks().stream().findFirst().get().getHref().equals("/api/accounts/3/transactions/"))
        )).isTrue();

    }
}
