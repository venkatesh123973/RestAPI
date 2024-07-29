package org.requestspecification;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JIRA_API_Basic_Auth_Map {

    @Test
    public void createIssue() {
        Map<String, String> project = new LinkedHashMap<>();
        project.put("id", "10000");

        Map<String, Object> issuetype = new LinkedHashMap<>();
        issuetype.put("id", "10002");

        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("project", project);
        fields.put("summary", "Added issue for Jira API");
        fields.put("issuetype", issuetype);

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("fields", fields);


        Response resp =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth()
                        .preemptive()
                        .basic("venkateshkannanofficial@gmail.com", "ATATT3xFfGF0gt8mm0vnWghz1r3WOi-lLNTb8jfOvGUhQs0QMMSaCEmqb9oZioeUiMvrq1gP7wEd21f-gkN88qTR6y0muBhK9CGrRGPk-iGHbJQPoCU6l2AHxVKulK5glx0ET-EeCzOISdIhVEyaRjEGVkUZVVETOc0AXdbSn7KacJ72SHBDrls=67502A42")
                        .body(payload)
                        .post("https://venkateshkannanofficial.atlassian.net/rest/api/3/issue").then().log().all().extract().response();

        System.out.println(resp.asPrettyString());

        System.out.println("Code " + resp.statusCode());

        Assert.assertTrue(resp.statusCode() == 201);

    }

}
