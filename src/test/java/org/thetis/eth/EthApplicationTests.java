package org.thetis.eth;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.quorum.Quorum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthApplicationTests {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	Web3j web3j;

    @Autowired
    Quorum quorum;

	@Test
	public void testWeb3jNumbOfAccounts()  {

		try {

			EthAccounts accounts = web3j.ethAccounts().send();

			logger.info("Accounts size: " + accounts.getAccounts().size());

			accounts.getAccounts().forEach(acc -> logger.info("Account: " + acc));

		} catch (Exception e) {

			logger.error("Error occured", e);
			Assert.fail();

		}

	}


    @Test
    public void testQuorumjNumbOfAccounts()  {

        try {

            EthAccounts accounts = quorum.ethAccounts().send();

            logger.info("Accounts size: " + accounts.getAccounts().size());

            accounts.getAccounts().forEach(acc -> logger.info("Account: " + acc));

        } catch (Exception e) {

            logger.error("Error occured", e);
            Assert.fail();

        }

    }

}
