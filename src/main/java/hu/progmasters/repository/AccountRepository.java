package hu.progmasters.repository;

import hu.progmasters.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIpAddress(String ipAddress);
}
