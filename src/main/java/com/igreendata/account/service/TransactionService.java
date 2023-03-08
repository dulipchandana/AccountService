package com.igreendata.account.service;

import com.igreendata.account.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> getTransactionDtoByAccountId(final Long accountId);
}
