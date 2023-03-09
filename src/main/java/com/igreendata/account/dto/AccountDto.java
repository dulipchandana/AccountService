package com.igreendata.account.dto;

import com.igreendata.account.controller.AccountController;
import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.text.SimpleDateFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * AccountDto hold values for /accounts/ api.
 *
 * @author Dulip Chandana
 */

public class AccountDto extends RepresentationModel<AccountDto> {

    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd/MM/yyyy");

    @Getter
    private final Long accountNumber;

    @Getter
    private final String accountName;

    @Getter
    private final String currency;

    @Getter
    private final String accountType;
    @Getter
    private final Double availableBalance;
    @Getter
    private final String balanceDate;

    /**
     * AccountDto constructor
     *
     * @param accountNumber
     * @param accountName
     * @param currency
     * @param accountType
     * @param balanceDate
     * @param availableBalance
     */
    public AccountDto(final Long accountNumber, final String accountName, final String currency,
                      final String accountType, final String balanceDate, final Double availableBalance) {
        this.accountType = accountType;
        this.availableBalance = availableBalance;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.currency = currency;
        this.add(setHateoas(accountNumber));
        this.balanceDate = balanceDate;

    }

    private Link setHateoas(final Long accountNumber) {
        return linkTo(methodOn(AccountController.class)
                .getTransactionsByAccountId(accountNumber)).withSelfRel();
    }

}
