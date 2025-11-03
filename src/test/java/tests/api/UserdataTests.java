package tests.api;

import api.model.responses.UserdataJson;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import java.util.List;
import static api.util.JsonToStringConverter.convertJsonToString;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static api.setup.RequestSpecs.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API Resource Management")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API:User schema tests")
@Tag("api")
public class UserdataTests {

    @Story("As an API consumer, I want user list data to match expected format")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User Data Retrieval")
    @Test
    @Order(1)
    @DisplayName("User list Json Scheme Validation GET")
    @Description("Tests for user list Json Scheme Validation GET")
    public void userListJsonSchemeValidation() {
        request(200)
            .when()
            .get("api/users?page=2")
            .then()
            .body(matchesJsonSchemaInClasspath("reqres/json_scheme/userdata_list.json"));
    }

    @Story("As an API consumer, I want user data to match expected format")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User Data Retrieval")
    @Test
    @Order(2)
    @DisplayName("Single user Json Scheme Validation GET")
    @Description("Tests for single user Json Scheme Validation GET")
    public void singleUserJsonSchemeValidation() {
        request(200)
            .when()
            .get("api/users/2")
            .then()
            .body(matchesJsonSchemaInClasspath("reqres/json_scheme/single_user.json"));
    }

    @Story("As an API consumer, I want to get single user details")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User Data Retrieval")
    @Test
    @Order(3)
    @DisplayName("Single User GET")
    @Description("Tests for single user GET")
    public void singleUserTest() {
        request(200)
                .when()
                .get("api/users/2")
                .then()
                .body("data.size()", equalTo(5));
    }

    @Story("As an API consumer, I want consistent data across requests")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User Data Retrieval")
    @Test
    @Order(4)
    @DisplayName("User data List GET")
    @Description("Tests for user data List GET")
    public void userdataListTests() {
        List<UserdataJson> users = request(200)
                .when()
                .get("api/users?page=2")
                .then()
                .extract()
                .jsonPath().getList("data", UserdataJson.class);
        users.forEach(x -> assertTrue(x.getEmail().matches("\\w+\\.\\w+@reqres\\.in")));
        users.forEach(x -> assertTrue(x.getAvatar().matches("https://reqres.in/img/faces/\\d+-image\\.\\w+")));
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
    }

    @Story("As an API consumer, I want to retrieve user list with pagination")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User Data Retrieval")
    @Test
    @Order(5)
    @DisplayName("User data Equity GET")
    @Description("Tests for user data Equity GET")
    public void userdataEquityTests() {
        String userDataPage1 = request(200)
                .when()
                .get("api/users?page=1")
                .then()
                .extract()
                .body().asString().replace(" ", "");
        String userDataPage2 = request(200)
                .when()
                .get("api/users?page=2")
                .then()
                .extract()
                .body().asString().replace(" ", "");
        assertEquals(userDataPage1, convertJsonToString("src/test/resources/reqres/test_data/td1.json"));
        assertEquals(userDataPage2, convertJsonToString("src/test/resources/reqres/test_data/td2.json"));
    }
}
