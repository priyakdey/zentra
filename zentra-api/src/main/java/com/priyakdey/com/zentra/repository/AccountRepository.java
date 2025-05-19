package com.priyakdey.com.zentra.repository;

import com.priyakdey.com.zentra.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Priyak Dey
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);
}
