package com.igreendata.account.repository;

import com.igreendata.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountRepository for define query and db functions .
 *
 * @author Dulip Chandana
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Get accounts by userId
     *
     * @param userId
     * @return AccountDto
     */
    List<Account> findByUser_Id(Long userId);


}
