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

    private List<TaskDto> inCompleteTasks;
    private List<TaskDto> completedTasks;

    public List<TaskDto> getInCompleteTasks() {
        return inCompleteTasks;
    }

    public void setInCompleteTasks(List<TaskDto> inCompleteTasks) {
        this.inCompleteTasks = inCompleteTasks;
    }

    public List<TaskDto> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(List<TaskDto> completedTasks) {
        this.completedTasks = completedTasks;
    }
}
