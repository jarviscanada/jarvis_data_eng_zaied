package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.*;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private AccountDao accountDao;
    @Mock
    private SecurityOrderDao securityOrderDao;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private PositionDao positionDao;
    @Mock
    private Account account;
    @Mock
    private Quote quote;
    @Captor
    ArgumentCaptor<SecurityOrder> securityOrder;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(accountDao.findByTraderId(1)).thenReturn(account);
        Mockito.when(account.getAmount()).thenReturn(100.00);
        Mockito.when(quoteDao.findById("dummy")).thenReturn(Optional.of(quote));
    }

    @Test
    public void executeMarketOrder() {
        MarketOrderDto dummy = new MarketOrderDto();
        dummy.setAccountId(1);
        dummy.setSize("2");
        dummy.setTicker("dummy");
        Mockito.when(positionDao.numberOfPositionsByAccountIdAndTickerId(dummy.getAccountId(), dummy.getTicker()))
                .thenReturn(0);

        assertNotNull(orderService.executeMarketOrder(dummy));
        Mockito.verify(securityOrderDao).save(securityOrder.capture());
        assertEquals(securityOrder.getValue().getAccount_id(), new Integer(1));
    }

}