package ApiTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured.*;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanTest {
   String spartanBaseUrl="http://52.87.245.61:8000";
    @Test
    public void viewSpartanTest1(){
        Response response =get(spartanBaseUrl+"/api/spartans");

        System.out.println(response.statusCode());

        System.out.println(response.body().asString());

        System.out.println(response.body().prettyPrint());


    }
    @Test
    public void viewSpartanTest2(){
        Response response=get(spartanBaseUrl+"/api/spartans");
        assertEquals(200,response.statusCode(),"Status code failed");
        assertTrue(response.body().asString().contains("Allen"));
    }
    @Test
    public void viewSpartanTest3(){
        Response response= given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl+"/api/spartans");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
    }
    @Test
    public void getSingleSpartan(){
      Response response= given().accept(ContentType.JSON).
                and().pathParam("id",5).
                when().get("http://54.211.225.58:8000/api/spartans/{id}");
                response.prettyPrint();
    }

    @Test
    public void checkSpartan(){
            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("id",500)
                    .when().get("http://54.211.225.58:8000/api/spartans/{id}");
                    assertEquals(404,response.statusCode());
                    assertEquals("application/json",response.contentType());
                    assertTrue(response.asString().contains("Not Found"));
    }
    @Test
    public void queryParam(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get("http://54.211.225.58:8000/api/spartans/search");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));
    }

    @Test
    public void readCountriesUsingPathMethod(){
        Response response= given().accept(ContentType.JSON)
                .when().get("http://54.211.225.58:1000/ords/hr/countries");
        System.out.println(response.path("items[0].country_id").toString());
        List<String> countries= response.path("items.country_name");
        System.out.println(countries);
    }
}
