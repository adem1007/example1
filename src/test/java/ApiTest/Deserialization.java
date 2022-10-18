package ApiTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import POJO.Spartans;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.*;
public class Deserialization {
    @Test
    public void test1(){
        /**
         Given accept type is application/json
         And Path param id = 10
         When i send GET request to /api/spartans
         Then status code is 200
         And content type is json
         And spartan data matching:
         id > 10
         name>Lorenza
         gender >Female
         phone >3312820936
         */
                    Response response=given().accept(ContentType.JSON)
                    .and().pathParam("id",10)
                    .when().get("http://52.87.245.61:8000/api/spartans/{id}");


            Map<String,Object>allSpartans = response.body().as(Map.class);
            System.out.println(allSpartans);
        Spartans spartans1 = response.body().as(Spartans.class);
        System.out.println(spartans1.getGender());

    }
    @Test
    public void test2(){
        List<String> list = Arrays.asList("ahmet","salih","osman"," ","");
        assertThat(list, hasSize(5));
        assertThat(list.get(0), startsWithIgnoringCase("ahmet"));
        assertThat(list.get(3),blankOrNullString());
    }
}
