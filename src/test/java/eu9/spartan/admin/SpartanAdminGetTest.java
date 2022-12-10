package eu9.spartan.admin;


import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;

@SerenityTest
public class SpartanAdminGetTest {
    @BeforeAll
    public static void init(){
         baseURI="http://54.196.21.229:7000";
    }
    @Test
    public void getAllSpartan(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }
    @Test
    public void getoneSpartan(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}");
//                .then()
//                .statusCode(200)
//                .and()
//                .contentType(ContentType.JSON);

        System.out.println("lastResponse().statusCode() = " + lastResponse().statusCode());
        System.out.println("lastResponse().path(\"id\") = " + lastResponse().path("id"));
        String name= lastResponse().jsonPath().getString("name");
        System.out.println("name = " + name);
    }
    @Test
    public void getoneSpartanTest2(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}");
// In serenity assure is soft assure
        Ensure.that("Status code is 200",validatableResponse -> validatableResponse.statusCode(200));
        Ensure.that("Content type is Json",validatableResponse -> validatableResponse.contentType(ContentType.JSON));
        Ensure.that("Id is 15",vRes-> vRes.body("id", is(15)));

    }

}
