package com.priyakdey.com.zentra.repository;

import com.priyakdey.com.zentra.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Priyak Dey
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
