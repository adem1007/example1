package ApiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PathParameter {
    @BeforeAll
    public static  void setupClass(){
        RestAssured.baseURI="http://52.87.245.61:8000";
    }
    @Test
    public void test1(){
       Response response= RestAssured.given().accept(ContentType.JSON).when().pathParam("id",18)
                .when().get("/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Allen"));
        response.body().prettyPrint();
    }
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",500).when().get("/api/spartans/{id}");
        assertEquals(404,response.statusCode());
        assertEquals("application/json",response.contentType());
        response.prettyPrint();

    }
    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON).and()
                .queryParam("gender","Female").queryParam("nameContains","J")
                .when().get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));


        Map<String,Object> params = new HashMap<>();
        params.put("gender","female");
        params.put("nameContains","j");

        RestAssured.given().accept(ContentType.JSON).and().queryParams(params)
                .when().get("/api/spartans/search");
    }
    @Test
    public void test4(){
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("id",10).
                when().get("/api/spartans/{id}");
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
    }
    @Test
    public void test5(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",11).when().get("/api/spartans/{id}");

        JsonPath jsondata= response.jsonPath();
        System.out.println(jsondata.getInt("id"));
        jsondata.getString("name");
    }
}
