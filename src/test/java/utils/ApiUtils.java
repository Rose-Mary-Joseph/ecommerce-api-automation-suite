package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

    public static Response get(String endpoint) {
        return RestAssured.get(endpoint);
    }

    public static Response post(String endpoint, String body) {
        return RestAssured.given().header("Content-Type", "application/json").body(body).post(endpoint);
    }

    public static Response put(String endpoint, String body) {
        return RestAssured.given().header("Content-Type", "application/json").body(body).put(endpoint);
    }

    public static Response delete(String endpoint) {
        return RestAssured.delete(endpoint);
    }
}
