package com.api.account.resource;

import com.api.account.repository.impl.AccountDaoImpl;
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

import static com.api.account.utils.HttpUtils.APP_HOST;

public class AccountResourceTest {

    private AccountDao accountDao = new AccountDaoImpl();

    private Undertow server;

    @Before
    public void setUp() {

        Undertow.Builder builder = Undertow.builder();
        builder.addHttpListener(8090, APP_HOST);
        builder.setHandler(RoutesApplication.ROUTES);

        server = builder.build();
        server.start();
    }

    @Test
    public void findById() {
        Account accountCreated = accountDao.insert(new Account("Rafael"));
        RestAssured.given().port(8090).get("/accounts/" + accountCreated.getId()).then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.equalTo("Rafael"));
    }

    @After
    public void finish() {
        accountDao.deleteAll();
        server.stop();
    }

}
