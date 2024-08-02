package org.petStoreCRUD;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.hasKey;

public class petstorecrudusingfile extends petstorebase {

    @Test(priority = 0)
    public void createuser() {
        Response response = RestAssured.given().spec(req).body(new File("./src/test/resources/JSONFiles/createuser.json")).when().post("/user");
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
    public void putuser() throws IOException {
        File file = new File("./src/test/resources/JSONFiles/createuser.json");
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> jsonmap=mapper.readValue(file,Map.class);
        jsonmap.put("firstName","Kohli");
        mapper.writeValue(file,jsonmap);
        Response response = RestAssured.given().spec(req).log().all().pathParam("uname", "Venkat").body(file)
                .when().put("/user/{uname}");
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.jsonPath().getString("email"), "kannan@gmail.com");

    }

    @Test(priority = 3)
    public void deleteuser(){
        Response response = RestAssured.given().spec(req).log().all().pathParam("uname", "Venkat").when()
                .delete("/user/{uname}");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
