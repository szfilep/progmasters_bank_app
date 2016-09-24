package hu.progmasters.repository;

import hu.progmasters.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIpAddress(String ipAddress);

    List<Account> findAllByOrderByFundsDesc();
}
