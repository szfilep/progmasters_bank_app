package hu.progmasters.controller;

import hu.progmasters.domain.Account;
import hu.progmasters.domain.Transfer;
import hu.progmasters.service.AccountService;
import hu.progmasters.service.TransferService;
import hu.progmasters.validation.TransferValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);

    private static final String MODEL_ATTR_TRANSFER = "transfer";
    private static final String MODEL_ATTR_ACCOUNTS = "accounts";

    @Autowired
    TransferService transferService;

    @Autowired
    AccountService accountService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new TransferValidator());
        binder.registerCustomEditor(Account.class, new AccountPropertyEditor());
    }

    protected boolean checkErrorsAndAddToFlashAttributes(String modelAttributeName, Transfer transfer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            addEntityAndBindingResultsToFlashAttr(modelAttributeName, transfer, bindingResult, redirectAttributes);
        }
        return bindingResult.hasErrors();
    }

    protected void addEntityAndBindingResultsToFlashAttr(String modelAttributeName, Transfer transfer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(modelAttributeName, transfer);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + modelAttributeName, bindingResult);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String transferPage(Model model, HttpServletRequest request) {
        logger.info("/transfer [GET]");

        Account myAccount = accountService.findMyAccount(request.getRemoteAddr());
        if (myAccount == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute(MODEL_ATTR_TRANSFER)) {
            model.addAttribute(MODEL_ATTR_TRANSFER, new Transfer(myAccount));
        }

        if (!model.containsAttribute(MODEL_ATTR_ACCOUNTS)) {
            List<Account> accounts = accountService.findAll();
            accounts.remove(myAccount);
            model.addAttribute(MODEL_ATTR_ACCOUNTS, accounts);
        }


        return "transfer/create";
    }


    @RequestMapping(value = "/transfer/create", method = RequestMethod.POST)
    public String createTransfer(
            @ModelAttribute(MODEL_ATTR_TRANSFER) @Valid Transfer transfer,
            BindingResult bindingResult,
            @RequestParam(value = "to", required = true) Account transferTo,
            RedirectAttributes redirectAttributes) {
        logger.info("/transfer/create [POST]");

        String view;

        if (!checkErrorsAndAddToFlashAttributes(MODEL_ATTR_TRANSFER, transfer, bindingResult, redirectAttributes)) {
            transferService.create(transfer);
            redirectAttributes.addFlashAttribute("successMessage", "Sikeres átutalás.");
//            serverSentEventsService.sendMessage("Blocking event created (id " + blockingEventEntity.getId() + ")");
            view = "redirect:/transfer";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Hibás adatok.");
            view = "redirect:/transfer";
        }

        return view;
    }


    private class AccountPropertyEditor extends PropertyEditorSupport {
        public AccountPropertyEditor()   {
        }

        public void setAsText(String id)   {
            Account account = accountService.findOne(Long.parseLong(id));
            setValue(account);
        }
    }
}
