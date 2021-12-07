package com.slotegrator.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiTests {

    private static String accessToken;
    private static String registratedUserAccessToken;
    private static Player newPlayer = new Player();

    @Order(1)
    @Test
    @DisplayName("get guest Access Token")
    void getGuestAccessToken() {

        Map<String, String> params = new HashMap<>();
        params.put("scope", "guest:default");

        System.out.println("Get guest Access Token");
        Response response = RequestHelper.auth("client_credentials", params);

        System.out.println("Response:");
        response
                .then().log().body().assertThat()
                .statusCode(200).and()
                .body("access_token", not(empty()))
                .body("access_token", notNullValue());

        accessToken = response.path("access_token");
    }

    @Order(2)
    @Test
    @DisplayName("register a new Player")
    public void registerNewPlayer() {

        System.out.println("Register a new Player");

        Response registration = given().urlEncodingEnabled(true)
                .spec(RequestHelper.getRequestSpecificationBuilder().build())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Authorization", "Bearer " + accessToken)
                .param("username", newPlayer.getUsername())
                .param("name", newPlayer.getName())
                .param("surname", newPlayer.getSurname())
                .param("password_change", newPlayer.getEncodedPasswordString())
                .param("password_repeat", newPlayer.getEncodedPasswordString())
                .param("email", newPlayer.getEmail())
                .param("currency_code", newPlayer.getCurrencyCode())
                .log().params()
                .when()
                .post(ConfProperties.getProperty("players"));

        System.out.println("Response:");

        registration
                .then()
                .log().body()
                .assertThat()
                .statusCode(201)
                .body("id", not(empty()))
                .body("username", equalTo(newPlayer.getUsername()))
                .body("email", equalTo(newPlayer.getEmail()))
                .body("name", equalTo(newPlayer.getName()))
                .body("surname", equalTo(newPlayer.getSurname()))
                .body(matchesJsonSchemaInClasspath("createNewUserResponseJSONSchema.json"));

        newPlayer.setId(registration.path("id"));
    }

    @Order(3)
    @Test
    @DisplayName("authenticate as a Player")
    public void authAsPlayer() {

        Map<String, String> params = new HashMap<>();
        params.put("username", newPlayer.getUsername());
        params.put("password", newPlayer.getEncodedPasswordString());

        System.out.println("Authenticate as a Player");

        Response response = RequestHelper.auth("password", params);

        System.out.println("Response:");

        response
                .then().log().body().assertThat().statusCode(200).and()
                .body("access_token", not(empty()))
                .body("access_token", notNullValue());

        registratedUserAccessToken = response.path("access_token");
    }

    @Order(4)
    @Test
    @DisplayName("get player profile")
    public void getPlayerProfile() {

        System.out.println("Get player profile");

        Response playerProfile = given().urlEncodingEnabled(true)
                .spec(RequestHelper.getRequestSpecificationBuilder().build())
                .header("Authorization", "Bearer " + registratedUserAccessToken)
                .pathParam("userId", newPlayer.getId())
                .log().all()
                .when()
                .get(ConfProperties.getProperty("players") + "/{userId}");

        System.out.println("Response:");

        playerProfile
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .body("id", not(empty()))
                .body("username", equalTo(newPlayer.getUsername()))
                .body("email", equalTo(newPlayer.getEmail()))
                .body("name", equalTo(newPlayer.getName()))
                .body("surname", equalTo(newPlayer.getSurname()))
                .body(matchesJsonSchemaInClasspath("getUserResponseJSONSchema.json"));
    }

    @Order(5)
    @Test
    @DisplayName("Get another player profile")
    public void getOtherPlayerProfile() {

        System.out.println("Get another player profile");

        Response otherProfile = given().urlEncodingEnabled(true)
                .spec(RequestHelper.getRequestSpecificationBuilder().build())
                .header("Authorization", "Bearer " + registratedUserAccessToken)
                .pathParam("userId", newPlayer.getId() - 1)
                .log().all()
                .when()
                .get(ConfProperties.getProperty("players") + "/{userId}");

        System.out.println("Response:");

        otherProfile
                .then()
                .log().body()
                .assertThat().statusCode(404);
    }
}
