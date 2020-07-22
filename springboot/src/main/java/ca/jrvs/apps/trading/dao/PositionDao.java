package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PositionDao {

    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
    private JdbcTemplate jdbcTemplate;
    private final String TABLE_NAME = "position";
    private final String ACCOUNT_ID_COLUMN = "account_id";
    private final String TICKER_COLUMN = "ticker";


    public PositionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean getPositionsByAccountId(Integer account_id) {
        String existSql = "SELECT EXISTS (SELECT FROM " + TABLE_NAME + " WHERE " + ACCOUNT_ID_COLUMN + "=?)";
        return jdbcTemplate.queryForObject(existSql, Boolean.class, account_id);
    }

    public Integer numberOfPositionsByAccountIdAndTickerId(Integer account_id, String ticker) {
        String selectSql = "SELECT position FROM position WHERE account_id=? AND ticker=?";
        return jdbcTemplate.queryForObject(selectSql, Integer.class, new Object[]{account_id, ticker});
    }

    public List<Position> positionsByTraderId(Integer account_id) {
        String selectSql = "SELECT * FROM position WHERE account_id=?";
        return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Position.class), account_id);
    }

    public List<Position> positionsByTraderIdAndTicker(Integer account_id, String ticker) {
        String selectSql = "SELECT * FROM position WHERE account_id=? AND ticker=?";
        return jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Position.class),
                new Object[]{account_id, ticker});
    }


}
