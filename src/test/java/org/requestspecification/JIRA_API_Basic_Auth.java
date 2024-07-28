package org.requestspecification;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JIRA_API_Basic_Auth {

    @Test
    public void getProjectDetails() {

        Response resp =
                given()
                        .auth()
                        .preemptive()
                        .basic("venkateshkannanofficial@gmail.com", "ATATT3xFfGF0gt8mm0vnWghz1r3WOi-lLNTb8jfOvGUhQs0QMMSaCEmqb9oZioeUiMvrq1gP7wEd21f-gkN88qTR6y0muBhK9CGrRGPk-iGHbJQPoCU6l2AHxVKulK5glx0ET-EeCzOISdIhVEyaRjEGVkUZVVETOc0AXdbSn7KacJ72SHBDrls=67502A42")
                        .get("https://venkateshkannanofficial.atlassian.net/rest/api/3/project/KAN");

        Assert.assertTrue(resp.statusCode() == 200);

        System.out.println(resp.statusCode());

        //System.out.println(resp.asPrettyString());

    }

    @Test(dependsOnMethods = "getProjectDetails")
    public void getNumberOfIssues() {
        Response resp =
                given()
                        .auth()
                        .preemptive()
                        .basic("venkateshkannanofficial@gmail.com", "ATATT3xFfGF0gt8mm0vnWghz1r3WOi-lLNTb8jfOvGUhQs0QMMSaCEmqb9oZioeUiMvrq1gP7wEd21f-gkN88qTR6y0muBhK9CGrRGPk-iGHbJQPoCU6l2AHxVKulK5glx0ET-EeCzOISdIhVEyaRjEGVkUZVVETOc0AXdbSn7KacJ72SHBDrls=67502A42")
                        .get("https://venkateshkannanofficial.atlassian.net/rest/api/3/search");

        Assert.assertTrue(resp.statusCode() == 200);

        //System.out.println(resp.asPrettyString());
    }


    @Test(dependsOnMethods = "getNumberOfIssues")
    public void createIssue() {
        Response resp =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .auth()
                        .preemptive()
                        .basic("venkateshkannanofficial@gmail.com", "ATATT3xFfGF0gt8mm0vnWghz1r3WOi-lLNTb8jfOvGUhQs0QMMSaCEmqb9oZioeUiMvrq1gP7wEd21f-gkN88qTR6y0muBhK9CGrRGPk-iGHbJQPoCU6l2AHxVKulK5glx0ET-EeCzOISdIhVEyaRjEGVkUZVVETOc0AXdbSn7KacJ72SHBDrls=67502A42")
                        .body(new File("./src/test/resources/JSONFiles/issue.json"))
                        .post("https://venkateshkannanofficial.atlassian.net/rest/api/3/issue").then().log().all().extract().response();

        System.out.println(resp.asPrettyString());

        System.out.println("Code " + resp.statusCode());

        Assert.assertTrue(resp.statusCode() == 201);

    }

}

