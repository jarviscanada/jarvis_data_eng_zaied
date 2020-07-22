package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityRows;
import ca.jrvs.apps.trading.model.view.PortfolioView;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class DashBoardService {

    private TraderDao traderDao;
    private PositionDao positionDao;
    private AccountDao accountDao;
    private QuoteDao quoteDao;

    public DashBoardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao,
                            QuoteDao quoteDao) {
        this.traderDao = traderDao;
        this.positionDao = positionDao;
        this.quoteDao = quoteDao;
        this.accountDao = accountDao;
    }

    public TraderAccountView getTraderAccount(Integer trader_id) {
        TraderAccountView traderAccountView = new TraderAccountView();
        traderAccountView.setTrader(traderDao.findById(trader_id).get());
        traderAccountView.setAccount(accountDao.findByTraderId(trader_id));
        return traderAccountView;
    }

    public PortfolioView getProfileViewByTraderId(Integer trader_id) {
        HashSet<String> alreadySeen = new HashSet<>();
        List<Position> res = positionDao.positionsByTraderId(trader_id);
        PortfolioView portfolioView = new PortfolioView();
        List<SecurityRows> secRowsToAdd = new ArrayList<SecurityRows>();

        for (int i = 0; i < res.size(); i++) {
            String ticker = res.get(i).getTicker();
            if (!alreadySeen.contains(ticker)) {
                alreadySeen.add(ticker);
                List<Position> curr = positionDao.positionsByTraderIdAndTicker(trader_id, ticker);
                SecurityRows securityRows = new SecurityRows();
                securityRows.setTicker(ticker);
                securityRows.setPosition(curr);
                securityRows.setQuote(quoteDao.findById(ticker).get());
                secRowsToAdd.add(securityRows);
            }
        }
        portfolioView.setSecRows(secRowsToAdd);
        return portfolioView;

    }
}
