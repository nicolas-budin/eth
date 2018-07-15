package org.thetis.eth.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthServiceTests {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	EthService ethService;

	@Test
	public void testGetNumbOfAccounts()  {

		try {

			logger.info("Accounts size: " + ethService.getNumbOfAccounts());

		} catch (Exception e) {

			logger.error("Error occured", e);
			Assert.fail();
		}
	}

}
