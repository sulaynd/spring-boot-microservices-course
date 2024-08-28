// package com.sivalabs.bookstore.orders;
//
//// import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//// import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
//// import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
//// import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
//// import static org.mockserver.model.HttpRequest.request;
//// import static org.mockserver.model.HttpResponse.response;
//// import static org.mockserver.model.JsonBody.json;
// import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
//
//// import com.github.tomakehurst.wiremock.client.WireMock;
//// import io.restassured.RestAssured;
// import java.math.BigDecimal;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.context.annotation.Import;
// import org.springframework.test.context.DynamicPropertyRegistry;
// import org.springframework.test.context.DynamicPropertySource;
// import org.springframework.test.web.servlet.MockMvc;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;
//// import org.testcontainers.containers.MockServerContainer;
// import org.testcontainers.utility.DockerImageName;
//
//// import org.wiremock.integrations.testcontainers.WireMockContainer;
//
// @SpringBootTest(webEnvironment = RANDOM_PORT)
// @Import(ContainersConfig.class)
// @AutoConfigureMockMvc
// @Testcontainers
// public abstract class AbstractIT2 {
////    @LocalServerPort
////    int port;
////
////    @BeforeEach
////    void setUp() {
////        RestAssured.port = port;
////    }
//
//    //    static final String CLIENT_ID = "bookstore-webapp";
//    //    static final String CLIENT_SECRET = "P1sibsIrELBhmvK18BOzw1bUl96DcP2z";
//    //    static final String USERNAME = "siva";
//    //    static final String PASSWORD = "siva1234";
//    //
//    //    @Autowired
//    //    OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
//
//        @Autowired
//        protected MockMvc mockMvc;
//    //
//   // protected static WireMockContainer wiremockServer = new WireMockContainer("wiremock/wiremock:3.5.2-alpine");
//    // static WireMockContainer wiremockServer = new WireMockContainer("wiremock/wiremock:3.6.0");
//    @Container
//    static MockServerContainer mockServerContainer = new MockServerContainer(
//            DockerImageName.parse("mockserver/mockserver:5.15.0"));
////    @BeforeAll
////    static void beforeAll() {
////        wiremockServer.start();
////        configureFor(wiremockServer.getHost(), wiremockServer.getPort());
////    }
//
////    @DynamicPropertySource
////    static void configureProperties(DynamicPropertyRegistry registry) {
////        registry.add("orders.catalog-service-url", wiremockServer::getBaseUrl);
////    }
//    @DynamicPropertySource
//    static void overrideProperties(DynamicPropertyRegistry registry) {
//        registry.add("orders.catalog-service-url", mockServerContainer::getEndpoint);
//    }
//    static MockServerClient mockServerClient;
//
//    @BeforeAll
//    static void beforeAll() {
//        mockServerClient = new MockServerClient(
//                mockServerContainer.getHost(),
//                mockServerContainer.getServerPort()
//        );
//    }
//
//    @BeforeEach
//    void setUp() {
//        mockServerClient.reset();
//    }
//    protected static void mockGetProductByCode(BigDecimal price) {
//
//        mockServerClient.when(request().withMethod("GET").withPath("/api/products/.*"))
//                .respond(response().withStatusCode(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody(
//                                """
//                                {
//                                "code": "%s",
//                                "name": "%s",
//                                "price": %f
//                                }
//                                """
//                        .formatted("P100", "Product 1", price.doubleValue())));
//
//
////        stubFor(WireMock.get(urlMatching("/api/products/" + code))
////                .willReturn(aResponse()
////                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
////                        .withStatus(200)
////                        .withBody(
////                                """
////                {
////                "code": "%s",
////                "name": "%s",
////                "price": %f
////                }
////                """
////                                        .formatted(code, name, price.doubleValue()))));
//    }
//
//    //    protected String getToken() {
//    //        RestTemplate restTemplate = new RestTemplate();
//    //        HttpHeaders httpHeaders = new HttpHeaders();
//    //        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//    //
//    //        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//    //        map.put(OAuth2Constants.GRANT_TYPE, singletonList(OAuth2Constants.PASSWORD));
//    //        map.put(OAuth2Constants.CLIENT_ID, singletonList(CLIENT_ID));
//    //        map.put(OAuth2Constants.CLIENT_SECRET, singletonList(CLIENT_SECRET));
//    //        map.put(OAuth2Constants.USERNAME, singletonList(USERNAME));
//    //        map.put(OAuth2Constants.PASSWORD, singletonList(PASSWORD));
//    //
//    //        String authServerUrl =
//    //                oAuth2ResourceServerProperties.getJwt().getIssuerUri() + "/protocol/openid-connect/token";
//    //
//    //        var request = new HttpEntity<>(map, httpHeaders);
//    //        KeyCloakToken token = restTemplate.postForObject(authServerUrl, request, KeyCloakToken.class);
//    //
//    //        assert token != null;
//    //        return token.accessToken();
//    //    }
//    //
//    //    record KeyCloakToken(@JsonProperty("access_token") String accessToken) {}
// }
