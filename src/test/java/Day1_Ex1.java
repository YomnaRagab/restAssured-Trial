import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Day1_Ex1 {
public String base ="https://reqres.in/";
    @Test
    public void expected200() {
        given().
                baseUri(base).
                when().
                get("api/usres").
                then().assertThat().statusCode(200);
    }


    @Test
    public void request_logs() {
        given().
                baseUri(base).
                when().
                get("api/users").then().log().all();
    }
    @Test
    public void all_tokens(){
        given().log().all().
                baseUri(base).
                and().
                queryParam("email","yomna@gmail.com").
                and().
                queryParam("first_name","yomna").
                and().
                pathParam("user_no",3).
                and().
                header("Accept","*/*").
                when().
                get("api/users/{user_no}").then().log().all();
    }
    @Test
    public void put_user(){
        given().log().all().
                baseUri(base).
                and().
                contentType(ContentType.JSON).
                and().
                body(
                        "    \"name\": \"morpheus\",\n" +
                                "    \"job\": \"zion resident\""
                ).
                when().
                put("api/users").
                then().log().all();
    }
}