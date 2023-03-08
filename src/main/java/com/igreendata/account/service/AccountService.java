package com.igreendata.account.service;

import com.igreendata.account.dto.AccountDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAccountsByUserId(final Long id);
}
