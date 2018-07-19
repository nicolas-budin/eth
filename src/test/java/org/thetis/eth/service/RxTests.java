package org.thetis.eth.service;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RxTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EthService ethService;

    @Test
    public void testRxJava() throws Exception {

        Flowable.fromCallable(() -> {
            // imitate expensive computation
            Thread.sleep(1000);
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        // wait for the flow to finish
        Thread.sleep(2000);
    }

}
