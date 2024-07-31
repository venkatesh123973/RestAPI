package org.petStoreCRUD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class petStoreCRUD extends petstorebase {

    @Test(priority = 0)
    public void createuser() {
        Response response = RestAssured.given().spec(req).body("{\n" +
                "  \"id\": 787,\n" +
                "  \"username\": \"Venkat\",\n" +
                "  \"firstName\": \"Venkat\",\n" +
                "  \"lastName\": \"Kannan\",\n" +
                "  \"email\": \"kan@gmail.com\",\n" +
                "  \"password\": \"9898\",\n" +
                "  \"phone\": \"909087865\",\n" +
                "  \"userStatus\": true\n" +
                "}").when().post("/user");

        System.out.println("Response: " + response.jsonPath().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
        //Assert.assertTrue(resp.jsonPath().getString("code").equals("200"));
        Assert.assertEquals(response.jsonPath().getString("code"), "200");
    }


    @Test(priority = 1)
    public void getuser() {
        Response response = RestAssured.given().spec(req).log().all().pathParam("uname", "Venkat").when().
                get("/user/{uname}");
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("$", hasKey("id"));
        response.then().body("$", hasKey("username"));
        response.then().body("$", hasKey("firstName"));
        response.then().body("$", hasKey("lastName"));
        response.then().body("$", hasKey("email"));
        response.then().body("$", hasKey("password"));
        response.then().body("$", hasKey("phone"));
        response.then().body("$", hasKey("userStatus"));


    }

    @Test(priority = 2)
    public void putuser() {
        Response response = RestAssured.given().spec(req).log().all().pathParam("uname", "Venkat").body("{\n" +
                "  \"id\": 787,\n" +
                "  \"username\": \"Venkat\",\n" +
                "  \"firstName\": \"Venkatesh\",\n" +
                "  \"lastName\": \"Kannan\",\n" +
                "  \"email\": \"kannan@gmail.com\",\n" +
                "  \"password\": \"9898\",\n" +
                "  \"phone\": \"909087865\",\n" +
                "  \"userStatus\": true\n" +
                "}").when().put("/user/{uname}");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("email"), "kannan@gmail.com");

    }

    @Test(priority = 3)
    public void deleteuser(){
        Response response = RestAssured.given().spec(req).log().all().pathParam("uname", "Venkat").when()
                .delete("/user/{uname}");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}