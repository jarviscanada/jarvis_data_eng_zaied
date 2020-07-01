package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.MarketDataConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoIntTest {

    private MarketDataDao dao;

    @Before
    public void setUp() throws Exception {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(50);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken("pk_7d126699746b47089ffc453a938c1eb3");

        dao = new MarketDataDao(poolingHttpClientConnectionManager,marketDataConfig);
    }

    @Test
    public void findById() {
        IexQuote iexQuote = dao.findById("AAPL").get();
        System.out.println(iexQuote.getSymbol());
        assertNotNull(iexQuote);
    }

    @Test
    public void findAllById() {
        List<IexQuote> res = dao.findAllById(Arrays.asList("AAPL","FB"));
        //System.out.println(res.get(0).toString());
        assertEquals(res.size(),2);
    }
}