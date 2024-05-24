package StepDef;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import netscape.javascript.JSObject;
import org.hamcrest.Matchers;
import org.json.JSONObject;

public class MyStepdefs {
    @Given("prepare for website")
    public void prepareForWebsite() {
        RestAssured
                .given().when()
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("per_page", Matchers.equalTo(6));
    }


    @Given("create user for website")
    public void createUserForWebsite() {

        String valuename = "Bayusudiro";
        String valuejob = "QA";

        JSONObject bodyObj = new JSONObject();

        bodyObj.put("name", valuename);
        bodyObj.put("job", valuejob);

        RestAssured.given()
                .header("Content-Type", "application/json")
                .header("accept", "application/json").body(bodyObj.toString()).when()
                .post("https://reqres.in/api/users").then().log().all()
                .assertThat().statusCode(201)
                .assertThat().body("name", Matchers.equalTo(valuename));

    }
}
