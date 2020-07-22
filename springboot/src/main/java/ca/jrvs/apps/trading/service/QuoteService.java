package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private final MarketDataDao marketDataDao;
    private final QuoteDao quoteDao;

    @Autowired
    public QuoteService(MarketDataDao marketDataDao, QuoteDao quoteDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    protected Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
        Quote quote = new Quote();
        quote.setTicker(iexQuote.getSymbol());
        quote.setLastPrice(iexQuote.getLatestPrice() != null ? iexQuote.getLatestPrice() : 1);
        quote.setBidSize(iexQuote.getIexBidSize() != null ? iexQuote.getIexBidSize() : 1);
        quote.setAskPrice(iexQuote.getIexAskPrice() != null ? iexQuote.getIexAskPrice() : 1);
        quote.setAskSize(iexQuote.getIexAskSize() != null ? iexQuote.getIexAskSize() : 1);
        quote.setBidPrice(iexQuote.getIexBidPrice() != null ? iexQuote.getIexBidPrice() : 1);
        return quote;
    }

    public List<Quote> updateMarketData() {

        Iterable<Quote> res = quoteDao.findAll();
        res.forEach(quote -> buildQuoteFromIexQuote(marketDataDao.findById(quote.getTicker()).get()));
        quoteDao.saveAll(res);
        List<Quote> dummy = new ArrayList<>();
        res.forEach(dummy::add);
        return dummy;
    }

    public Quote saveQuote(String ticker) {
        IexQuote iexQuote = findIexQuoteByTicker(ticker);
        Quote quote = buildQuoteFromIexQuote(iexQuote);
        quoteDao.save(quote);
        return quote;
    }

    public Quote saveQuote(Quote quote) {
        return quoteDao.save(quote);
    }

    public List<Quote> saveQuotes(List<String> tickers) {
        return tickers.stream().map(this::saveQuote).collect(Collectors.toList());
    }

    public IexQuote findIexQuoteByTicker(String ticker) {
        return marketDataDao.findById(ticker).orElseThrow(() ->
                new IllegalArgumentException(ticker + "is invalid"));
    }

    public List<Quote> findAllQuotes() {
        List<Quote> dummy = new ArrayList<>();
        quoteDao.findAll().forEach(dummy::add);
        return dummy;
    }
}
