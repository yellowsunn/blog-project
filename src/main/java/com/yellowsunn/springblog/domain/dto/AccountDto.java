package com.yellowsunn.springblog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
public class AccountDto {
    private String username;

    @JsonInclude(NON_EMPTY)
    private String password;

    @JsonInclude(NON_EMPTY)
    private String role;
}
