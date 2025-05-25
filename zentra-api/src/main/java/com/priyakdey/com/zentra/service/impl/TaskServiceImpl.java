package com.priyakdey.com.zentra.service.impl;

import com.priyakdey.com.zentra.domain.Account;
import com.priyakdey.com.zentra.domain.Task;
import com.priyakdey.com.zentra.exception.TaskNotFoundException;
import com.priyakdey.com.zentra.model.dto.TaskDto;
import com.priyakdey.com.zentra.repository.TaskRepository;
import com.priyakdey.com.zentra.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Priyak Dey
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public TaskDto createTask(int accountId, TaskDto taskDto) {
        Account hollowAccount = new Account();
        hollowAccount.setId(accountId);

        Task task = new Task(taskDto.title(), hollowAccount);
        task.setCompleted(taskDto.isCompleted());
        task.setTentativeCompletionDate(taskDto.tentativeCompletionDate());

        task = taskRepository.save(task);
        return TaskDto.from(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto>[] getAllTasksFor(int accountId) {
        Account hollowAccount = new Account();
        hollowAccount.setId(accountId);

        List<TaskDto> taskDtos = taskRepository.findAllByAccount(hollowAccount).stream()
                .map(TaskDto::from).toList();

        Comparator<TaskDto> compareFn = Comparator.comparing(TaskDto::tentativeCompletionDate,
                Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(TaskDto::createdAt);

        List<TaskDto> inCompleteTasks = taskDtos.stream()
                .filter(taskDto -> !taskDto.isCompleted())
                .sorted(compareFn)
                .toList();

        List<TaskDto> completedTasks = taskDtos.stream()
                .filter(TaskDto::isCompleted)
                .sorted(Comparator.comparing(TaskDto::completedAt).reversed())
                .toList();

        return new List[]{inCompleteTasks, completedTasks};
    }

    @Override
    @Transactional
    public void markTaskAsCompleted(int accountId, int taskId) {
        Optional<Task> optional = taskRepository.findById(taskId);
        if (optional.isEmpty()) {
            throw new TaskNotFoundException();
        }

        Task task = optional.get();

        if (task.getAccount().getId() != accountId) {
            throw new TaskNotFoundException();  // TODO: maybe a different exception with 403?
        }

        task.setCompleted(true);
        task.setCompletedAt(ZonedDateTime.now(Clock.systemUTC()));
        taskRepository.save(task);
    }
}
