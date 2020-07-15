package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.controller.ResponseExceptionUtil;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {

    private static final Logger logger = LoggerFactory.getLogger(TraderAccountService.class);

    private TraderDao traderDao;
    private AccountDao accountDao;
    private PositionDao positionDao;
    private SecurityOrderDao securityOrderDao;

    @Autowired
    public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao, SecurityOrderDao securityOrderDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    public TraderAccountView createTraderAndAccount(Trader trader) {
        checkCreateTraderAndAccountInput(trader);
        traderDao.save(trader);
        Account account = new Account();
        account.setId(trader.getId());
        account.setTrader_id(trader.getId());
        account.setAmount(1000d);
        accountDao.save(account);
        TraderAccountView traderAccountView = new TraderAccountView();
        traderAccountView.setAccount(account);
        traderAccountView.setTrader(trader);
        return traderAccountView;
    }

    //helper
    private void checkCreateTraderAndAccountInput(Trader trader) {
        if (trader.getFirstName() == null || trader.getLastName() == null || trader.getCountry() == null
                || trader.getDob() == null || trader.getEmail() == null) {
            logger.debug("invalid input trader and account no first name");
            throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());
        }
    }

    public void deleteTraderById(Integer trader_id) {
        if (trader_id <= 0 || trader_id > Integer.MAX_VALUE || trader_id == null) {
            throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());
        }
        Double accountBalance = accountDao.findByTraderId(trader_id).getAmount();
        if (accountBalance != 0) throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());
        if (positionDao.getPositionsByAccountId(trader_id))
            throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());

        securityOrderDao.deleteByAccountId(trader_id);
        accountDao.deleteById(trader_id);
        traderDao.deleteById(trader_id);
    }

    public Account deposit(Integer trader_id, Double fund) {
        if (trader_id <= 0 || trader_id > Integer.MAX_VALUE || trader_id == null || fund < 0) {
            throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());
        }
        return accountDao.updateAmountById(trader_id, fund);
    }

    public Account withdraw(Integer trader_id, Double fund) {
        if (trader_id <= 0 || trader_id > Integer.MAX_VALUE || trader_id == null || fund < 0) {
            throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());
        }
        Double currentFund = accountDao.findByTraderId(trader_id).getAmount();
        if (currentFund < fund) throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());
        return accountDao.updateAmountById(trader_id, (-1) * fund);
    }
}
