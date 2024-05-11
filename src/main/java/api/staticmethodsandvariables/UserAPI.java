package api.staticmethodsandvariables;
import io.restassured.response.Response;
import api.dto.*;
import ui.data.*;

import static io.restassured.RestAssured.given;

public class UserAPI extends StellarBurgersAPI {
    private static final String handleForCreateUser = "api/auth/register";
    private static final String handleForDeleteUser = "api/auth/user";
    private static final String handleForLoginUser = "api/auth/login";
    public Response createUserMaria() {
        return createUser(Registration.EMAIL, Registration.PASSWORD, Registration.NAME);
    }

    public Response deleteUser (String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete(handleForDeleteUser);
        return response;
    }
    public Response loginUserMaria() {
        return loginUser(Registration.EMAIL, Registration.PASSWORD);
    }
    private Response createUser(String givenEmail, String givenPassword, String givenName) {
        CreateUserRequest newUser = new CreateUserRequest(givenEmail, givenPassword, givenName);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(newUser)
                .when()
                .post(handleForCreateUser);
        return response;
    }
    public Response loginUser(String givenEmail, String givenPassword) {
        LoginUserRequest newUser = new LoginUserRequest(givenEmail, givenPassword);
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(newUser)
                .when()
                .post(handleForLoginUser);
        return response;
    }
}