package com.igreendata.account.controller;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.service.AccountService;
import com.igreendata.account.service.TransactionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * AccountController for handle /accounts/ and /transactions/ api .
 *
 * @author Dulip Chandana
 */
@RestController
@RequestMapping("/api")
@Api(value = "accountServices", description = "Bank service in Account Department.")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final TransactionService transactionService;


    /**
     * Get Account dto list with pagination parameters
     *
     * @param userId
     * @return Page<AccountDto>
     */
    @GetMapping(value = "users/{userId}/accounts/", produces = {MediaTypes.HAL_JSON_VALUE})
    public CollectionModel<List<AccountDto>> getAccountsByUserId(@PathVariable(value = "userId") final Long userId) {

        List<AccountDto> accountDtos = accountService.getAccountsByUserId(userId);

        return new CollectionModel<List<AccountDto>>(Collections.singleton(accountDtos));
    }

    /**
     * Get transactionDto list with pagination parameters
     *
     * @param accountId
     * @return Page<TransactionDto>
     */
    @GetMapping(value = "accounts/{accountId}/transactions/", produces = {MediaTypes.HAL_JSON_VALUE})
    public CollectionModel<List<TransactionDto>> getTransactionsByAccountId(@PathVariable(value = "accountId") final Long accountId) {

        List<TransactionDto> transactionDtos = transactionService.getTransactionDtoByAccountId(accountId);
        Link link = linkTo(methodOn(AccountController.class).getAccountsByUserId
                (transactionDtos.stream().findFirst().get().getUserId())).withSelfRel();
        return new CollectionModel<List<TransactionDto>>(Collections.singleton(transactionDtos), link);
    }
}
