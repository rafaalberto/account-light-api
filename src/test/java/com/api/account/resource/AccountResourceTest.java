package com.api.account.resource;

import com.api.account.model.Account;
import com.api.account.model.Message;
import com.api.account.repository.AccountDao;
import com.api.account.repository.impl.AccountDaoImpl;
import com.api.account.utils.NumericConverter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.undertow.Undertow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.api.account.config.RoutesApplication.ROUTES;
import static com.api.account.utils.HttpUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountResourceTest {

    private static final String RESOURCE_PATH = "/accounts";

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
    public void shouldCreateAccountSuccessfully() {
        Account accountToInsert = new Account("Mary");
        Account accountInserted = insertAccount(accountToInsert);

        Account result = RestAssured.given().contentType(ContentType.JSON)
                .body(accountToInsert)
                .post(RESOURCE_PATH)
                .then().statusCode(HTTP_CREATED_STATUS).extract().as(Account.class);

        assertThat(result.getName()).isEqualTo("Mary");
        assertThat(result.getBalance()).isEqualTo(NumericConverter.convertTwoDecimalPlace(BigDecimal.ZERO));

        deleteAccount(accountInserted.getId());
    }

    @Test
    public void shouldDenyToCreateAccount() {
        Account accountToInsert = new Account("");

        Message result = RestAssured.given().contentType(ContentType.JSON)
                .body(accountToInsert)
                .post(RESOURCE_PATH)
                .then().statusCode(HTTP_BAD_REQUEST_STATUS).extract().as(Message.class);

        assertThat(result.getDescription()).isEqualTo("Name must be informed");
    }

    @Test
    public void shouldDeleteAccountSuccessfully() {
        Account accountToInsert = new Account("Mary");
        Account accountInserted = insertAccount(accountToInsert);

        RestAssured.given().contentType(ContentType.JSON)
                .body(accountToInsert)
                .delete(RESOURCE_PATH + "/" + accountInserted.getId())
                .then().statusCode(HTTP_NO_CONTENT_STATUS);

        deleteAccount(accountInserted.getId());
    }

    @Test
    public void shouldFindByIdSuccessfully() {
        Account accountToInsert = new Account("Mary");
        Account accountInserted = insertAccount(accountToInsert);

        Account result = RestAssured.given().contentType(ContentType.JSON)
                .body(accountToInsert)
                .get(RESOURCE_PATH + "/" + accountInserted.getId())
                .then().statusCode(HTTP_OK_STATUS).extract().as(Account.class);

        assertThat(result.getName()).isEqualTo("Mary");
        assertThat(result.getBalance()).isEqualTo(NumericConverter.convertTwoDecimalPlace(BigDecimal.ZERO));

        deleteAccount(accountInserted.getId());
    }

    @Test
    public void shouldNotFindAccountById() {
        Account accountToFind = new Account(999L, "Rafael");
        RestAssured.given().contentType(ContentType.JSON)
                .body(accountToFind)
                .get(RESOURCE_PATH + "/" + accountToFind.getId())
                .then().statusCode(HTTP_NOT_FOUND_STATUS).extract().as(Message.class);
    }

    private Account insertAccount(Account account) {
        return accountDao.insert(account);
    }

    private void deleteAccount(Long accountId) {
        accountDao.delete(accountId);
    }

    @After
    public void finish() {
        server.stop();
        accountDao.deleteAll();
    }
}
