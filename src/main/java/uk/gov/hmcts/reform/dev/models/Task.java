package uk.gov.hmcts.reform.dev.models;

import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Type to represent a Task.
 * All attributes are required in the constructor
 * apart from description
 * */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    private final String title;
    private String description = "";
    private final TaskState status;
    private final Date dueDate;
}
