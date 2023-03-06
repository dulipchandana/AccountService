package com.igreendata.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * BaseDto hold common values for /transactions/ and /accounts/ api.
 *
 * @author Dulip Chandana
 */

@AllArgsConstructor
public class BaseDto {

    @Getter
    private Long accountNumber;

    @Getter
    private String accountName;

    @Getter
    private String currency;

}
