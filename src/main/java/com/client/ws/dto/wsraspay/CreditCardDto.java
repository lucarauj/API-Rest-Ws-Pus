package com.client.ws.dto.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDto {

    private Long cvv;
    private String documentNumber;
    private Long installments;
    private Long month;
    private String number;
    private Long year;
}
