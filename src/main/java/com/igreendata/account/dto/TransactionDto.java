package com.igreendata.account.dto;

import com.igreendata.account.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * TransactionDto hold values for /transactions/ api.
 *
 * @author Dulip Chandana
 */
@AllArgsConstructor
@Builder
public class TransactionDto extends RepresentationModel<TransactionDto> {
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
    private final String valueDate;

}
