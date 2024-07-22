package org.apiautomationbdd;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ListofResponses {

    @Test
    public void getAllUsers() {
        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured.given().get("/users?page=2");

        JsonPath json = resp.jsonPath();

        List<Integer> allUserID = json.getList("data.id");

        Assert.assertEquals(allUserID.size(), 10);

    }

    @Test
    public void getAllUsers1() {
        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured.given().get("/users?page=2");

        JsonPath json = resp.jsonPath();

        List<Integer> allUserID = json.getList("data.id");

        boolean status = false;

        for (int userID : allUserID) {
            if (userID == 13) {
                status = true;
                break;
            }
        }

        Assert.assertTrue(status);

    }

    @Test
    public void getAllUsersEmails() {
        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured.given().get("/users?page=2");

        JsonPath json = resp.jsonPath();

        List<String> allUserID = json.getList("data.email");

        boolean status = false;

        for (String userID : allUserID) {
            if (userID.equals("george.edwards@reqres.in")) {
                status = true;
                break;
            }
        }

        Assert.assertTrue(status);

    }

    @Test
    public void getAllUsersEmails1() {
        RestAssured.baseURI = "https://reqres.in/api";

        Response resp = RestAssured.given().get("/users?page=2");

        JsonPath json = resp.jsonPath();

        List<String> allUserID = json.getList("data.email");

        boolean status = false;

        //int counter=0;

        for (String userID : allUserID) {
            if (userID.contains("george")) {
                status = true;
                break;
            }
        }

        Assert.assertTrue(status);

    }


}
