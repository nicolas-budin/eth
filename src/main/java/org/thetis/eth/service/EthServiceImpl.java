package org.thetis.eth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.quorum.Quorum;
import reactor.core.publisher.Flux;
import rx.RxReactiveStreams;


public class EthServiceImpl implements EthService {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected Quorum quorum;

    public Flux<EthAccounts> getAccounts() {

        return Flux.from(RxReactiveStreams.toPublisher(quorum.ethAccounts().observable()));
    }

}
