package uk.gov.hmcts.reform.dev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.models.TaskState;

import java.util.Date;

@RestController
public class TaskController {

    @GetMapping(value = "/create-task", produces = "application/json")
    public Task createTask() {
        return new Task("Example", "Description", TaskState.NOT_COMPLETED, new Date());
    }
}
