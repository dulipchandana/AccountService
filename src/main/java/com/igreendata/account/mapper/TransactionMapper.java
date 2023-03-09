package com.igreendata.account.mapper;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target="accountNumber", source="transaction.account.id")
    @Mapping(target="accountName", source="transaction.account.accountName")
    @Mapping(target="currency", source="transaction.account.currencyType.currency")
    @Mapping(target="valueDate", source="transaction.valueDate" ,
            dateFormat="MMM.dd,yyyy")
    @Mapping(target="creditAmount", source="transaction.creditAmount")
    @Mapping(target="debitAmount", source="transaction.debitAmount")
    @Mapping(target="transactionType", source="transaction.transactionType")
    @Mapping(target="transactionNarrative", source="transaction.transactionNarrative")
    @Mapping(target="userId", source="transaction.account.user.id")
    TransactionDto transactionToTransactionDto (Transaction transaction);
}
