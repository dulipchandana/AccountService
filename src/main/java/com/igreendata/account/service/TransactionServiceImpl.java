package com.igreendata.account.service;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.exception.ResourceNotFoundException;
import com.igreendata.account.exception.ServiceException;
import com.igreendata.account.repository.TransactionRepository;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;

    /**
     * Get TransactionDto list with filtering accountId
     *
     * @param accountId - Long account id
     * @return List<TransactionDto> - transaction id
     */
    @Override
    public List<TransactionDto> getTransactionDtoByAccountId(final Long accountId) {

        try {
            List<TransactionDto> transactionDtoResults = transactionRepository.findTransactionByAccountId
                    (accountId);
            if (!transactionDtoResults.isEmpty()) {
                return transactionDtoResults;
            } else {
                throw new ResourceNotFoundException("Transaction", "accountId", accountId);
            }
        } catch (ResourceNotFoundException re) {
            throw re;
        } catch (Exception ex) {
            throw new ServiceException("Transaction", "accountId", accountId);

        }
    }
}
