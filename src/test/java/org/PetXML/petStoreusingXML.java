package org.PetXML;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class petStoreusingXML {

    @Test
    public void createPet(){
        String payload = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Pet>\n" +
                "\t<id>786</id>\n" +
                "\t<Category>\n" +
                "\t\t<id>111</id>\n" +
                "\t\t<name>Dogs</name>\n" +
                "\t</Category>\n" +
                "\t<name>doggie</name>\n" +
                "\t<photoUrls>\n" +
                "\t\t<photoUrl>https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg</photoUrl>\n" +
                "\t</photoUrls>\n" +
                "\t<tags>\n" +
                "\t\t<Tag>\n" +
                "\t\t\t<id>101</id>\n" +
                "\t\t\t<name>Dogs</name>\n" +
                "\t\t</Tag>\n" +
                "\t</tags>\n" +
                "\t<status>available</status>\n" +
                "</Pet>";

        Response resp=RestAssured.given().baseUri("https://petstore.swagger.io/v2").header("Content-Type","application/xml")
                .accept("application/xml").body(payload).log().all().
                when().post("/pet").
                then().log().all().assertThat().statusCode(200).body("Pet.id",Matchers.equalTo("786"))
                .body("Pet.photoUrls.photoUrl",Matchers.equalTo("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg")).
    extract().response();
        System.out.println(resp.asPrettyString());
    }
}
