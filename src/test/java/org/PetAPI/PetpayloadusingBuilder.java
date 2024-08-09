package org.PetAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class PetpayloadusingBuilder {

    @Test
    public void createpetusingpojo(){

        PetPOJO payload=PetPOJO.builder().id(201).name("Dogs").photoUrls(List.of("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"))
                .category(PetPOJO.category.builder().id(101).name("Golden Retriever").build()).
                tags(List.of(PetPOJO.tags.builder().id(101).name("Dogs").build())).status("available").build();
          Response response= RestAssured.given().baseUri("https://petstore.swagger.io/v2").contentType(ContentType.JSON).
                body(payload).log().all().when().post("/pet").then().log().all().assertThat().
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
