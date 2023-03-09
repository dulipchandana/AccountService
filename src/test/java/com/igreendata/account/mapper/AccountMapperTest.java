package com.igreendata.account.mapper;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.entity.Account;
import com.igreendata.account.entity.AccountType;
import com.igreendata.account.entity.CurrencyType;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountMapperTest {

    static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    @InjectMocks
    AccountMapperImpl accountMapper;

    @Test
    void test_account_to_AccountDto_with_data() throws ParseException {
        Account account = Account.builder().id(1L)
                .accountName("testName").currencyType
                        (CurrencyType.builder().currency("AUD").build())
                .accountType(AccountType.builder().accountType("Savings").build())
                .balanceDate(formatter.parse("08/03/2023"))
                .availableBalance(12.3)
                .build();
        AccountDto accountDto = accountMapper.accountToAccountDto(account);
        assertEquals(1L, (long) accountDto.getAccountNumber());
        assertThat(accountDto.getAccountName()).isEqualTo("testName");
        assertThat(accountDto.getCurrency()).isEqualTo("AUD");
        assertThat(accountDto.getAccountType()).isEqualTo("Savings");
        assertThat(accountDto.getBalanceDate()).isEqualTo("08/03/2023");
        assertThat(accountDto.getLinks().stream().findFirst().get().getHref())
                .isEqualTo("/api/accounts/1/transactions/");
    }

    @Test
    void test_account_to_AccountDto_with_empty_data() {

        AccountDto accountDto = accountMapper.accountToAccountDto(Account.builder().build());
        assertNull(accountDto.getAccountNumber());
        assertNull(accountDto.getAccountName());
        assertNull(accountDto.getAccountType());
        assertNull(accountDto.getBalanceDate());
        assertNull(accountDto.getCurrency());

    }


}
