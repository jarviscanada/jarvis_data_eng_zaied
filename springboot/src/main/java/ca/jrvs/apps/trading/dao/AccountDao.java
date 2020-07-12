package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleInsert;
    private final String TABLE_NAME = "account";
    private final String ID_COLUMN = "id";

    public AccountDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
        this.simpleInsert = new SimpleJdbcInsert(datasource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
    }

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
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public int updateOne(Account entity) {
        String update_sql = "UPDATE account SET trader_id=?, amount=? WHERE id=?";
        return jdbcTemplate.update(update_sql,makeUpdateValues(entity));
    }

    private Object[] makeUpdateValues(Account entity)
    {
        Object[] res = new Object[3];
        res[0] = entity.getTrader_id();
        res[1] = entity.getAmount();
        res[2] = entity.getId();
        return res;
    }

    public Account findByTraderId(Integer Id)
    {
        String getSql = "SELECT * FROM "+ TABLE_NAME + " WHERE " + ID_COLUMN + "=?";
        return jdbcTemplate.queryForObject(getSql, BeanPropertyRowMapper.newInstance(Account.class),Id);
    }

    public Account updateAmountById(Integer Id, Double amount)
    {
        Account account = findByTraderId(Id);
        account.setId(Id);
        account.setAmount(account.getAmount()+amount);
        updateOne(account);
        return account;
    }

    @Override
    public void deleteById(Integer s) {
        String deleteSql = "DELETE FROM " + TABLE_NAME +" WHERE "+ ID_COLUMN + " =?";
        jdbcTemplate.update(deleteSql,s);

    }

    @Override
    public void delete(Account account) {
        deleteById(account.getId());
    }
}
