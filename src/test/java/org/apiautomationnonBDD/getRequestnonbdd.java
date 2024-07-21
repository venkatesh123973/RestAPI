package org.apiautomationnonBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class getRequestnonbdd {

    @Test
    public void getnonbdd() {
        Response resp = RestAssured.get("https://reqres.in/api/users/2");

        System.out.println(resp.asString());

        System.out.println(resp.asPrettyString());

        System.out.println(resp.contentType());

        System.out.println(resp.getStatusCode());

        System.out.println(resp.statusLine());

        System.out.println(resp.getTimeIn(TimeUnit.SECONDS));

        System.out.println(resp.cookies());

        System.out.println(resp.headers());

    }

    @Test
    public void statuscode() throws InterruptedException {
        while (true) {
            int code = RestAssured.get("https://reqres.in/api/users/2").getStatusCode();

            Thread.sleep(1000);

            System.out.println("API Current status " + code);

            if (code == 200) {

            } else {
                //
            }
        }
    }
}
