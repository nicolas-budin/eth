package org.thetis.eth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thetis.eth.service.EthService;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/web/")
public class EthController {

    @Autowired
    protected EthService ethService;

    @GetMapping("/transactions")
    String transactions(final Model model) {

        final Flux<Transaction> transactionFlux = this.ethService.getTransactions();

        model.addAttribute("dataSource", new ReactiveDataDriverContextVariable(transactionFlux, 1000));

        return "thymeleaf/transactions";

    }

}
