package uk.gov.hmcts.reform.dev;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FunctionalTests {
    protected static final String CONTENT_TYPE_VALUE = "application/json";

    @Value("${TEST_URL:http://localhost:4000}")
    private String testUrl;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = testUrl;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    void makeNewTask() {
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("title", "Example");
        taskMap.put("status", "NOT_COMPLETED");
        taskMap.put("description", "Example description");
        taskMap.put("dueDate", new Date());

        given()
            .contentType(ContentType.JSON)
            .body(taskMap)
            .when()
            .post("/create-task")
            .then()
            .statusCode(201)
            .body("title", equalTo("Example"))
            .body("description", equalTo("Example description"))
            .body("status", equalTo("NOT_COMPLETED"));
    }
}
