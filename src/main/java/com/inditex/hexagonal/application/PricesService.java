package com.inditex.hexagonal.application;

import com.inditex.hexagonal.application.dto.PriceDto;
import com.inditex.hexagonal.application.dto.PriceRequestDto;
import com.inditex.hexagonal.port.out.db.DbPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class PricesService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DbPort dbPort;


    public List<PriceDto> getFilteredPrices(PriceRequestDto priceRequestDto) {
        return modelMapper.map(dbPort.filterPrices(priceRequestDto.getDate(), priceRequestDto.getProductId(), priceRequestDto.getBrandId()),new TypeToken<List<PriceDto>>() {}.getType());
    }

}
