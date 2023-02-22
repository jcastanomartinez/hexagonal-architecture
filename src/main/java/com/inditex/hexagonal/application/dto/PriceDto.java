package com.inditex.hexagonal.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PriceDto {
    private Long BRAND_ID;

    private LocalDateTime START_DATE;
    private LocalDateTime END_DATE;

    private Long PRICE_LIST;

    private Long product_ID;

    private Long PRIORITY;
    private BigDecimal PRICE;

    private String CURR;
}
