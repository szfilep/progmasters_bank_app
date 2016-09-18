package hu.progmasters.repository;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findAllByFrom(Account from);

    List<Transfer> findAllByTo(Account to);

    List<Transfer> findAllByFromOrTo(Account from, Account to);
}
