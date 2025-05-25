package com.priyakdey.com.zentra.model.response;

import com.priyakdey.com.zentra.model.dto.TaskDto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author Priyak Dey
 */
public class TasksResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -4165136856955649015L;

    private List<TaskDto> tasks;

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
