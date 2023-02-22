package com.inditex.hexagonal.port.out.db;

import com.inditex.hexagonal.adapter.out.db.model.PricesEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DbPort {
//
//  void upsertMatch(TennisMatchDTO match);
//
//  void updateStatus(String matchId, String status);

    @Query(value = "SELECT * FROM prices t WHERE  ?1 BETWEEN t.start_date AND t.end_date AND t.product_ID = ?2 AND t.brand_id = ?3",
        nativeQuery = true)
    List<PricesEntity> filterPrices(LocalDateTime date, String productId, int brandId);

}
