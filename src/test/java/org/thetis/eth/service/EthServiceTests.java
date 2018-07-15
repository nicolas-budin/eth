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

import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthServiceTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EthService ethService;

    @Test
    public void testGetAccountsFlux() {

        ethService.getAccounts().subscribe(new Consumer<EthAccounts>() {
            public void accept(EthAccounts accounts) {
                accounts.getAccounts().forEach(acc -> {
                    logger.info("Account: " + acc);
                    Assert.assertNotNull(acc);
                });
            }
        });
    }

}
