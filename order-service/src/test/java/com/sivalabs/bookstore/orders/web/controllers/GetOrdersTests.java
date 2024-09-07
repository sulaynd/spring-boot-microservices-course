package com.sivalabs.bookstore.orders.web.controllers;

import io.restassured.http.ContentType;

import java.nio.charset.Charset;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivalabs.bookstore.orders.AbstractIT;
import com.sivalabs.bookstore.orders.WithMockOAuth2User;
import com.sivalabs.bookstore.orders.testdata.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-orders.sql")
class GetOrdersTests extends AbstractIT {
    //public final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockOAuth2User(username = "user")
    void shouldGetOrdersSuccessfully() throws Exception {
        mockMvc.perform(get("/api/orders")).andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2User(username = "user")
    void shouldGetOrderByNumberSuccessfully() throws Exception {
        String orderNumber = "order-123";
        mockMvc.perform(get("/api/orders/{orderNumber}", orderNumber)).andExpect(status().isOk());
    }

    @Test
    @WithMockOAuth2User(username = "user")
    void shouldReturnBadRequestWhenMandatoryDataIsMissing() throws Exception {
        var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();

//        mockMvc.perform(post("/api/orders")
//                    .contentType(APPLICATION_JSON_UTF8)
//                    .content(String.valueOf(payload)))
//                    .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest());

    }
}
