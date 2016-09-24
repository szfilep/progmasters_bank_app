package hu.progmasters.service;

import hu.progmasters.domain.Account;
import hu.progmasters.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void create(Account account) {
        accountRepository.save(account);
//        if (Account.findByName(applicationEntity.getName()) == null) {
//            createrAndModifierUpdateService.setCreater(applicationEntity);
//            createrAndModifierUpdateService.setModifier(applicationEntity);
//            applicationRepository.save(applicationEntity);
//        } else {
//            throw new ServiceValidationExpcetion("name", "nameExists", new String[]{applicationEntity.getName()}, "Name already exists: " + applicationEntity.getName());
//        }
    }

    public Account findByIpAddress(String ipAddress) {
        return accountRepository.findByIpAddress(ipAddress);
    }

    public Account findOne(Long id) {
        return accountRepository.findOne(id);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findAllOrderByFunds() {
        return accountRepository.findAllByOrderByFundsDesc();
    }

    public Account findMyAccount(String ipAddress) {
        return findByIpAddress(ipAddress);
    }
}
