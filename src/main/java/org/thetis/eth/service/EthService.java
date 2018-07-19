package org.thetis.eth.service;

import org.web3j.protocol.core.methods.response.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EthService {

    Flux<EthAccounts> getAccounts();

    Mono<EthBlockNumber> getBlockNumber();

    Mono<EthTransaction> getTransaction(String hash);

    Flux<Transaction> getTransactions();

    Flux<EthBlock> getBlocks();
}
