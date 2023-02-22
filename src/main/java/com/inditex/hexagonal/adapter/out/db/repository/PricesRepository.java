package com.inditex.hexagonal.adapter.out.db.repository;

import com.inditex.hexagonal.adapter.out.db.model.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {
@Query(value = "SELECT * FROM prices t WHERE  ?1 BETWEEN t.start_date AND t.end_date AND t.product_ID = ?2 AND t.brand_id = ?3",
    nativeQuery = true)
List<PricesEntity> filterPrices(LocalDateTime date, String productId, int brandId);
}
