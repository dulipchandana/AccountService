package com.igreendata.account.dto;

import com.igreendata.account.entity.TransactionType;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TransactionDto hold values for /transactions/ api.
 *
 * @author Dulip Chandana
 */
public class TransactionDto extends RepresentationModel<TransactionDto> {

    private final SimpleDateFormat dateFormat
            = new SimpleDateFormat("MMM.dd,yyyy");

    @Getter
    private final Long accountNumber;

    @Getter
    private final String accountName;

    @Getter
    private final String currency;
    @Getter
    private final Double creditAmount;
    @Getter
    private final Double debitAmount;
    @Getter
    private final TransactionType transactionType;
    @Getter
    private final String transactionNarrative;
    @Getter
    private final Long userId;
    @Getter
    private String valueDate;

    /**
     * TransactionDto constructor
     *
     * @param accountNumber
     * @param accountName
     * @param currency
     * @param valueDate
     * @param creditAmount
     * @param debitAmount
     * @param transactionType
     * @param transactionNarrative
     */
    public TransactionDto(final Long accountNumber, final String accountName, final String currency,
                          final Date valueDate, final Double creditAmount, final Double debitAmount,
                          final TransactionType transactionType, final String transactionNarrative, final Long userId) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.currency = currency;
        this.setValueDate(valueDate);
        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
        this.transactionType = transactionType;
        this.transactionNarrative = transactionNarrative;
        this.userId = userId;
    }

    /**
     * Set Value date with date for mat
     *
     * @param valueDate
     */
    public void setValueDate(final Date valueDate) {
        this.valueDate = dateFormat.format(valueDate);
    }
}
