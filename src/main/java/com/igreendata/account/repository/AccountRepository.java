package com.igreendata.account.repository;

import com.igreendata.account.dto.BaseDto;
import com.igreendata.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * AccountRepository for define query and db functions .
 *
 * @author Dulip Chandana
 */
@Repository
public interface AccountRepository<T extends BaseDto> extends JpaRepository<Account, Long> {

    /**
     * Get accounts by userId
     *
     * @param userId
     * @param pageable
     * @return AccountDto
     */
    @Query("SELECT new com.igreendata.account.dto.AccountDto(a.id,a.accountName,a.currencyType.currency," +
            "a.accountType.accountType,a.balanceDate,a.availableBalance) " +
            "FROM Account a WHERE a.user.id = ?1")
    Page<T> findAccountByUserId(Long userId, Pageable pageable);

    /**
     * Get transactions for given account
     * @param accountId
     * @param pageable
     * @return TransactionDto
     */
    @Query("SELECT new com.igreendata.account.dto.TransactionDto(t.account.id,t.account.accountName," +
            "t.account.currencyType.currency,t.valueDate,t.creditAmount,t.debitAmount,t.transactionType,t.transactionNarrative) " +
            "FROM Transaction t WHERE t.account.id = ?1")
    Page<T> findAccountAccountId(Long accountId, Pageable pageable);


}
