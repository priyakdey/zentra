package com.priyakdey.com.zentra.service;

import com.priyakdey.com.zentra.model.dto.TaskDto;

import java.util.List;

/**
 * @author Priyak Dey
 */
public interface TaskService {

    TaskDto createTask(int accountId, TaskDto taskDto);

    List<TaskDto> getAllTasksFor(int accountId);

    void markTaskAsCompleted(int accountId, int taskId);
}
