package com.yalonglee.learning.core.common;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SelectBox {

    /**
     * key
     */
    private Object label;

    /**
     * value
     */
    private String value;
}
