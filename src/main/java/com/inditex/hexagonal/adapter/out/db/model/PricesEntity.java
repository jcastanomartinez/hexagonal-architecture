package com.inditex.hexagonal.adapter.out.db.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="prices")
public class PricesEntity {
    private Long BRAND_ID;

    private LocalDateTime START_DATE;
    private LocalDateTime END_DATE;
    @Id
    private Long PRICE_LIST;

    private Long product_ID;

    private Long PRIORITY;
    private BigDecimal PRICE;

    private String CURR;

    public Long getBRAND_ID() {
        return BRAND_ID;
    }

    public void setBRAND_ID(Long BRAND_ID) {
        this.BRAND_ID = BRAND_ID;
    }

    public LocalDateTime getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(LocalDateTime START_DATE) {
        this.START_DATE = START_DATE;
    }

    public LocalDateTime getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(LocalDateTime END_DATE) {
        this.END_DATE = END_DATE;
    }

    public Long getPRICE_LIST() {
        return PRICE_LIST;
    }

    public void setPRICE_LIST(Long PRICE_LIST) {
        this.PRICE_LIST = PRICE_LIST;
    }

    public Long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(Long product_ID) {
        this.product_ID = product_ID;
    }

    public Long getPRIORITY() {
        return PRIORITY;
    }

    public void setPRIORITY(Long PRIORITY) {
        this.PRIORITY = PRIORITY;
    }

    public BigDecimal getPRICE() {
        return PRICE;
    }

    public void setPRICE(BigDecimal PRICE) {
        this.PRICE = PRICE;
    }

    public String getCURR() {
        return CURR;
    }

    public void setCURR(String CURR) {
        this.CURR = CURR;
    }
}
