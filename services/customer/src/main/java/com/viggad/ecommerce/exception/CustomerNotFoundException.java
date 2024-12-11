package com.viggad.ecommerce.exception;

import lombok.*;

@EqualsAndHashCode(callSuper=true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerNotFoundException extends RuntimeException {
    private String msg;
}
