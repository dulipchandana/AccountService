package com.igreendata.account.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * BankService interface to Bank services  .
 * @author Dulip Chandana
 *
 */
public interface BankService<T> {

    Page<T> getDtoById(final Long id, final Pageable pageable);

}
