package com.api.account.resource;

import com.api.account.config.RoutesApplication;
import com.api.account.model.Account;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.undertow.Undertow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static com.api.account.constants.HttpConstants.APP_HOST;

public class AccountResourceTest {

    private Account accountInDB;

    @Before
    public void setUp() {

        Undertow.Builder builder = Undertow.builder();
        builder.addHttpListener(8090, APP_HOST);
        builder.setHandler(RoutesApplication.ROUTES);

        Undertow server = builder.build();
        server.start();

        MockitoAnnotations.initMocks(this);
        accountInDB = new Account(1L, "Rafael");
    }

    @Test
    public void create() {
        RestAssured.given().port(8090).get("/accounts/1").then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200);
    }

}
