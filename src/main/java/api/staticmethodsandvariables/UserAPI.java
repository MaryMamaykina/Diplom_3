package api.staticmethodsandvariables;

import data.UserWithPassword;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI extends StellarBurgersAPI {
    private static final String handleForCreateUser = "api/auth/register";
    private static final String handleForDeleteUser = "api/auth/user";
    private static final String handleForLoginUser = "api/auth/login";


    public Response deleteUser (String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete(handleForDeleteUser);
        return response;
    }
    public Response createUser(UserWithPassword user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(handleForCreateUser);
        return response;
    }
    public Response loginUser(UserWithPassword user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(handleForLoginUser);
        return response;
    }
}