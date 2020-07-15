package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class TraderDao extends JdbcCrudDao<Trader> {

    private static final Logger logger = LoggerFactory.getLogger(TraderDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleInsert;
    private final String TABLE_NAME = "trader";
    private final String ID_COLUMN = "id";
    private final String FIRST_NAME_COLUMN = "first_name";
    private final String LAST_NAME_COLUMN = "last_name";
    private final String COUNTRY_COLUMN = "country";
    private final String DOB_COLUMN = "dob";
    private final String EMAIL_COLUMN = "email";

    public TraderDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingGeneratedKeyColumns(ID_COLUMN);
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
    public Class<Trader> getEntityClass() {
        return Trader.class;
    }

    @Override
    public int updateOne(Trader entity) {
        String update_sql = "UPDATE " + TABLE_NAME + " SET " +
                FIRST_NAME_COLUMN + "=?, " + LAST_NAME_COLUMN + "=?, " + COUNTRY_COLUMN + "=?, " + DOB_COLUMN + "=?, " +
                EMAIL_COLUMN + "=? WHERE " + ID_COLUMN + "=?";
        return jdbcTemplate.update(update_sql, makeUpdateValues(entity));
    }

    //helper
    private Object[] makeUpdateValues(Trader entity) {
        Object[] res = new Object[6];
        res[0] = entity.getFirstName();
        res[1] = entity.getLastName();
        res[2] = entity.getDob();
        res[3] = entity.getCountry();
        res[4] = entity.getEmail();
        res[5] = entity.getId();
        return res;
    }

    @Override
    public <S extends Trader> S save(S s) {
        if (existsById(s.getId())) {
            int updatedRowNo = updateOne(s);
            if (updatedRowNo != 1) {
                throw new DataRetrievalFailureException("Unable to update trader");
            }
        } else {
            addOne(s);
        }
        return s;
    }

    private void addOne(Trader s) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(s);
        int trader_id = simpleInsert.executeAndReturnKey(parameterSource).intValue();
        s.setId(trader_id);
    }

    @Override
    public <S extends Trader> Iterable<S> saveAll(Iterable<S> iterable) {
        iterable.forEach(this::save);
        return iterable;
    }

    @Override
    public Optional<Trader> findById(Integer s) {
        Trader t = null;
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " =?";
        try {

            t = jdbcTemplate.queryForObject(selectSql, BeanPropertyRowMapper.newInstance(Trader.class), s);
        } catch (EmptyResultDataAccessException ex) {
            logger.debug("Can't find trader id:" + s, ex);
        }
        return Optional.ofNullable(t);
    }

    @Override
    public boolean existsById(Integer s) {
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=?";
        List<Quote> res = jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class), s);
        return !res.isEmpty();
    }

    @Override
    public Iterable<Trader> findAll() {
        String getAllSql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(getAllSql, BeanPropertyRowMapper.newInstance(Trader.class));
    }

    @Override
    public Iterable<Trader> findAllById(Iterable<Integer> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(Integer s) {
        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=?";
        jdbcTemplate.update(deleteSql, s);
    }

    @Override
    public void delete(Trader trader) {
        deleteById(trader.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends Trader> iterable) {
        iterable.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        Iterable<Trader> res = findAll();
        deleteAll(res);
    }
}
