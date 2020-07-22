package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

    @Autowired
    private QuoteDao quoteDao;

    private final Quote savedQuote = new Quote();

    @Before
    public void insertOne() {
        savedQuote.setTicker("aapl");
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10L);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10L);
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);
    }

    @Test
    public void findOne() {
        quoteDao.findById("aapl");
    }

    @Test
    public void updateTest() {
        assertEquals(quoteDao.updateOne(savedQuote), 1);
    }

    @After
    public void deleteOne() {
        quoteDao.deleteById("aapl");
    }
}
