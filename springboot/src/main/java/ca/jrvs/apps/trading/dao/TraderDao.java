package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TraderDao extends JdbcCrudDao<Trader> {

    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleInsert;
    private final String TABLE_NAME = "trader";
    private final String ID_COLUMN = "id";

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public SimpleJdbcInsert getSimpleJdbcInsert() {
        return simpleInsert;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getIdColumnName() {
        return ID_COLUMN;
    }

    @Override
    public Class<Trader> getEntityClass() {
        return Trader.class;
    }

    @Override
    public int updateOne(Trader entity) {
        String update_sql = "UPDATE trader SET first_name=?, last_name=?, dob=?, country=?, email=? WHERE id=?";
        return jdbcTemplate.update(update_sql,makeUpdateValues(entity));
    }

    //helper
    private Object[] makeUpdateValues(Trader entity)
    {
        Object[] res = new Object[6];
        res[0] = entity.getFirstName();
        res[1] = entity.getLastName();
        res[2] = entity.getDob();
        res[3] = entity.getCountry();
        res[4] = entity.getEmail();
        res[5] = entity.getId();
        return res;
    }
}
