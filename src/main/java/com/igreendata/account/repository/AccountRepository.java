package com.igreendata.account.repository;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT new com.igreendata.account.dto.AccountDto(a.id,a.accountName,a.currencyType.currency," +
            "a.accountType.accountType,a.balanceDate,a.availableBalance) " +
            "FROM Account a WHERE a.user.id = ?1")
    List<AccountDto> findAccountByUserId(Long userId);


}
