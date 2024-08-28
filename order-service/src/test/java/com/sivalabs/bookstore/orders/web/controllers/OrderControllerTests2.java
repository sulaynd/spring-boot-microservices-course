// package com.sivalabs.bookstore.orders.web.controllers;
//
// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.notNullValue;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// import com.sivalabs.bookstore.orders.AbstractIT2;
// import com.sivalabs.bookstore.orders.testdata.TestDataFactory;
// import io.restassured.http.ContentType;
// import java.math.BigDecimal;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
/// **
// * this is test integration
// */
//// @Sql("/test-orders.sql")
// class OrderControllerTests2 extends AbstractIT2 {
//
//    /**
//     * organize them in nested classes
//     */
//    @Nested
//    class CreateOrderTests {
//        @Test
//        void shouldCreateOrderSuccessfully() throws Exception {
//            //String baseUrl = wiremockServer.getBaseUrl();
//            //System.out.println("baseUrl : " + wiremockServer.getBaseUrl());
//            mockGetProductByCode(new BigDecimal("25.50"));
//
//            /**
//             * Using multiline string support here
//             * payload
//             */
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
//
//            mockMvc.perform(
//                            MockMvcRequestBuilders.post("/api/orders")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .content(payload))
//                    .andExpect(status().isCreated());
//
//
////            given().contentType(ContentType.JSON)
////                    //                    .header("Authorization", "Bearer " + getToken())
////                    .body(payload)
////                    .when()
////                    .post("/api/orders")
////                    .then()
////                    .statusCode(HttpStatus.CREATED.value())
////                    .body("orderNumber", notNullValue());
//
//            // System.out.println("baseUrl : "+ wiremockServer.);
//        }
//
//        @Test
//        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
//            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
//            given().contentType(ContentType.JSON)
//                    //                    .header("Authorization", "Bearer " + getToken())
//                    .body(payload)
//                    .when()
//                    .post("/api/orders")
//                    .then()
//                    .statusCode(HttpStatus.BAD_REQUEST.value());
//        }
//    }
//    //
//    //    @Nested
//    //    class GetOrdersTests {
//    //        @Test
//    //        void shouldGetOrdersSuccessfully() {
//    //            List<OrderSummary> orderSummaries = given().when()
//    //                    .header("Authorization", "Bearer " + getToken())
//    //                    .get("/api/orders")
//    //                    .then()
//    //                    .statusCode(200)
//    //                    .extract()
//    //                    .body()
//    //                    .as(new TypeRef<>() {});
//    //
//    //            assertThat(orderSummaries).hasSize(2);
//    //        }
//    //    }
//    //
//    //    @Nested
//    //    class GetOrderByOrderNumberTests {
//    //        String orderNumber = "order-123";
//    //
//    //        @Test
//    //        void shouldGetOrderSuccessfully() {
//    //            given().when()
//    //                    .header("Authorization", "Bearer " + getToken())
//    //                    .get("/api/orders/{orderNumber}", orderNumber)
//    //                    .then()
//    //                    .statusCode(200)
//    //                    .body("orderNumber", is(orderNumber))
//    //                    .body("items.size()", is(2));
//    //        }
//    //    }
// }
