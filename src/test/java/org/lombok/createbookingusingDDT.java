package org.lombok;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.requestspecification.Base;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class createbookingusingDDT extends Base {

    @Test
    public void createbooking() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> values = mapper.readValue(new File("./src/test/resources/JSONFiles/Builduser.json"), new TypeReference<List<Map<String, Object>>>() {
        });

        System.out.println(values.size());

        for (Map<String, Object> payload : values) {
            Response resp = given().spec(req)

                    .body(payload)
                    .post("/booking");
        }
    }
}
