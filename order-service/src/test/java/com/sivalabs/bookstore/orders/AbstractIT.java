package com.sivalabs.bookstore.orders;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathTemplate;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.sivalabs.bookstore.orders.clients.catalog.ProductResponse;
import io.restassured.RestAssured;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.wiremock.integrations.testcontainers.WireMockContainer;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
// @AutoConfigureMockMvc
public abstract class AbstractIT {
    //    static final String CLIENT_ID = "bookstore-webapp";
    //    static final String CLIENT_SECRET = "P1sibsIrELBhmvK18BOzw1bUl96DcP2z";
    //    static final String USERNAME = "siva";
    //    static final String PASSWORD = "siva1234";
    //
    //    @Autowired
    //    OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

    @LocalServerPort
    int port;

    //    @Autowired
    //    protected MockMvc mockMvc;

    static WireMockContainer wiremockServer = new WireMockContainer("wiremock/wiremock:3.5.2-alpine");

    @BeforeAll
    static void beforeAll() {
        wiremockServer.start();
        configureFor(wiremockServer.getHost(), wiremockServer.getPort());
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("orders.catalog-service-url", wiremockServer::getBaseUrl);
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    protected static void mockGetProductByCode(String code, String name, BigDecimal price) {
        stubFor(WireMock.get(urlPathTemplate("/api/products/" + code))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(
                                """
                    {
                        "code": "%s",
                        "name": "%s",
                        "price": %f
                    }
                """
                                        .formatted(code, name, price.doubleValue()))));
    }

    public static ProductResponse getProductResponse() {
        var productResponse = new ProductResponse();
        productResponse.setCode("P100");
        productResponse.setName("Product 1");
        productResponse.setPrice(new BigDecimal("25.50"));

        return productResponse;
    }
    //
    //    protected String getToken() {
    //        RestTemplate restTemplate = new RestTemplate();
    //        HttpHeaders httpHeaders = new HttpHeaders();
    //        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    //
    //        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    //        map.put(OAuth2Constants.GRANT_TYPE, singletonList(OAuth2Constants.PASSWORD));
    //        map.put(OAuth2Constants.CLIENT_ID, singletonList(CLIENT_ID));
    //        map.put(OAuth2Constants.CLIENT_SECRET, singletonList(CLIENT_SECRET));
    //        map.put(OAuth2Constants.USERNAME, singletonList(USERNAME));
    //        map.put(OAuth2Constants.PASSWORD, singletonList(PASSWORD));
    //
    //        String authServerUrl =
    //                oAuth2ResourceServerProperties.getJwt().getIssuerUri() + "/protocol/openid-connect/token";
    //
    //        var request = new HttpEntity<>(map, httpHeaders);
    //        KeyCloakToken token = restTemplate.postForObject(authServerUrl, request, KeyCloakToken.class);
    //
    //        assert token != null;
    //        return token.accessToken();
    //    }
    //
    //    record KeyCloakToken(@JsonProperty("access_token") String accessToken) {}
}
