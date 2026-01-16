package domain;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;
import schema.SchemaValidator;

public class ProductsTest extends BaseTest {

    @Test
    public void getAllProducts() {
        Response response = ApiUtils.get("/products");
        response.then().assertThat().statusCode(200)
                .body(SchemaValidator.validateSchema("schemas/product-schema.json"));
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }

    @Test
    public void getProductById() {
        Response response = ApiUtils.get("/products/1");
        response.then().assertThat().statusCode(200)
                .body(SchemaValidator.validateSchema("schemas/product-schema.json"));
    }

    @Test
    public void createProduct() {
        String body = "{ \"title\": \"New Product\", \"price\": 19.99, \"description\": \"Test product\", \"category\": \"electronics\" }";
        Response response = ApiUtils.post("/products", body);
        int status = response.getStatusCode();
        Assert.assertTrue(status == 200 || status == 201, "Unexpected status code: " + status);

    }

    @Test
    public void updateProduct() {
        String body = "{ \"title\": \"Updated Product\", \"price\": 25.99 }";
        Response response = ApiUtils.put("/products/1", body);
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Product");
    }

    @Test
    public void deleteProduct() {
        Response response = ApiUtils.delete("/products/1");
        response.then().assertThat().statusCode(200);
    }
}
