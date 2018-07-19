package org.thetis.eth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.quorum.Quorum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.RxReactiveStreams;
import rx.Subscription;


public class EthServiceImpl implements EthService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected Quorum quorum;

    public Flux<EthAccounts> getAccounts() {

        return Flux.from(RxReactiveStreams.toPublisher(quorum.ethAccounts().observable()));
    }

    public Mono<EthBlockNumber> getBlockNumber() {

        return Mono.from(RxReactiveStreams.toPublisher(quorum.ethBlockNumber().observable().single()));
    }

    public Mono<EthTransaction> getTransaction(String hash) {

        return Mono.from(RxReactiveStreams.toPublisher(quorum.ethGetTransactionByHash(hash).observable().single()));
    }

    public Flux<Transaction> getTransactions() {

        return Flux.from(RxReactiveStreams.toPublisher(quorum.transactionObservable()));
    }

    public Flux<EthBlock> getBlocks() {

        return Flux.from(RxReactiveStreams.toPublisher(quorum.blockObservable(false)));
    }

}
