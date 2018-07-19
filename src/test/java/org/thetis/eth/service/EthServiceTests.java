package org.thetis.eth.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthTransaction;

import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthServiceTests {

    @Autowired
    EthService ethService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testGetAccounts() {

        ethService.getAccounts().subscribe(new Consumer<EthAccounts>() {
            public void accept(EthAccounts accounts) {
                accounts.getAccounts().forEach(acc -> {
                    logger.info("Account: " + acc);
                    Assert.assertNotNull(acc);
                });
            }
        });
    }

    @Test
    public void testGetAccounts2() {

        ethService.
                getAccounts().
                doOnError(e -> logger.error("Error occured", e)).
                doOnNext(a -> logger.info("id: " + a.getId())).
                doAfterTerminate(() -> logger.info("done")).
                subscribe(ethAccounts -> ethAccounts.getAccounts().forEach(acc -> logger.info("Account: " + acc)));
    }

    @Test
    public void testGetTransaction() {

        String hash = "0x46fcac081c4bdb90696c902492f4e3f494dcf31cdccb158da368d1d55ea685ba";

        ethService.
                getTransaction(hash).
                subscribe(ethTransaction -> ethTransaction.
                                getTransaction().
                                ifPresentOrElse(t -> logger.info(t.getBlockHash()),() -> logger.warn("No transaction found")),
                        throwable -> logger.error("Failed", throwable));
    }

    @Test
    public void testGetBlockNumber() {

        ethService.getBlockNumber().subscribe(new Consumer<EthBlockNumber>() {

            public void accept(EthBlockNumber ethBlockNumber) {
                logger.info("block number: " + ethBlockNumber.getBlockNumber());
            }
        });
    }


}
