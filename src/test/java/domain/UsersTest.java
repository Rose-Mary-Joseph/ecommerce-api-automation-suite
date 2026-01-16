package domain;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import schema.SchemaValidator;
import utils.ApiUtils;

public class UsersTest extends BaseTest {

    @Test
    public void getAllUsers() {
        Response response = ApiUtils.get("/users");
        response.then().assertThat().statusCode(200)
                .body(SchemaValidator.validateSchema("schemas/user-schema.json"));
    }

    @Test
    public void getUserById() {
        Response response = ApiUtils.get("/users/1");
        response.then().assertThat().statusCode(200)
                .body(SchemaValidator.validateSchema("schemas/user-schema.json"));
    }

    @Test
    public void createUser() {
        String body = "{ \"email\": \"newuser@test.com\", \"username\": \"newuser\", \"password\": \"pass123\" }";
        Response response = ApiUtils.post("/users", body);
        int status = response.getStatusCode();
        Assert.assertTrue(status == 200 || status == 201, "Unexpected status code: " + status);

    }

    @Test
    public void updateUser() {
        String body = "{ \"username\": \"updateduser\" }";
        Response response = ApiUtils.put("/users/1", body);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteUser() {
        Response response = ApiUtils.delete("/users/1");
        response.then().assertThat().statusCode(200);
    }
}
