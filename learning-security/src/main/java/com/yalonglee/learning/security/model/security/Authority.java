package com.yalonglee.learning.security.model.security;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    private Long id;

    private AuthorityName name;

}