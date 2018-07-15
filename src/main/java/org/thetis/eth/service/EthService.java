package org.thetis.eth.service;

import org.web3j.protocol.core.methods.response.EthAccounts;
import reactor.core.publisher.Flux;

public interface EthService {

    Flux<EthAccounts> getAccounts();
}
