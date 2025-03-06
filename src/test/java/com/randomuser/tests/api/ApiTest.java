package com.randomuser.tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiTest {

    @Parameters("gender")
    @Test
    public void apiTest() {

        String gender = System.getProperty("gender", "male");
        System.out.println("Using gender: " + gender);

        Response response = RestAssured.given()
                .queryParam("gender", gender)
                .queryParam("results", 10)
                .get("https://randomuser.me/api/");

        Assert.assertEquals(response.getStatusCode(), 200, "API response is not 200");

        List<Map<String, Object>> results = response.jsonPath().getList("results");

        List<User> users = results.stream()
                .map(user -> new User(
                        ((Map<String, String>) user.get("name")).get("first") + " " + ((Map<String, String>) user.get("name")).get("last"),
                        ((Map<String, String>) user.get("login")).get("username")
                ))
                .collect(Collectors.toList());

        boolean allMatch = results.stream().allMatch(user -> gender.equalsIgnoreCase((String) user.get("gender")));
        Assert.assertTrue(allMatch, "Nie wszyscy użytkownicy mają poprawną płeć!");

        users.forEach(user -> System.out.println("User: " + user.name + " - Login: " + user.login));
    }
}
