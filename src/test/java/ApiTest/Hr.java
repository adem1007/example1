package ApiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Hr {
    String url="http://52.87.245.61:1000/ords/hr";
    @Test
    public void test1(){
        /*- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2*/
       Response response= RestAssured.given().accept(ContentType.JSON)
              .when().get(url+"/countries/US");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        JsonPath jsonPath=response.jsonPath();
        assertEquals("US",jsonPath.getString("country_id"));
        assertEquals("United States of America",jsonPath.getString("country_name"));
        assertEquals(2,jsonPath.getInt("region_id"));

    }
  /*  Q2:
            - Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
            - And Content - Type is Json
- And all job_ids start with 'SA'
            - And all department_ids are 80
            - Count is 25*/
    @Test
    public void test2(){
        Response response=RestAssured.given().accept(ContentType.JSON).and()
                .queryParam("department_id",80).when().get(url+"/employees");
            assertEquals(200,response.statusCode());
            assertEquals("application/json",response.contentType());
            JsonPath jsonPath=response.jsonPath();
              List<Object> listOfJobIds=jsonPath.getList("job_id");

    }

}
