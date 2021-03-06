package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.controller.ResponseExceptionUtil;
import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private AccountDao accountDao;
    private SecurityOrderDao securityOrderDao;
    private QuoteDao quoteDao;
    private PositionDao positionDao;

    @Autowired
    public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao, PositionDao positionDao) {
        this.accountDao = accountDao;
        this.securityOrderDao = securityOrderDao;
        this.quoteDao = quoteDao;
        this.positionDao = positionDao;
    }

    public SecurityOrder executeMarketOrder(MarketOrderDto marketOrderDto) {
        if (marketOrderDto.getSize() == null || marketOrderDto.getTicker() == null) {
            throw ResponseExceptionUtil.getResponseException(new IllegalArgumentException());
        }

        Integer marketOrderDtoId = marketOrderDto.getAccountId();
        String marketOrderDtoSize = marketOrderDto.getSize();
        String marketOrderDtoTicker = marketOrderDto.getTicker();

        Integer orderSize = Integer.parseInt(marketOrderDtoSize);
        Account account = accountDao.findByTraderId(marketOrderDtoId);

        SecurityOrder securityOrder = buildSecurityOrder(marketOrderDto);

        if (orderSize > 0) {
            Double currentAmount = account.getAmount();

            Double requiredAmountToBuy = getUnit(marketOrderDto, quoteDao)
                    * Integer.parseInt(marketOrderDtoSize);

            if (currentAmount > requiredAmountToBuy) {
                handleBuyMarketOrder(marketOrderDto, securityOrder, account);
            }
        } else {

            orderSize = (-1) * orderSize;
            Integer boughtPositions = positionDao.numberOfPositionsByAccountIdAndTickerId
                    (marketOrderDtoId, marketOrderDtoTicker);

            if (boughtPositions >= orderSize) {
                handleSellMarketOrder(marketOrderDto, securityOrder, account);
            }
        }
        return securityOrder;
    }

    //helper

    private Double getUnit(MarketOrderDto marketOrderDto, QuoteDao quoteDao) {
        return quoteDao.findById(marketOrderDto.getTicker()).get().getAskPrice();
    }

    private SecurityOrder buildSecurityOrder(MarketOrderDto marketOrderDto) {

        Integer marketOrderDtoId = marketOrderDto.getAccountId();
        String marketOrderDtoSize = marketOrderDto.getSize();
        String marketOrderDtoTicker = marketOrderDto.getTicker();

        SecurityOrder securityOrder = new SecurityOrder();
        securityOrder.setAccount_id(marketOrderDtoId);
        securityOrder.setNotes(null);
        securityOrder.setTicker(marketOrderDtoTicker);
        securityOrder.setSize(Integer.parseInt(marketOrderDtoSize));
        securityOrder.setPrice(quoteDao.findById(marketOrderDtoTicker).get().getAskPrice());
        return securityOrder;
    }

    protected void handleBuyMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder, Account account) {

        securityOrder.setStatus("FILLED");

        securityOrderDao.save(securityOrder);

        Integer unit = Integer.parseInt(marketOrderDto.getSize());

        account.setAmount(account.getAmount() - unit * securityOrder.getPrice());
        accountDao.updateOne(account);
    }

    protected void handleSellMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder, Account account) {

        securityOrder.setStatus("FILLED");

        securityOrderDao.save(securityOrder);

        Integer unit = Integer.parseInt(marketOrderDto.getSize());
        unit = (-1) * unit;

        account.setAmount(account.getAmount() + unit * securityOrder.getPrice());
        accountDao.updateOne(account);
    }
}
