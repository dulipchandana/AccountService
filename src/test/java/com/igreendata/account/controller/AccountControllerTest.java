package com.igreendata.account.controller;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.entity.TransactionType;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.service.AccountService;
import com.igreendata.account.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    @Test
    void getAccountsByUserIdWithResult() throws Exception {

        AccountDto accountDto1 = new AccountDto(99L, "test1", "AUD", "Savings", "08/03/2023", 5D);
        AccountDto accountDto2 = new AccountDto(100L, "test2", "USD", "Current", "08/03/2023", 6D);
        List<AccountDto> acList = Arrays.asList(accountDto1, accountDto2);
        given(accountService.getAccountsByUserId(1L)).willReturn(acList);
        this.mockMvc.perform(get("/api/users/1/accounts/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[0].accountNumber").value(99))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[0].accountName").value("test1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[0].currency").value("AUD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[0].accountType").value("Savings"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[0].balanceDate").value("08/03/2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[0].availableBalance").value(5.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[0]._links.self.href").value("/api/accounts/99/transactions/"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[1].accountNumber").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[1].accountName").value("test2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[1].currency").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[1].accountType").value("Current"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[1].balanceDate").value("08/03/2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[1].availableBalance").value(6.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.accountDtoList.[1]._links.self.href").value("/api/accounts/100/transactions/"));

        //verify the behavior
        verify(accountService).getAccountsByUserId(1L);


    }

    @Test
    void getAccountsByUserIdWithNoResult() throws Exception {
        given(accountService.getAccountsByUserId(1L)).willThrow(ResourceNotFoundException.class);
        this.mockMvc.perform(get("/api/users/1/accounts/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isNotFound());
        verify(accountService).getAccountsByUserId(1L);

    }

    @Test
    void getAccountsByUserIdWithDbException() throws Exception {
        given(accountService.getAccountsByUserId(1L)).willThrow(ServiceException.class);
        this.mockMvc.perform(get("/api/users/1/accounts/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isServiceUnavailable());
        verify(accountService).getAccountsByUserId(1L);

    }

    @Test
    void getAccountsByUserIdWithIncorrectParameterException() throws Exception {
        this.mockMvc.perform(get("/api/users/l/accounts/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isBadRequest());
        verify(accountService, times(0)).getAccountsByUserId(any());

    }

    @Test
    void getTransactionsByAccountIdWithResult() throws Exception {
        TransactionDto tansactionDto = new TransactionDto(1L, "test", "USD", "Mar.08,2023", 12D, 3D, TransactionType.Credit, "NC", 1L);
        List<TransactionDto> transactionDtoList = List.of(tansactionDto);
        given(transactionService.getTransactionDtoByAccountId(1L)).willReturn(transactionDtoList);
        this.mockMvc.perform(get("/api/accounts/1/transactions/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].accountNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].accountName").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].currency").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].valueDate").value("Mar.08,2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].creditAmount").value(12.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].debitAmount").value(3.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].transactionType").value(TransactionType.Credit.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].transactionNarrative").value("NC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.transactionDtoList.[0].userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href").value("http://localhost/api/users/1/accounts/"));
        verify(transactionService).getTransactionDtoByAccountId(1L);

    }

    @Test
    void getTransactionWithNoResult() throws Exception {
        given(transactionService.getTransactionDtoByAccountId(1L)).willThrow(ResourceNotFoundException.class);
        this.mockMvc.perform(get("/api/accounts/1/transactions/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isNotFound());
        verify(transactionService).getTransactionDtoByAccountId(1L);

    }

    @Test
    void getTransactionWithDbException() throws Exception {
        given(transactionService.getTransactionDtoByAccountId(1L)).willThrow(ServiceException.class);
        this.mockMvc.perform(get("/api/accounts/1/transactions/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isServiceUnavailable());
        verify(transactionService).getTransactionDtoByAccountId(1L);

    }

    @Test
    void getTransactionWithIncorrectParameterException() throws Exception {
        given(transactionService.getTransactionDtoByAccountId(1L)).willThrow(ServiceException.class);
        this.mockMvc.perform(get("/api/accounts/o/transactions/").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isBadRequest());
        verify(transactionService, times(0)).getTransactionDtoByAccountId(any());

    }


}
