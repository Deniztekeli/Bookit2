import com.bookit.utilities.BookItApiUtil;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.Environment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class ApiSteyDefs {

    String token;
    Response response;

    @Given("I logged Bookit api using {string} and {string}")
    public void iLoggedBookitApiUsingAnd(String email, String password) {

        token = BookItApiUtil.generateToken(email, password);

    }

    @When("I get the current user information from api")
    public void iGetTheCurrentUserInformationFromApi() {
        System.out.println("token = " + token);
        //send a GET request "/api/users/me" endpoint to get current user info

        response = given().accept(ContentType.JSON)
                .and()
                .header("Authorization", token)
                .when()
                .get(ConfigurationReader.get("qa2api.url")+ "/api/users/me");
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());
    }

    @Then("the information about current user from api and database should match")
    public void theInformationAboutCurrentUserFromApiAndDatabaseShouldMatch() {
    }

    @Given("user logs in using {string} {string}")
    public void userLogsInUsing(String arg0, String arg1) {
    }

    @And("user is on the my self page")
    public void userIsOnTheMySelfPage() {
    }

    @Then("UI,API and Database user information must be match")
    public void uiAPIAndDatabaseUserInformationMustBeMatch() {
    }
}
