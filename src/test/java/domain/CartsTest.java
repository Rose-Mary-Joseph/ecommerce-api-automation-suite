package domain;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;
import schema.SchemaValidator;

public class CartsTest extends BaseTest {

    @Test
    public void getAllCarts() {
        Response response = ApiUtils.get("/carts");
        response.then().assertThat().statusCode(200)
                .body(SchemaValidator.validateSchema("schemas/cart-schema.json"));
    }

    @Test
    public void getCartById() {
        Response response = ApiUtils.get("/carts/1");
        response.then().assertThat().statusCode(200)
                .body(SchemaValidator.validateSchema("schemas/cart-schema.json"));
    }

    @Test
    public void createCart() {
        String body = "{ \"userId\": 1, \"products\": [{\"productId\":1,\"quantity\":2}]}";
        Response response = ApiUtils.post("/carts", body);
        int status = response.getStatusCode();
        Assert.assertTrue(status == 200 || status == 201, "Unexpected status code: " + status);

    }

    @Test
    public void updateCart() {
        String body = "{ \"products\": [{\"productId\":1,\"quantity\":5}]}";
        Response response = ApiUtils.put("/carts/1", body);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteCart() {
        Response response = ApiUtils.delete("/carts/1");
        response.then().assertThat().statusCode(200);
    }
}
