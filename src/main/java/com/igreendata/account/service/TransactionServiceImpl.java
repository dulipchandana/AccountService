package com.igreendata.account.service;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.exception.IncorrectParameterException;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * TransactionServiceImpl service implementations for transactions  .
 *
 * @author Dulip Chandana
 */
@Service
@Qualifier("com.igreendata.account.service.TransactionServiceImpl")
public class TransactionServiceImpl implements BankService<TransactionDto> {

    @Autowired
    AccountRepository<TransactionDto> transactionRepository;

    /**
     * Get TransactionDto page list with filtering accountId
     *
     * @param accountId
     * @param pageable
     * @return
     */
    @Override
    public Page<TransactionDto> getDtoById(final Long accountId, final Pageable pageable) {

        try {
            Page<TransactionDto> transactionDtoResults = transactionRepository.findAccountAccountId
                    (accountId, pageable);
            if (!transactionDtoResults.isEmpty()) {
                return transactionDtoResults;
            } else {
                throw new ResourceNotFoundException("Transaction", "accountId", accountId);
            }

        } catch (InvalidDataAccessApiUsageException invalidDataAccessApiUsageException) {
            throw new IncorrectParameterException("Transaction", "Sort", pageable.getSort());

        }
    }
}
