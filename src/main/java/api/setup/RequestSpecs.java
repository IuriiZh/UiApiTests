package api.setup;

import api.model.ReportTemplate;
import api.model.requests.LoginJson;
import api.model.requests.RegisterJson;
import io.restassured.response.Response;
import io.restassured.specification.*;
import utils.PropertyReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static utils.PropertyReader.*;

public class RequestSpecs {

    private static final long responseTime = 5000;

    public static RequestSpecification request(int status) {
        return given()
                .baseUri(ApiUrl)
                .header(ApiKeyHeader, ApiKey)
                .header("Content-type", "application/json")
                .filter(ReportTemplate.filters().customTemplates());
    }

    public static void login(String email, String password, int status, String errMsg) {
        Response response = request(status)
                .body(new LoginJson(email, password))
                .post("api/login")
                .then()
                .extract()
                .response();
        assertThat(response.statusCode(), equalTo(status));
        if (status == 200) {
            api.model.responses.LoginJson loginJson = response.as(api.model.responses.LoginJson.class);
            assertThat(loginJson.getToken(), not(emptyOrNullString()));
        } else if (status == 400) {
            assertThat(response.jsonPath().getString("error"), equalTo(errMsg));
        }
    }

    public static void register(String username, String email, String password, int status, String errMsg) {
            Response response = request(status)
                    .body(new api.model.requests.RegisterJson(username, email, password))
                    .post("api/register")
                    .then()
                    .extract()
                    .response();
            assertThat(response.statusCode(), equalTo(status));
            if (status == 200) {
                api.model.responses.RegisterJson registerJson = response.as(api.model.responses.RegisterJson.class);
                assertThat(registerJson.getId().toString(), equalTo(PropertyReader.ApiId));
            } else if (status == 400) {
                assertThat(response.jsonPath().getString("error"), equalTo(errMsg));
            }
    }
}
