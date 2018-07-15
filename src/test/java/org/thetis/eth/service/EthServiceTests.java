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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EthService ethService;

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
    public void testGetTransaction() {

        String hash = "0x46fcac081c4bdb90696c902492f4e3f494dcf31cdccb158da368d1d55ea685ba";

        ethService.getTransaction(hash).subscribe(new Consumer<EthTransaction>() {

            public void accept(EthTransaction ethTransaction) {

                ethTransaction.getTransaction().ifPresentOrElse(t -> logger.info(t.getBlockHash()), new Runnable() {
                    @Override
                    public void run() {
                        Assert.fail();
                    }
                });
            }
        });
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
