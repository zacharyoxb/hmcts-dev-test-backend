package uk.gov.hmcts.reform.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.hmcts.reform.dev.models.Task;

/**
 * Spring JPA interface for the task_management database.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {}
