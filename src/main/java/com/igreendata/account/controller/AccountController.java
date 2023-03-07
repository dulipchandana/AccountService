package com.igreendata.account.controller;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.service.BankService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AccountController {

    @Autowired
    @Qualifier("com.igreendata.account.service.AccountServiceImpl")
    private BankService<AccountDto> accountService;

    @Autowired
    @Qualifier("com.igreendata.account.service.TransactionServiceImpl")
    private BankService<TransactionDto> transactionService;


    /**
     * Get Account dto list with pagination parameters
     *
     * @param userId
     * @return Page<AccountDto>
     */
    @GetMapping(value = "/accounts/{userId}", produces = {"application/hal+json"})
    public CollectionModel<AccountDto> getAccountsByUserId(@PathVariable(value = "userId") final Long userId) {

        List<AccountDto> accountDtos = accountService.getDtoById(userId);

        return new CollectionModel<AccountDto>(accountDtos);
    }

    /**
     * Get transactionDto list with pagination parameters
     *
     * @param accountId
     * @return Page<TransactionDto>
     */
    @GetMapping(value = "transactions/{accountId}", produces = {"application/hal+json"})
    public CollectionModel<TransactionDto> getTransactionsByAccountId(@PathVariable(value = "accountId") final Long accountId) {

        List<TransactionDto> transactionDtos = transactionService.getDtoById(accountId);
        Link link = linkTo(methodOn(AccountController.class).getAccountsByUserId
                (transactionDtos.stream().findFirst().get().getUserId())).withSelfRel();
        return new CollectionModel<>(transactionDtos, link);
    }
}
