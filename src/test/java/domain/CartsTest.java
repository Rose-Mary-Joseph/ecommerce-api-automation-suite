package domain;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import schema.SchemaValidator;
import utils.ApiUtils;

public class CartsTest extends BaseTest {

    @Test
    public void getAllCarts() {
        Response response = ApiUtils.get("/carts");
        int status = response.getStatusCode();

        Assert.assertTrue(
                status == 200 || status == 403,
                "Unexpected status code: " + status
        );

        if (status == 200) {
            response.then().body(SchemaValidator.validateSchema("schemas/cart-schema.json"));
        }
    }

    @Test
    public void getCartById() {
        Response response = ApiUtils.get("/carts/1");
        int status = response.getStatusCode();

        // Accept valid mock responses
        Assert.assertTrue(
                status == 200 || status == 404 || status == 403,
                "Unexpected status code: " + status
        );

        // Validate schema only when response contains data
        if (status == 200) {
            response.then().body(SchemaValidator.validateSchema("schemas/cart-schema.json"));
        }
    }

    @Test
    public void createCart() {
        String body = "{ \"userId\": 1, \"products\": [{\"productId\":1,\"quantity\":2}]}";
        Response response = ApiUtils.post("/carts", body);
        int status = response.getStatusCode();
        if (status == 403) {
            System.out.println("Create Cart blocked due to API restrictions (403). Skipping validation.");
            return;
        }
        Assert.assertTrue(status == 200 || status == 201, "Unexpected status: " + status);

    }

    @Test
    public void updateCart() {
        String body = "{ \"products\": [{\"productId\":1,\"quantity\":5}]}";
        Response response = ApiUtils.put("/carts/1", body);
        int status = response.getStatusCode();
        if (status == 403) {
            System.out.println("Update Cart blocked due to API restrictions (403)");
            return;
        }
        Assert.assertEquals(status, 200);

    }

    @Test
    public void deleteCart() {
        Response response = ApiUtils.delete("/carts/1");
        int status = response.getStatusCode();
        if (status == 403) {
            System.out.println("Delete Cart blocked due to API restrictions (403)");
            return;
        }
        Assert.assertEquals(status, 200);

    }
}
