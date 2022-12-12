package eu9.spartan.editor;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanNewBase;
import utilities.SpartanUtil;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.rest.SerenityRest.given;

@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {

    @DisplayName("Editor should be able to Post")
    @Test
    public void postSpartanAsEditor(){
        Map<String,Object> bodyMap= SpartanUtil.getRandomSpartanMap();
        System.out.println("bodyMap = " + bodyMap);

        given()
                .auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
                .when()
                .post("/spartans")
                .prettyPrint();

        Ensure.that("Status code is 201",x->x.statusCode(201));
        Ensure.that("Content type is Json",vR->vR.contentType(ContentType.JSON));
        Ensure.that("Success message is correct",
                thenpart-> thenpart.body("success",is("A Spartan is Born!")));
        Ensure.that("id is not null",id->id.body("data.id",notNullValue()));
        Ensure.that("name is correct",name->name.body("data.name",is(bodyMap.get("name"))));
        Ensure.that("gender is correct",gender->gender.body("data.gender",is(bodyMap.get("gender"))));
        Ensure.that("phone is correct",phone->phone.body("data.phone",is(bodyMap.get("phone"))));
        // this part is important
        String id= lastResponse().jsonPath().getString("data.id");
        Ensure.that("Check the location heaader end with newly generated id",
                validatableResponse -> validatableResponse.header("Location",endsWith(id)));

    }






}
