package com.inditex.hexagonal.adapter.out.db;

import com.inditex.hexagonal.adapter.out.db.model.PricesEntity;
import com.inditex.hexagonal.adapter.out.db.repository.PricesRepository;
import com.inditex.hexagonal.port.out.db.DbPort;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DbAdapter implements DbPort {

  @Autowired
  private PricesRepository pricesRepository;

  @Autowired
  private ModelMapper modelMapper;



    @Override
    public List<PricesEntity> filterPrices(LocalDateTime date, String productId, int brandId) {
        List<PricesEntity> pe = pricesRepository.filterPrices(date,productId,brandId);
         return modelMapper.map(pe,new TypeToken<List<PricesEntity>>() {}.getType());
    }
}
