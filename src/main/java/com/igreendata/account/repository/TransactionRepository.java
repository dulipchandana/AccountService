package com.igreendata.account.repository;

import com.igreendata.account.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<Transaction> findByAccount_Id(Long accountId);

}
