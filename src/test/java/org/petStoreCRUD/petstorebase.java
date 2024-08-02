package org.petStoreCRUD;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class petstorebase {
   public RequestSpecification req;
    @BeforeClass
    public void beforesetup(){
        System.out.println("****** Generating RequestSpecification ***********");
        req= new RequestSpecBuilder().log(LogDetail.ALL).setBaseUri("https://petstore.swagger.io/v2").setContentType(ContentType.JSON).build();
        System.out.println("****** Generated RequestSpecification ***********");
    }
}
