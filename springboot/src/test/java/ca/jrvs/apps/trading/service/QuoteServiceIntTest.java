package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

    @Autowired
    public QuoteService quoteService;

    @Autowired
    public QuoteDao quoteDao;

    @Before
    public void setup() {
        quoteDao.deleteAll();
    }

    @Test
    public void findIexQuoteByTicker() {
        assertNotNull(quoteService.findIexQuoteByTicker("AAPL"));
    }

    @Test
    public void updateMarketData() {
        assertNotNull(quoteService.updateMarketData());
    }

    @Test
    public void saveQuotes() {
        List<String> tickers = new ArrayList<>();
        tickers.add("MSFT");
        assertNotNull(quoteService.saveQuotes(tickers));
    }

    @Test
    public void saveQuote() {
        assertNotNull(quoteService.saveQuote("AAPL"));
    }

    @Test
    public void findAllQuotes() {
        assertNotNull(quoteService.findAllQuotes());
    }

}