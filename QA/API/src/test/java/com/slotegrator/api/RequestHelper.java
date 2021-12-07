package com.slotegrator.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;


class RequestHelper {

    private static RequestSpecBuilder requestSpecificationBuilder;

    static RequestSpecBuilder getRequestSpecificationBuilder() {
        createRequestSpecification();
        return requestSpecificationBuilder;
    }

    private static void createRequestSpecification() {
        requestSpecificationBuilder = new RequestSpecBuilder()
                .setBaseUri(ConfProperties.getProperty("base_uri"))
                .setBasePath(ConfProperties.getProperty("base_path"))
                .addHeader("Accept", ContentType.JSON.getAcceptHeader());
    }

    static Response auth(String grantTypeValue, Map authParams) {
        createRequestSpecification();

        RequestSpecification authParamsSpecification = requestSpecificationBuilder
                .addParams(authParams)
                .build();

        return given().urlEncodingEnabled(true)
                .auth().preemptive()
                .basic(ConfProperties.getProperty("basic_auth_token"), "")
                .param("grant_type", grantTypeValue)
                .spec(authParamsSpecification)
                .when()
                .log().all()
                .post(ConfProperties.getProperty("oauth2"));

    }
}