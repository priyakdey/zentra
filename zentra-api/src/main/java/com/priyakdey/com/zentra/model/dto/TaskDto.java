package com.priyakdey.com.zentra.model.dto;

import com.priyakdey.com.zentra.domain.Task;
import com.priyakdey.com.zentra.model.request.NewTaskRequest;

import java.time.ZonedDateTime;

/**
 * @author Priyak Dey
 */
public record TaskDto(int id, String title, boolean isCompleted,
                      ZonedDateTime tentativeCompletionDate, ZonedDateTime createdAt,
                      ZonedDateTime completedAt) {

    public static TaskDto from(NewTaskRequest newTaskRequest) {
        return new TaskDto(-1, newTaskRequest.getTitle(), false,
                newTaskRequest.getTentativeCompletionDate(), null, null);
    }

    public static TaskDto from(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getCompleted(),
                task.getTentativeCompletionDate(), task.getCreatedAt(), task.getCompletedAt());
    }

}
