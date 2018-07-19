package org.thetis.eth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thetis.eth.service.EthService;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/json/")
public class EthRESTController {

    @Autowired
    protected EthService ethService;

    @GetMapping("/accounts")
    Flux<EthAccounts> list() {
        return this.ethService.getAccounts();
    }

    @GetMapping("/transactions")
    Flux<Transaction> transactions() {
        return this.ethService.getTransactions();
    }

    @GetMapping("/blocks")
    Flux<EthBlock> blocks() {
        return this.ethService.getBlocks();
    }

}
