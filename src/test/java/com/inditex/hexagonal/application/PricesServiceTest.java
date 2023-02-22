package com.inditex.hexagonal.application;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.inditex.hexagonal.application.dto.PriceDto;
import com.inditex.hexagonal.application.dto.PriceRequestDto;
import com.inditex.hexagonal.boot.HexagonalApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = HexagonalApplication.class, webEnvironment = RANDOM_PORT)
class PricesServiceTest {

    private static DateTimeFormatter formatter;
    private static LocalDateTimeDeserializer dateTimeDeserializer;
    private static LocalDateTimeSerializer dateTimeSerializer;
    private static JavaTimeModule javaTimeModule;
    @Autowired
    PricesService service;
    private LocalDateTime dateTime;

    @BeforeAll
    static void setup() throws Exception {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
        dateTimeSerializer = new LocalDateTimeSerializer(formatter);
        javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);
    }

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del Priceo 35455   para la brand 1 (ZARA)")
    void test1() {

        //given
        PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setDate(LocalDateTime.parse("2020-06-14 10:00:00", formatter));
        priceRequestDto.setProductId("35455");
        priceRequestDto.setBrandId(1);

        //when
        List<PriceDto> results = service.getFilteredPrices(priceRequestDto);
        //then
        assertEquals(1, results.get(0).getBRAND_ID());
        assertEquals(35455, results.get(0).getProduct_ID());
        assertEquals(new BigDecimal("35.50"), results.get(0).getPRICE());
        assertEquals(1, results.get(0).getPRICE_LIST());
        assertEquals("EUR", results.get(0).getCURR());
        assertEquals(0, results.get(0).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-14 00:00:00", formatter), results.get(0).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-12-31 23:59:59", formatter), results.get(0).getEND_DATE());

    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del Priceo 35455   para la brand 1 (ZARA)")
    void test2() {
        //given
        PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setDate(LocalDateTime.parse("2020-06-14 16:00:00", formatter));
        priceRequestDto.setProductId("35455");
        priceRequestDto.setBrandId(1);

        //when
        List<PriceDto> results = service.getFilteredPrices(priceRequestDto);
        //then
        assertEquals(1, results.get(0).getBRAND_ID());
        assertEquals(35455, results.get(0).getProduct_ID());
        assertEquals(new BigDecimal("35.50"), results.get(0).getPRICE());
        assertEquals(1, results.get(0).getPRICE_LIST());
        assertEquals("EUR", results.get(0).getCURR());
        assertEquals(0, results.get(0).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-14 00:00:00", formatter), results.get(0).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-12-31 23:59:59", formatter), results.get(0).getEND_DATE());

        assertEquals(1, results.get(1).getBRAND_ID());
        assertEquals(35455, results.get(1).getProduct_ID());
        assertEquals(new BigDecimal("25.45"), results.get(1).getPRICE());
        assertEquals(2, results.get(1).getPRICE_LIST());
        assertEquals("EUR", results.get(1).getCURR());
        assertEquals(1, results.get(1).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-14 15:00:00", formatter), results.get(1).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-06-14 18:30:00", formatter), results.get(1).getEND_DATE());

    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del Priceo 35455   para la brand 1 (ZARA)")
    void test3() {
        //given
        PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setDate(LocalDateTime.parse("2020-06-14 21:00:00", formatter));
        priceRequestDto.setProductId("35455");
        priceRequestDto.setBrandId(1);

        //when
        List<PriceDto> results = service.getFilteredPrices(priceRequestDto);
        //then
        assertEquals(1, results.get(0).getBRAND_ID());
        assertEquals(35455, results.get(0).getProduct_ID());
        assertEquals(new BigDecimal("35.50"), results.get(0).getPRICE());
        assertEquals(1, results.get(0).getPRICE_LIST());
        assertEquals("EUR", results.get(0).getCURR());
        assertEquals(0, results.get(0).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-14 00:00:00", formatter), results.get(0).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-12-31 23:59:59", formatter), results.get(0).getEND_DATE());
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del Priceo 35455   para la brand 1 (ZARA)")
    void test4() {
        //given
        PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setDate(LocalDateTime.parse("2020-06-15 10:00:00", formatter));
        priceRequestDto.setProductId("35455");
        priceRequestDto.setBrandId(1);

        //when
        List<PriceDto> results = service.getFilteredPrices(priceRequestDto);
        //then
        assertEquals(1, results.get(0).getBRAND_ID());
        assertEquals(35455, results.get(0).getProduct_ID());
        assertEquals(new BigDecimal("35.50"), results.get(0).getPRICE());
        assertEquals(1, results.get(0).getPRICE_LIST());
        assertEquals("EUR", results.get(0).getCURR());
        assertEquals(0, results.get(0).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-14 00:00:00", formatter), results.get(0).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-12-31 23:59:59", formatter), results.get(0).getEND_DATE());

        assertEquals(1, results.get(1).getBRAND_ID());
        assertEquals(35455, results.get(1).getProduct_ID());
        assertEquals(new BigDecimal("30.50"), results.get(1).getPRICE());
        assertEquals(3, results.get(1).getPRICE_LIST());
        assertEquals("EUR", results.get(1).getCURR());
        assertEquals(1, results.get(1).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-15 00:00:00", formatter), results.get(1).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-06-15 11:00:00", formatter), results.get(1).getEND_DATE());
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del Priceo 35455   para la brand 1 (ZARA)")
    void test5() {
        //given
        PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setDate(LocalDateTime.parse("2020-06-15 21:00:00", formatter));
        priceRequestDto.setProductId("35455");
        priceRequestDto.setBrandId(1);

        //when
        List<PriceDto> results = service.getFilteredPrices(priceRequestDto);
        //then
        assertEquals(1, results.get(0).getBRAND_ID());
        assertEquals(35455, results.get(0).getProduct_ID());
        assertEquals(new BigDecimal("35.50"), results.get(0).getPRICE());
        assertEquals(1, results.get(0).getPRICE_LIST());
        assertEquals("EUR", results.get(0).getCURR());
        assertEquals(0, results.get(0).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-14 00:00:00", formatter), results.get(0).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-12-31 23:59:59", formatter), results.get(0).getEND_DATE());

        assertEquals(1, results.get(1).getBRAND_ID());
        assertEquals(35455, results.get(1).getProduct_ID());
        assertEquals(new BigDecimal("38.95"), results.get(1).getPRICE());
        assertEquals(4, results.get(1).getPRICE_LIST());
        assertEquals("EUR", results.get(1).getCURR());
        assertEquals(1, results.get(1).getPRIORITY());
        assertEquals(LocalDateTime.parse("2020-06-15 16:00:00", formatter), results.get(1).getSTART_DATE());
        assertEquals(LocalDateTime.parse("2020-12-31 23:59:59", formatter), results.get(1).getEND_DATE());
    }
}
