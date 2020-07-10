package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

public class SecurityOrderDao {

    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleInsert;
    private final String TABLE_NAME = "security_order";
    private final String ID_COLUMN = "id";
    private final String ACCOUNT_ID_COLUMN = "account_id";
    private final String TICKER_COLUMN = "ticker";


    public SecurityOrderDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
    }

    List<SecurityOrder> findAllByAccountId(Integer accountId)
    {
        String selectSql = "SELECT * FROM "+ TABLE_NAME +" WHERE " + ACCOUNT_ID_COLUMN +"=?";
        return jdbcTemplate.queryForList(selectSql,SecurityOrder.class,accountId);
    }

    public void delete(SecurityOrder securityOrder)
    {
        String deleteSql = "DELETE FROM " + TABLE_NAME +" WHERE " + ACCOUNT_ID_COLUMN + "=? AND " + TICKER_COLUMN+ "=?";
        jdbcTemplate.update(deleteSql,securityOrder.getAccount_id(),securityOrder.getTicker());
    }
    public void  deleteAll(List<SecurityOrder> res)
    {
        res.forEach(this::delete);
    }

    public void deleteByAccountId(Integer accountId)
    {
        List<SecurityOrder> res = findAllByAccountId(accountId);
        deleteAll(res);
    }

}
