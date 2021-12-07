package com.slotegrator.api;

import com.github.javafaker.Faker;

import java.util.Base64;


public class Player {
    private final String name;
    private final String surname;
    private final String username;
    private final String encodedPasswordString;
    private final String email;
    private final String currencyCode;
    private Integer id;

    public Player() {
        Faker faker = new Faker();

        name = faker.name().firstName();
        surname = faker.name().lastName();
        username = name + surname + System.currentTimeMillis() % 10000;
        String password = ConfProperties.getProperty("player_password");
        encodedPasswordString = Base64.getEncoder().encodeToString(password.getBytes());
        email = username + "@gmail.com";
        currencyCode = "RUB";
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEncodedPasswordString() {
        return encodedPasswordString;
    }

    public String getEmail() {
        return email;
    }

}
