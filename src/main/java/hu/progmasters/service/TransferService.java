package hu.progmasters.service;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.Transfer;
import hu.progmasters.repository.AccountRepository;
import hu.progmasters.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransferService {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AccountRepository accountRepository;

    public Transfer create(Transfer transfer) {
        //TODO transfer amount from an account to another fund and set datetime

        Account from = transfer.getFrom();
        Account to = transfer.getTo();

        from.setBalance(from.getBalance() - transfer.getAmount());
        accountRepository.save(from);

        to.setFunds(to.getFunds() + transfer.getAmount());
        accountRepository.save(to);

        transfer.setTimeStamp(new Date());

        return transferRepository.save(transfer);
    }

    public List<Transfer> findMyTransfers(Account account) {
        return transferRepository.findAllByFromOrTo(account, account);
    }

    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }
}
