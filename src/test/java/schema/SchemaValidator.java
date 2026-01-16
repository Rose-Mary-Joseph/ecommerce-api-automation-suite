package schema;

import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidator {
    public static JsonSchemaValidator validateSchema(String schemaPath) {
        return JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath);
    }
}