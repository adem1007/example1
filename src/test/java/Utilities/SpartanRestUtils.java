package Utilities;

import static io.restassured.RestAssured.*;

public class SpartanRestUtils {

    public static void deleteSpartanById(int spartanID){
        String baseUrl ="http://52.87.245.61:8000/api/spartans";
        given().pathParam("id",spartanID)
                .when().delete(baseUrl+"/{id}")
                .then().log().all();
    }
}
