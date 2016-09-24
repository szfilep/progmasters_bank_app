package hu.progmasters.controller;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.Transfer;
import hu.progmasters.service.AccountService;
import hu.progmasters.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private static final String MODEL_ATTR_ACCOUNT = "account";

    @Autowired
    AccountService accountService;

    @Autowired
    TransferService transferService;

    protected boolean checkErrorsAndAddToFlashAttributes(String modelAttributeName, Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addEntityAndBindingResultsToFlashAttr(modelAttributeName, account, bindingResult, redirectAttributes);
        }
        return bindingResult.hasErrors();
    }

    protected void addEntityAndBindingResultsToFlashAttr(String modelAttributeName, Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(modelAttributeName, account);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + modelAttributeName, bindingResult);
    }

    @RequestMapping(value= "/", method= RequestMethod.GET)
    public String homePage(HttpServletRequest request, Model model) {
        logger.info("/ [GET]");

        Account existingAccount = accountService.findByIpAddress(request.getRemoteAddr());
        String view = "index";
        if (existingAccount != null) {
            model.addAttribute(MODEL_ATTR_ACCOUNT, existingAccount);
            view = "redirect:/myaccount";
        } else if (!model.containsAttribute(MODEL_ATTR_ACCOUNT)) {
            model.addAttribute(MODEL_ATTR_ACCOUNT, new Account());
        }

        return view;
    }

    @RequestMapping(value= "/myaccount", method= RequestMethod.GET)
    public String myAccount(Model model, HttpServletRequest request) {
        logger.info("/myaccount [GET]");

        Account existingAccount = accountService.findByIpAddress(request.getRemoteAddr());
        if (existingAccount != null) {
            model.addAttribute("account", existingAccount);

            List<Transfer> transfers = transferService.findMyTransfers(existingAccount);
            model.addAttribute("transfers", transfers);

            return "account/details";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/account/create", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute(MODEL_ATTR_ACCOUNT) @Valid Account account,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request) {

        logger.info("/account/create [POST]");

        String view;
        if (!checkErrorsAndAddToFlashAttributes(MODEL_ATTR_ACCOUNT, account, bindingResult, redirectAttributes)) {
            account.setIpAddress(request.getRemoteAddr());
            account.setBalance(5000);
            account.setFunds(0);
            accountService.create(account);
//            serverSentEventsService.sendMessage("Application created (id " + applicationEntity.getId() + ")");
            view = "redirect:/myaccount/";
        } else {
            view = "redirect:/";
        }

        return view;
    }

    @RequestMapping(value= "/accounts", method= RequestMethod.GET)
    public String accounts(Model model, HttpServletRequest request) {
        logger.info("/account [GET]");

        List<Account> accounts = accountService.findAllOrderByFunds();
        model.addAttribute("accounts", accounts);

        List<Transfer> transfers = transferService.findAll();
        model.addAttribute("transfers", transfers);

        return "account/list";
    }

}
