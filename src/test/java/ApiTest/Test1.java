package ApiTest;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import  org.junit.jupiter.api.*;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class Test1 {


    @Test
    public void pathMethod1(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",13)
                .get("http://54.211.225.58:8000/api/spartans/{id}");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.path("name").equals("Jaimie"));
        assertTrue(response.path("gender").equals("Female"));
        assertTrue((long)response.path("phone")==7842554879L);
        Map<String,Object> spartanMap = response.body().as(Map.class);
        System.out.println(spartanMap.keySet());
    }

    @Test
    public void jsonPathMethod(){
        given().accept(ContentType.JSON)
              .and().pathParam("country","us")
              .and().pathParam("postal-code","22102")
              .when().get("https://api.zippopotam.us/{country}/{postal-code}")
              .then().statusCode(200)
                .and().body("post code",is("22102"))
                .and().body("country",equalTo("United States"))
                .and().body("places[0].'place name'",is("Mc Lean"))
                .and().body("places[0].state",is("Virginia"));
                assertThat(1+3,is(4));
    }
    @Test
    public void test2(){
        String jsonBody = "{\n" +
                "     \"gender\": \"Male\",\n" +
                "     \"name\": \"TestPost1\",\n" +
                "     \"phone\": 1234567425\n" +
                "     }";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("http://54.211.225.58:8000/api/spartans");
                assertThat(response.statusCode(),is(201));
                assertThat(response.contentType(),is("application/json"));
                response.prettyPrint();
                Headers headers = response.getHeaders();
        System.out.println(headers);
    }
    @Test
    public void test3(){
        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("http://54.211.225.58:7000/api/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().log().all();
    }

    @Test
    public void jsonSchemaValidation(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://54.211.225.58:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanSchema.json")))
                .and().log().all();
    }

}
