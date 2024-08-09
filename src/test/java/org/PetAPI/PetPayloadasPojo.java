package org.PetAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class PetPayloadasPojo {

    @Test
    public void createpetusingpojo(){
        PetPOJO.category Category= new PetPOJO.category(101,"Golden Retriever");
        PetPOJO.tags Tags= new PetPOJO.tags(101,"Dogs");
        PetPOJO payload = new PetPOJO(201,Category,"Dogs", List.of("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"),List.of(Tags),"available");
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
