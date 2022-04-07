package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
public class LoanTest {

    static {
      RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testBasePrice() {
        given()
          .body("{\r\n  \"Prospect\": {\r\n    \"credit score\": 200,\r\n    \"income\": 50000\r\n  },\r\n  \"Loan\": {\r\n    \"loan amount\": 5000,\r\n    \"months\": 48,\r\n    \"rate\": 2.8\r\n  }\r\n}")
          .contentType(ContentType.JSON)
          .when()
            .post("/loan")
          .then()
            .statusCode(200)
            .body("Approval", is("fasttrack"));
    }
}
