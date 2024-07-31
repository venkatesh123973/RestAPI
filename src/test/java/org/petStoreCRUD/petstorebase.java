package org.petStoreCRUD;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class petstorebase {
    RequestSpecification req;
    @Test
    public void beforesetup(){
        req= new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io").build();
    }
}
