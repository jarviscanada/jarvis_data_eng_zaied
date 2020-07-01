package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.MarketDataConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.URI;
import java.util.*;

public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig)
    {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }



    @Override
    public Optional<IexQuote> findById(String s) {

        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(s));

        if(quotes.size() == 0)
        {
            return Optional.empty();
        }
        else if(quotes.size() == 1)
        {
            iexQuote = Optional.of(quotes.get(0));
            System.out.println(iexQuote.get().getSymbol());
        }
        else
        {
            throw new DataRetrievalFailureException("unexpected number of quotes");
        }
        return iexQuote;
    }

    @Override
    public List<IexQuote> findAllById(Iterable<String> iterable) {
        Iterator<String> it = iterable.iterator();
        List<IexQuote> res = new ArrayList<IexQuote>();
        while(it.hasNext())
        {
            String fullBatchPath = String.format(IEX_BATCH_URL,it.next());
            Optional<String> curr = executeHttpGet(fullBatchPath);
            JSONObject jsonObject = new JSONObject(curr.get());
            System.out.println(curr.get());
            String res2 = jsonObject.getJSONObject("AAPL").getJSONObject("quote").toString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            try {
                IexQuote iexQuote = objectMapper.readValue(res2, IexQuote.class);
                res.add(iexQuote);
            } catch (IOException ex) {
                logger.error("findAllById failed",ex);
                throw new RuntimeException("findAllById failed");
            }
        }
        return res;
    }

    private Optional<String> executeHttpGet(String url)
    {
        CloseableHttpClient closeableHttpClient = getHttpClient();
        HttpUriRequest postReq = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = closeableHttpClient.execute(postReq);
        } catch (IOException ex) {
            logger.error("quote post uri could not be built",ex);
        }
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        try {
            return Optional.ofNullable(EntityUtils.toString(httpEntity));
        } catch (IOException ex) {
            logger.error("quote http could not be converted to string",ex);
            throw new RuntimeException("in executeHttpGet method in marketDataDao");
        }
    }
    private CloseableHttpClient getHttpClient()
    {
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager).setConnectionManagerShared(true)
                .build();
    }


    //unimplemented methods

    @Override
    public <S extends IexQuote> S save(S s) {
        return null;
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }
    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(IexQuote iexQuote) {

    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
