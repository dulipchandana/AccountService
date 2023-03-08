package com.igreendata.account.repository;

import com.igreendata.account.dto.TransactionDto;
import com.igreendata.account.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Get transactions for given account
     *
     * @param accountId
     * @return TransactionDto
     */
    @Query("SELECT new com.igreendata.account.dto.TransactionDto(t.account.id,t.account.accountName," +
            "t.account.currencyType.currency,t.valueDate,t.creditAmount,t.debitAmount,t.transactionType,t.transactionNarrative,t.account.user.id) " +
            "FROM Transaction t WHERE t.account.id = ?1")
    List<TransactionDto> findTransactionByAccountId(Long accountId);

}
