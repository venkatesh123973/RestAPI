package org.petStoreCRUD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasKey;

public class petStoreCRUDusingPOJO extends petstorebase{

    @Test(priority = 0)
    public void createuser() {

        petStorePOJO payload = new petStorePOJO(788,"Venkat","Venkat","Kannan","kan@gmail.com","9898","90879076",true);
        Response response = RestAssured.given().spec(req).body(payload).when().post("/user");
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
        petStorePOJO payload = new petStorePOJO(788,"Venkat","Venkat","Kannan","kannan@gmail.com","9898","90879076",true);
        Response response = RestAssured.given().spec(req).log().all().pathParam("uname", "Venkat").body(payload).when().put("/user/{uname}");
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
