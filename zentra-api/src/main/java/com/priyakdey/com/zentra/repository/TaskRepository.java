package com.priyakdey.com.zentra.repository;

import com.priyakdey.com.zentra.domain.Account;
import com.priyakdey.com.zentra.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Priyak Dey
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByAccount(Account account);

}
