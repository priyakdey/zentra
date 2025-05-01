package com.priyakdey.zentra.repository;

import com.priyakdey.zentra.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Priyak Dey
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Account> findByUsername(String username);
}
