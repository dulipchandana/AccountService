package com.igreendata.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * AccountDto hold values for /accounts/ api.
 *
 * @author Dulip Chandana
 */

public class AccountDto extends BaseDto {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd/MM/YYYY");

    @Getter
    private final String accountType;

    @Getter
    private String balanceDate;

    @Getter
    private final Double availableBalance;

    public void setBalanceDate(final Date balanceDate) {
        this.balanceDate = dateFormat.format(balanceDate);
    }

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
                      final String accountType, final Date balanceDate, final Double availableBalance) {
        super(accountNumber, accountName, currency);
        this.accountType = accountType;
        this.availableBalance = availableBalance;
        setBalanceDate(balanceDate);


    }


}
