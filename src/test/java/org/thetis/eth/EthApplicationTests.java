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
import reactor.core.publisher.Flux;
import rx.RxReactiveStreams;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthApplicationTests {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	Web3j web3j;

    @Autowired
    Quorum quorum;

	@Test
	public void testWeb3j()  {

		try {

			Assert.assertNotNull(this.web3j);
            logger.info(this.web3j.toString());

		} catch (Exception e) {

			logger.error("Error occured", e);
			Assert.fail();

		}

	}


    @Test
    public void testQuorum()  {

        try {

            Assert.assertNotNull(this.quorum);
            logger.info(this.quorum.toString());

        } catch (Exception e) {

            logger.error("Error occured", e);
            Assert.fail();

        }

    }


    @Test
    public void testRx2Reactor()  {

        try {

            // rxjava
            quorum.ethAccounts().observable().subscribe(accounts -> {
                accounts.getAccounts().forEach(acc -> {
                    logger.info("Account: " + acc);
                    Assert.assertNotNull(acc);
                });

            });


            // Reactor
            Flux.from(RxReactiveStreams.toPublisher(quorum.ethAccounts().observable())).subscribe(accounts -> {
                accounts.getAccounts().forEach(acc -> {
                    logger.info("Account: " + acc);
                    Assert.assertNotNull(acc);
                });
            });


        } catch (Exception e) {

            logger.error("Error occured", e);
            Assert.fail();

        }

    }

}
