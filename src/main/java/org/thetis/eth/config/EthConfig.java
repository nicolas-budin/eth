package org.thetis.eth.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thetis.eth.service.EthService;
import org.thetis.eth.service.EthServiceImpl;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;

@Configuration
@Profile("default")
public class EthConfig {

    @Value("${web3j.node.url}")
    private String nodeUrl;

    @Bean(name="web3j")
    public Web3j web3j() {
        return Web3j.build(new HttpService(nodeUrl));
    }

    @Bean(name="quorum")
    public Quorum quorum() {
        return Quorum.build(new HttpService(nodeUrl));
    }

    @Bean(name="ethService")
    public EthService ethService() {
        return new EthServiceImpl();
    }

}
