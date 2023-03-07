package com.igreendata.account.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * BankService interface to Bank services  .
 * @author Dulip Chandana
 *
 */
public interface BankService<T> {

    List<T> getDtoById(final Long id);

}
