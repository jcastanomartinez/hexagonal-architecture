package com.inditex.hexagonal.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.inditex.hexagonal.application.dto.PriceRequestDto;
import com.inditex.hexagonal.boot.HexagonalApplication;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = HexagonalApplication.class, webEnvironment = RANDOM_PORT)
class WebControllerTest {

    private static ObjectMapper objectMapper;
    private static DateTimeFormatter formatter;

    private static LocalDateTimeDeserializer dateTimeDeserializer;
    private static LocalDateTimeSerializer dateTimeSerializer;

    private static JavaTimeModule javaTimeModule;
    private LocalDateTime dateTime;

    @Autowired
    private WebTestClient client;

    @BeforeAll
    static void setup() throws Exception {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTimeDeserializer = new LocalDateTimeDeserializer(formatter);
        dateTimeSerializer = new LocalDateTimeSerializer(formatter);
        javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del Priceo 35455   para la brand 1 (ZARA)")
    void test1() throws JsonProcessingException {
        // given
        PriceRequestDto dto = new PriceRequestDto();
        dto.setDate(LocalDateTime.parse("2020-06-14 10:00:00", formatter));
        dto.setProductId("35455");
        dto.setBrandId(1);
        // when
        client.post().uri("/Prices")
            .header("Accept", "application/json")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            // then
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith(respuesta -> {
                try {
                    JsonNode json = objectMapper.readTree(respuesta.getResponseBody());
                    assertEquals(1, json.path(0).path("brand_ID").asInt());
                    assertEquals("2020-06-14T00:00:00", json.path(0).path("start_DATE").asText());
                    assertEquals("2020-12-31T23:59:59", json.path(0).path("end_DATE").asText());
                    assertEquals("35455", json.path(0).path("product_ID").asText());
                    assertEquals(0, json.path(0).path("priority").asInt());
                    assertEquals(1, json.path(0).path("price_LIST").asInt());
                    assertEquals(35.50, json.path(0).path("price").asDouble());
                    assertEquals("EUR", json.path(0).path("curr").asText());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del Priceo 35455   para la brand 1 (ZARA)")
    void test2() throws JsonProcessingException {
        // given
        PriceRequestDto dto = new PriceRequestDto();
        dto.setDate(LocalDateTime.parse("2020-06-14 16:00:00", formatter));
        dto.setProductId("35455");
        dto.setBrandId(1);
        // when
        client.post().uri("/Prices")
            .header("Accept", "application/json")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            // then
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith(respuesta -> {
                try {
                    JsonNode json = objectMapper.readTree(respuesta.getResponseBody());
                    assertEquals(1, json.path(0).path("brand_ID").asInt());
                    assertEquals("2020-06-14T00:00:00", json.path(0).path("start_DATE").asText());
                    assertEquals("2020-12-31T23:59:59", json.path(0).path("end_DATE").asText());
                    assertEquals("35455", json.path(0).path("product_ID").asText());
                    assertEquals(0, json.path(0).path("priority").asInt());
                    assertEquals(1, json.path(0).path("price_LIST").asInt());
                    assertEquals(35.50, json.path(0).path("price").asDouble());
                    assertEquals("EUR", json.path(0).path("curr").asText());
                    assertEquals(1, json.path(1).path("brand_ID").asInt());
                    assertEquals("2020-06-14T15:00:00", json.path(1).path("start_DATE").asText());
                    assertEquals("2020-06-14T18:30:00", json.path(1).path("end_DATE").asText());
                    assertEquals("35455", json.path(1).path("product_ID").asText());
                    assertEquals(1, json.path(1).path("priority").asInt());
                    assertEquals(2, json.path(1).path("price_LIST").asInt());
                    assertEquals(25.45, json.path(1).path("price").asDouble());
                    assertEquals("EUR", json.path(1).path("curr").asText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del Priceo 35455   para la brand 1 (ZARA)")
    void test3() throws JsonProcessingException {
        // given
        PriceRequestDto dto = new PriceRequestDto();
        dto.setDate(LocalDateTime.parse("2020-06-14 21:00:00", formatter));
        dto.setProductId("35455");
        dto.setBrandId(1);
        // when
        client.post().uri("/Prices")
            .header("Accept", "application/json")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            // then
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith(respuesta -> {
                try {
                    JsonNode json = objectMapper.readTree(respuesta.getResponseBody());
                    assertEquals(1, json.path(0).path("brand_ID").asInt());
                    assertEquals("2020-06-14T00:00:00", json.path(0).path("start_DATE").asText());
                    assertEquals("2020-12-31T23:59:59", json.path(0).path("end_DATE").asText());
                    assertEquals("35455", json.path(0).path("product_ID").asText());
                    assertEquals(0, json.path(0).path("priority").asInt());
                    assertEquals(1, json.path(0).path("price_LIST").asInt());
                    assertEquals(35.50, json.path(0).path("price").asDouble());
                    assertEquals("EUR", json.path(0).path("curr").asText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del Priceo 35455   para la brand 1 (ZARA)")
    void test4() throws JsonProcessingException {
        // given
        PriceRequestDto dto = new PriceRequestDto();
        dto.setDate(LocalDateTime.parse("2020-06-15 10:00:00", formatter));
        dto.setProductId("35455");
        dto.setBrandId(1);
        // when
        client.post().uri("/Prices")
            .header("Accept", "application/json")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            // then
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith(respuesta -> {
                try {
                    JsonNode json = objectMapper.readTree(respuesta.getResponseBody());
                    assertEquals(1, json.path(0).path("brand_ID").asInt());
                    assertEquals("2020-06-14T00:00:00", json.path(0).path("start_DATE").asText());
                    assertEquals("2020-12-31T23:59:59", json.path(0).path("end_DATE").asText());
                    assertEquals("35455", json.path(0).path("product_ID").asText());
                    assertEquals(0, json.path(0).path("priority").asInt());
                    assertEquals(1, json.path(0).path("price_LIST").asInt());
                    assertEquals(35.50, json.path(0).path("price").asDouble());
                    assertEquals("EUR", json.path(0).path("curr").asText());
                    assertEquals(1, json.path(1).path("brand_ID").asInt());
                    assertEquals("2020-06-15T00:00:00", json.path(1).path("start_DATE").asText());
                    assertEquals("2020-06-15T11:00:00", json.path(1).path("end_DATE").asText());
                    assertEquals("35455", json.path(1).path("product_ID").asText());
                    assertEquals(1, json.path(1).path("priority").asInt());
                    assertEquals(3, json.path(1).path("price_LIST").asInt());
                    assertEquals(30.50, json.path(1).path("price").asDouble());
                    assertEquals("EUR", json.path(1).path("curr").asText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del Priceo 35455   para la brand 1 (ZARA)")
    void test5() throws JsonProcessingException {
        // given
        PriceRequestDto dto = new PriceRequestDto();
        dto.setDate(LocalDateTime.parse("2020-06-15 21:00:00", formatter));
        dto.setProductId("35455");
        dto.setBrandId(1);
        // when
        client.post().uri("/Prices")
            .header("Accept", "application/json")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            // then
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .consumeWith(respuesta -> {
                try {
                    JsonNode json = objectMapper.readTree(respuesta.getResponseBody());
                    assertEquals(1, json.path(0).path("brand_ID").asInt());
                    assertEquals("2020-06-14T00:00:00", json.path(0).path("start_DATE").asText());
                    assertEquals("2020-12-31T23:59:59", json.path(0).path("end_DATE").asText());
                    assertEquals("35455", json.path(0).path("product_ID").asText());
                    assertEquals(0, json.path(0).path("priority").asInt());
                    assertEquals(1, json.path(0).path("price_LIST").asInt());
                    assertEquals(35.50, json.path(0).path("price").asDouble());
                    assertEquals("EUR", json.path(0).path("curr").asText());
                    assertEquals(1, json.path(1).path("brand_ID").asInt());
                    assertEquals("2020-06-15T16:00:00", json.path(1).path("start_DATE").asText());
                    assertEquals("2020-12-31T23:59:59", json.path(1).path("end_DATE").asText());
                    assertEquals("35455", json.path(1).path("product_ID").asText());
                    assertEquals(1, json.path(1).path("priority").asInt());
                    assertEquals(4, json.path(1).path("price_LIST").asInt());
                    assertEquals(38.95, json.path(1).path("price").asDouble());
                    assertEquals("EUR", json.path(1).path("curr").asText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

    }
}
