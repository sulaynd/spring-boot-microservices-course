package com.sivalabs.bookstore.orders.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivalabs.bookstore.orders.AbstractIT;
import com.sivalabs.bookstore.orders.WithMockOAuth2User;
import com.sivalabs.bookstore.orders.clients.catalog.Product;
import com.sivalabs.bookstore.orders.clients.catalog.ProductServiceClient;
import com.sivalabs.bookstore.orders.domain.*;
import com.sivalabs.bookstore.orders.domain.models.CreateOrderRequest;
import com.sivalabs.bookstore.orders.domain.models.OrderSummary;
import com.sivalabs.bookstore.orders.testdata.TestDataFactory;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql("/test-orders.sql")
class OrderControllerTests extends AbstractIT {
    @Mock
    RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private  OrderService orderService;

    @Autowired
    private  SecurityService securityService;

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderService,securityService)).build();
    }

    @Nested
    class CreateOrderTests {

        @Test
        @WithMockOAuth2User(username = "user")
        void shouldCreateOrderSuccessfully() throws Exception {

            mockGetProductByCode("P100", "Product 1", new BigDecimal("25.50"));
//             var product = TestDataFactory.mockGetProductByCode();
//            RestClient.RequestHeadersUriSpec getRequest = mock(RestClient.RequestHeadersUriSpec.class);
//            RestClient.ResponseSpec response = mock(RestClient.ResponseSpec.class);
//            when(restClient.get()).thenReturn(getRequest);
//            when(getRequest.uri(anyString())).thenReturn(getRequest);
//           // when(getRequest.accept(any())).thenReturn(getRequest);
//            when(getRequest.retrieve()).thenReturn(response);
//            when(response.body(any(ParameterizedTypeReference.class))).thenReturn(product);

            var payload = TestDataFactory.createValidOrderRequest();

            mockMvc.perform(
                            MockMvcRequestBuilders.post("/api/orders")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(payload)))
                                    .andExpect(status().isCreated())
                                    .andExpect(jsonPath("$.orderNumber").exists());


           List<OrderEntity> order = repository.findAll();
           System.out.println(order);


//            mockGetProductByCode("P100", "Product 1", new BigDecimal("25.50"));
//            var payload =
//                    """
//                        {
//                            "customer" : {
//                                "name": "Siva",
//                                "email": "siva@gmail.com",
//                                "phone": "999999999"
//                            },
//                            "deliveryAddress" : {
//                                "addressLine1": "HNO 123",
//                                "addressLine2": "Kukatpally",
//                                "city": "Hyderabad",
//                                "state": "Telangana",
//                                "zipCode": "500072",
//                                "country": "India"
//                            },
//                            "items": [
//                                {
//                                    "code": "P100",
//                                    "name": "Product 1",
//                                    "price": 25.50,
//                                    "quantity": 1
//                                }
//                            ]
//                        }
//                    """;




//                        given().contentType(ContentType.JSON)
//                                .header("Authorization", "Bearer " + getToken())
//                                .body(payload)
//                                .when()
//                                .post("/api/orders")
//                                .then()
//                                .statusCode(HttpStatus.CREATED.value())
//                                .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + getToken())
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    class GetOrdersTests {
        @Test
        void shouldGetOrdersSuccessfully() {
            List<OrderSummary> orderSummaries = given().when()
                    .header("Authorization", "Bearer " + getToken())
                    .get("/api/orders")
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(new TypeRef<>() {});

            assertThat(orderSummaries).hasSize(2);
        }
    }

    @Nested
    class GetOrderByOrderNumberTests {
        String orderNumber = "order-123";

        @Test
        void shouldGetOrderSuccessfully() {
            given().when()
                    .header("Authorization", "Bearer " + getToken())
                    .get("/api/orders/{orderNumber}", orderNumber)
                    .then()
                    .statusCode(200)
                    .body("orderNumber", is(orderNumber))
                    .body("items.size()", is(2));
        }

        @Test
        @WithMockOAuth2User(username = "user")
        void shouldGetOrderByNumberSuccessfully() throws Exception {
            List<OrderEntity> order = repository.findAll();
            System.out.println(order);
           // String orderNumber = "order-123";
            mockMvc.perform(get("/api/orders/{orderNumber}", orderNumber)).andExpect(status().isOk());
        }
    }


}
