package com.igreendata.account.service;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @return
     */
    @Override
    public List<TransactionDto> getDtoById(final Long accountId) {

        try {
            List<TransactionDto> transactionDtoResults = transactionRepository.findAccountAccountId
                    (accountId);
            if (!transactionDtoResults.isEmpty()) {
                return transactionDtoResults;
            } else {
                throw new ResourceNotFoundException("Transaction", "accountId", accountId);
            }
        } catch (ResourceNotFoundException re){
            throw re;
        } catch (Exception ex) {
            throw new ServiceException("Transaction", "Sort", accountId);

        }
    }
}
