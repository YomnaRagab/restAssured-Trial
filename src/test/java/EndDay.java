import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class EndDay {
    String base = "https://reqres.in/";
    RequestSpecification sepcObject;
    ResponseSpecification responseTemplate;
    @DataProvider(name = "dataReader")
    public static Object[][] dataReader() {
        return new Object[][]{
        {
            "2", "Janet","janet.weaver@reqres.in"
        },
        {
            "3", "Emma","emma.wong@reqres.in"
        }
    };

}
@BeforeClass
public void setSpec(){
        sepcObject = new RequestSpecBuilder().setBaseUri(base).build();
        responseTemplate= new ResponseSpecBuilder().expectStatusCode(200).build();
}

    @Test(dataProvider="dataReader")
    public void request_query_path_parameters(String ID,String fName,String email) {


    Response response = given().
                log().all().
                spec(sepcObject).
            and().pathParam("userID",ID).
            when().
            get("api/users/{userID}").
            then().
            spec(responseTemplate).
            extract().response();
            JsonPath path= response.jsonPath();

        Assert.assertEquals(path.get("data.email"),email);
        Assert.assertEquals(path.get("data.first_name"),fName);
}



}
