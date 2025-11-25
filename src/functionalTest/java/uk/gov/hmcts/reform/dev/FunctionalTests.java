package uk.gov.hmcts.reform.dev;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import uk.gov.hmcts.reform.dev.models.TaskState;

import java.util.Date;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FunctionalTests {
    protected static final String CONTENT_TYPE_VALUE = "application/json";

    @Value("${TEST_URL:http://localhost:4000/create-task}")
    private String testUrl;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = testUrl;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    void makeNewTask() {
        String title = "Example";
        String description = "Example description";
        TaskState state = TaskState.NOT_COMPLETED;
        Date dueDateTime = new Date();

        given()
            .params("title", title,
                    "description", description,
                    "state", state,
                    "dueDateTime", dueDateTime
            )
            .contentType(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(201)
            .body("title", equalTo(title))
            .body("description", equalTo(description))
            .body("state", equalTo(state.toString()))
            .body("dueDateTime", equalTo(dueDateTime.toString()));
    }
}
