package Post;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AddSpartan {
    @DisplayName("POST new spartan using string")
    @Test
    public void addNewSpaprtanAsJsonType() {
        String jsonBody = " {\n" +
                "            \"name\": \"Paige\",\n" +
                "            \"gender\": \"Female\",\n" +
                "            \"phone\": 3786741487\n" +
                "        }";
       Response response= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("http://52.87.245.61:8000/api/spartans");

                response.prettyPrint();
        System.out.println(response.statusCode());
        assertThat(response.statusCode(), is(201));
        JsonPath jsonPath= response.jsonPath();
        assertThat(jsonPath.getString("success"),equalTo("A Spartan is Born!"));
        assertThat(jsonPath.getString("data.name"),equalTo("Paige"));
        assertThat(jsonPath.getString("data.gender"),equalTo("Female"));}

        @DisplayName("POST /spartans using map - SERIALITION")
        @Test
        public void addNewSpartanAsMapTest(){
            Map<String, Object> spartanPostMap = new HashMap<>();
            spartanPostMap.put("gender","Male");
            spartanPostMap.put("name","TestPost1");
            spartanPostMap.put("phone",1234567867);

            Response response = given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().body(spartanPostMap)
                    .when().post("http://52.87.245.61:8000/api/spartans");
        }
        @DisplayName("SPARTAN-API")
        @Test
        public void getAllSpartansHeaderTest(){
        when().get("http://54.211.225.58:8000/api/spartans").then()
                .assertThat().statusCode(200)
                .and().contentType(ContentType.JSON);
        }

}
