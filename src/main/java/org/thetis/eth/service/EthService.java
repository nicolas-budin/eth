package org.thetis.eth.service;

import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EthService {

    Flux<EthAccounts> getAccounts();

    Mono<EthBlockNumber> getBlockNumber();

    Mono<EthTransaction> getTransaction(String hash);
}
