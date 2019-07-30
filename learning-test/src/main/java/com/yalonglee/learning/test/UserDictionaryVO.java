package com.yalonglee.learning.test;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDictionaryVO extends UserDictionary {

    private Long id;

    private String username;

    private String email;

}
