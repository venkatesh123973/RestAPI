package org.PetAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
public class PayloadasString {

    @Test
    public void pet(){

        Response response=RestAssured.given().baseUri("https://petstore.swagger.io/v2").contentType(ContentType.JSON).
                body("{\n" +
                        "    \"id\": 201,\n" +
                        "    \"category\": {\n" +
                        "        \"id\": 101,\n" +
                        "        \"name\": \"Golden Retriever\"\n" +
                        "    },\n" +
                        "    \"name\": \"Dogs\",\n" +
                        "    \"photoUrls\": [\n" +
                        "        \"https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg\"\n" +
                        "    ],\n" +
                        "    \"tags\": [\n" +
                        "        {\n" +
                        "            \"id\": 101,\n" +
                        "            \"name\": \"Dogs\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"status\": \"available\"\n" +
                        "}").log().all().when().post("/pet").then().log().all().assertThat().
                statusCode(200).extract().response();
        response.then().body("id", equalTo(201));
        response.then().body("category.id", equalTo(101));
        response.then().body("category.name", equalTo("Golden Retriever"));
        response.then().body("name", equalTo("Dogs"));
        response.then().body("photoUrls[0]", equalTo("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"));
        response.then().body("tags[0].id", equalTo(101));
        response.then().body("tags[0].name", equalTo("Dogs"));
        response.then().body("status", equalTo("available"));

    }
}
