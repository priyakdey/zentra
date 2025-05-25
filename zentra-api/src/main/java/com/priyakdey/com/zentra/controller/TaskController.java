package com.priyakdey.com.zentra.controller;

import com.priyakdey.com.zentra.exception.InvalidRequestException;
import com.priyakdey.com.zentra.model.dto.TaskDto;
import com.priyakdey.com.zentra.model.request.NewTaskRequest;
import com.priyakdey.com.zentra.model.response.TaskResponse;
import com.priyakdey.com.zentra.model.response.TasksResponse;
import com.priyakdey.com.zentra.service.TaskService;
import com.priyakdey.com.zentra.util.validator.core.NewTaskRequestValidator;
import com.priyakdey.com.zentra.util.validator.core.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author Priyak Dey
 */
@RestController
@RequestMapping(path = "/v1/me/tasks", consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody NewTaskRequest newTaskRequest,
                                                   Principal principal) {
        ValidationResult result = NewTaskRequestValidator.isValidTitle()
                .and(NewTaskRequestValidator.isValidCompletionDate())
                .apply(newTaskRequest);
        if (!result.isSuccess()) {
            throw new InvalidRequestException(result.message());
        }

        TaskDto taskDto = TaskDto.from(newTaskRequest);
        int accountId = Integer.parseInt(principal.getName());
        taskDto = taskService.createTask(accountId, taskDto);
        URI location = URI.create("/v1/me/tasks/" + taskDto.id());
        TaskResponse taskResponse = TaskResponse.from(taskDto);
        return ResponseEntity.created(location).body(taskResponse);
    }

    @GetMapping
    public ResponseEntity<TasksResponse> getTasks(Principal principal) {
        List<TaskDto> allTasks = taskService.getAllTasksFor(Integer.parseInt(principal.getName()));
        TasksResponse tasksResponse = new TasksResponse();
        tasksResponse.setTasks(allTasks);
        return ResponseEntity.ok(tasksResponse);
    }

    @PatchMapping("{taskId}")
    public ResponseEntity<Void> markTaskAsCompleted(@PathVariable("taskId") int taskId,
                                                    Principal principal) {
        int accountId = Integer.parseInt(principal.getName());
        taskService.markTaskAsCompleted(accountId, taskId);
        return ResponseEntity.noContent().build();
    }

}
