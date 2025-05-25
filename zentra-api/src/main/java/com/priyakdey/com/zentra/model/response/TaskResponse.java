package com.priyakdey.com.zentra.model.response;

import com.priyakdey.com.zentra.model.dto.TaskDto;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Priyak Dey
 */
public class TaskResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 4188710224826133702L;

    private Integer id;
    private String title;
    private boolean isCompleted;
    private ZonedDateTime tentativeCompletionDate;
    private ZonedDateTime createdAt;
    private ZonedDateTime completedAt;

    public static TaskResponse from(TaskDto taskDto) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(taskDto.id());
        taskResponse.setTitle(taskDto.title());
        taskResponse.setCompleted(taskDto.isCompleted());
        taskResponse.setTentativeCompletionDate(taskDto.tentativeCompletionDate());
        taskResponse.setCreatedAt(taskDto.createdAt());
        taskResponse.setCompletedAt(taskDto.completedAt());
        return taskResponse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public ZonedDateTime getTentativeCompletionDate() {
        return tentativeCompletionDate;
    }

    public void setTentativeCompletionDate(ZonedDateTime tentativeCompletionDate) {
        this.tentativeCompletionDate = tentativeCompletionDate;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(ZonedDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
