package com.api.account.resource;

import com.api.account.model.Account;
import com.api.account.model.Message;
import com.api.account.model.Transaction;
import com.api.account.repository.AccountDao;
import com.api.account.repository.impl.AccountDaoImpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.undertow.Undertow;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.api.account.config.RoutesApplication.ROUTES;
import static com.api.account.enumeration.TransactionType.DEPOSIT;
import static com.api.account.utils.HttpUtils.*;
import static com.api.account.utils.NumericConverter.convertTwoDecimalPlace;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionResourceTest {

    private AccountDao accountDao = new AccountDaoImpl();

    private Undertow server;

    @Before
    public void setUp() {
        Undertow.Builder builder = Undertow.builder();
        builder.addHttpListener(TEST_PORT, APP_HOST);
        builder.setHandler(ROUTES);
        server = builder.build();
        server.start();

        RestAssured.baseURI = "http://" + APP_HOST + ":" + TEST_PORT;
    }

    @Test
    public void shouldDepositSuccessfully() {
        Account account = accountDao.insert(new Account("Rafael"));

        Transaction transaction = new Transaction(account.getId(), account.getId(), convertTwoDecimalPlace(new BigDecimal(1000)), DEPOSIT);

        int status = RestAssured.given().contentType(ContentType.JSON).body(transaction).post("/transactions").statusCode();
        assertThat(status).isEqualTo(201);
    }

//    @Test
//    public void shouldDenyToCreateAccount() {
//        Account accountToInsert = new Account("");
//
//        Message result = RestAssured.given().contentType(ContentType.JSON)
//                .body(accountToInsert)
//                .post("/accounts")
//                .then().statusCode(HTTP_BAD_REQUEST_STATUS).extract().as(Message.class);
//        assertThat(result.getDescription()).isEqualTo("Name must be informed");
//    }

    @After
    public void finish() {
        server.stop();
    }

}
