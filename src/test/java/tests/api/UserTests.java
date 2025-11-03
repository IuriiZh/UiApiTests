package tests.api;

import api.model.requests.CreateUserJson;
import api.model.responses.CreatedUserJson;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.*;
import static org.hamcrest.Matchers.equalTo;
import static utils.PropertyReader.*;

@Epic("API User Management")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API:Users tests")
@Tag("api")
public class UserTests {

    @Story("As a user, I want to register with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User Registration")
    @Test
    @Order(1)
    @DisplayName("Register successful POST")
    @Description("Register successful functionality POST")
    @Tag("g")
    public void successfulRegister() {
        register("", ApiEmail, ApiPassword, 200, "");
    }

    @Story("As a user, I should receive validation errors for incomplete registration")
    @Feature("User Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Order(2)
    @DisplayName("Register unsuccessful: Missing username POST")
    @Description("Register unSuccessful: Missing username functionality POST")
    public void unSuccessfulRegisterNoUsername() {
        register("", ApiEmail, ApiPassword, 400, "Missing username");
    }

    @Story("As a user, I should receive validation errors for incomplete registration")
    @Feature("User Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Order(3)
    @DisplayName("Register unsuccessful: Missing email POST")
    @Description("Register unSuccessful: Missing email functionality POST")
    public void unSuccessfulRegisterNoEmail() {
        register(ApiUsername, "", ApiPassword, 400, "Note: Only defined users succeed registration");
    }

    @Story("As a user, I should receive validation errors for incomplete registration")
    @Feature("User Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Order(4)
    @DisplayName("Register unsuccessful: Missing username and email POST")
    @Description("Register unSuccessful: Missing username and email functionality POST")
    public void unSuccessfulRegisterNoUsernameAndEmail() {
        register("", "", ApiPassword, 400, "Missing email or username");
    }

    @Story("As a user, I should receive validation errors for incomplete registration")
    @Feature("User Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Order(5)
    @DisplayName("Register unsuccessful: Missing password POST")
    @Description("Register unsuccessful: Missing password functionality POST")
    public void unSuccessfulRegisterNoPassword() {
        register(ApiUsername, ApiEmail, "", 400, "Missing password");
    }

    @Story("As an admin, I want to perform create user")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User CRUD Operations")
    @Test
    @Order(6)
    @DisplayName("Create User POST")
    @Description("Tests for create user functionality POST")
    @Tag("g")
    public void createUserTests() {
        String name= "user1", job = "worker";
        request(201)
                .body(new CreateUserJson(name, job))
                .when()
                .post("api/users")
                .then()
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }

    @Story("As an admin, I want to perform update user")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User CRUD Operations")
    @Test
    @Order(7)
    @DisplayName("Update user PUT")
    @Description("Tests for update user functionality PUT")
    public void updateUserTests() {
        String name= "user1", newJob = "worker";
        request(200)
            .body(new CreateUserJson(name, newJob))
            .when()
            .put("api/users/2")
            .then()
            .body("name", equalTo(name))
            .body("job", equalTo(newJob));
    }

    @Story("As an admin, I want to perform update user in chain")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User CRUD Operations")
    @Test
    @Order(8)
    @DisplayName("Update user chained")
    @Description("Tests for update user in chain functionality")
    @Tag("g")
    public void updatedUserChainedTests() {
        String name= "user1", job = "worker";
        CreatedUserJson createdUser =
        request(201)
                .body(new CreateUserJson(name, job))
                .when()
                .post("api/users")
                .then()
                .extract()
                .as(CreatedUserJson.class);
        request(200)
                .when()
                .get("api/users/"+createdUser.getId())
                .then()
                .body("name", equalTo(name))
                .body("job", equalTo(job));
        String newJob = "manager";
        request(200)
                .body(new CreateUserJson(name, newJob))
                .when()
                .put("api/users/"+createdUser.getId())
                .then()
                .body("name", equalTo(name))
                .body("job", equalTo(newJob));
    }

    @Story("As an admin, I want to perform delete user")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("User CRUD Operations")
    @Test
    @Order(9)
    @DisplayName("delete User DELETE")
    @Description("Tests for delete user DELETE")
    public void deleteUserTest() {
        request(204)
                .when()
                .delete("api/users/2")
                .then()
                .statusCode(204);
        request(404)
                .when()
                .get("api/users/2")
                .then()
                .statusCode(404);
    }

}
