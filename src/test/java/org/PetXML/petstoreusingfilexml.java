package org.PetXML;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

public class petstoreusingfilexml {

    @Test
    public void createPet(){
        Response resp= RestAssured.given().baseUri("https://petstore.swagger.io/v2").header("Content-Type","application/xml")
                .accept("application/xml").body(new File("src/test/resources/XMLFiles/pet.xml")).log().all().
                        when().post("/pet").
                        then().log().all().assertThat().statusCode(200).body("Pet.id", Matchers.equalTo("786"))
                .body("Pet.photoUrls.photoUrl",Matchers.equalTo("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg")).
                        extract().response();
        System.out.println(resp.asPrettyString());
    }
}
