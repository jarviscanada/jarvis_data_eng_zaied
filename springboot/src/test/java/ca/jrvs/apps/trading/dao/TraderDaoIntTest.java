package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

    @Autowired
    private TraderDao traderDao;

    private final Trader savedTrader = new Trader();

    @Before
    public void insertOne(){
        //savedTrader.setId(1);
        savedTrader.setFirstName("zaied");
        savedTrader.setLastName("zaman");
        savedTrader.setCountry("CAN");
        savedTrader.setEmail("zzaman2");
        savedTrader.setDob(new Date());
        traderDao.save(savedTrader);
    }

    @Test
    public void findOne(){
        traderDao.findById(1);
    }

    //@After
    public void deleteOne(){
        traderDao.deleteById(1);
    }
}
