package POJO;

import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class Spartans2 {
    @Test
    public void method1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().get("http://54.211.225.58:8000/api/spartans/{id}");
        response.prettyPrint();
        SpartanPojo spartanPojo= response.as(SpartanPojo.class);
        System.out.println(spartanPojo);
        System.out.println(spartanPojo.getId());
    }
}
