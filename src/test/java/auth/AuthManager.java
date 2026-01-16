package auth;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthManager {
    public static String getToken(String username, String password) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
                .post("/auth/login");

        return response.jsonPath().getString("token");
    }
}