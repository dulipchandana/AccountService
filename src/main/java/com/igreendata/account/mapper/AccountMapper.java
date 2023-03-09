package com.igreendata.account.mapper;

import com.igreendata.account.dto.AccountDto;
import com.igreendata.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountNumber", source = "account.id")
    @Mapping(target = "accountName", source = "account.accountName")
    @Mapping(target = "currency", source = "account.currencyType.currency")
    @Mapping(target = "accountType", source = "account.accountType.accountType")
    @Mapping(target = "balanceDate", source = "account.balanceDate",
            dateFormat = "dd/MM/yyyy")
    @Mapping(target = "availableBalance", source = "account.availableBalance")
    AccountDto accountToAccountDto(Account account);
}
