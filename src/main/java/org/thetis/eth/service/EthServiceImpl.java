package org.thetis.eth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.quorum.Quorum;



public class EthServiceImpl implements EthService {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected Quorum quorum;

    public int getNumbOfAccounts() throws Exception {

        try {

            EthAccounts accounts = quorum.ethAccounts().send();
            return accounts.getAccounts().size();

        } catch (Exception e) {

            logger.error("Error occured", e);
            throw e;

        }
    }


}
