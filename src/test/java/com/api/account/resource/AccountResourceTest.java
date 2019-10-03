package com.api.account.resource;

import com.api.account.AccountDaoImpl;
import com.api.account.config.RoutesApplication;
import com.api.account.model.Account;
import com.api.account.repository.AccountDao;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.undertow.Undertow;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static com.api.account.constants.HttpConstants.APP_HOST;

public class AccountResourceTest {

    private Account accountInDB;

    private AccountDao accountDao = new AccountDaoImpl();

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
        Account accountCreated = accountDao.insert(new Account("Rafael"));
        RestAssured.given().port(8090).get("/accounts/" + accountCreated.getId()).then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo("Rafael"));
    }

    @After
    public void deleteAll() {
        accountDao.deleteAll();
    }

}
