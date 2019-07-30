package com.yalonglee.learning.test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonSerialize(using = VoSerializer.class)
public class Account extends AccountDictionary {

    private String accountAddr;

    private String password;

}
