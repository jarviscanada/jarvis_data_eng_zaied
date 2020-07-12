package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderAccountServiceIntTest {

    private TraderAccountView traderAccountView;

    @Autowired
    private TraderAccountService traderAccountService;

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private AccountDao accountDao;

    @Before
    public void createTraderAndAccount() {
        Trader trader = new Trader("zaied","zaman","can", new Date(),"am");
        traderAccountView = traderAccountService.createTraderAndAccount(trader);
    }

    @Test
    public void deposit() {
        traderAccountService.deposit(traderAccountView.getTrader().getId(),1000.00);
    }

    @Test
    public void withdraw() {
        Double currAmount = traderAccountService.withdraw(traderAccountView.getTrader().getId(),1000.00).getAmount();
        assertEquals(currAmount, new Double(0.0));

    }

    @After
    public void deleteTraderById() {
        traderAccountService.deleteTraderById(traderAccountView.getTrader().getId());
    }

}