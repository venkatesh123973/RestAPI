package org.requestspecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class Base {

    public RequestSpecification req;

   public ResponseSpecification resp_spec_200;

   public ResponseSpecification resp_spec_201;

    @BeforeClass
    public void setup()
    {
        System.out.println("****** Generating RequestSpecification ***********");

        req=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();

        System.out.println("****** Generated RequestSpecification ***********");

        System.out.println("****** Generating ResponseSpecification ***********");

        resp_spec_200=new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();

        resp_spec_201=new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(201)
                .build();

        System.out.println("****** Generated ResponseSpecification ***********");

    }


}
