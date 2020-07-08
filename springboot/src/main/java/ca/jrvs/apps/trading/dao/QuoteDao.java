package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
        System.out.println("x");
    }

    @Override
    public <S extends Quote> S save(S s) {
        if(existsById(s.getId()))
        {
            int updatedRowNo= updateOne(s);
            if(updatedRowNo != 1)
            {
                throw new DataRetrievalFailureException("Unable to update quote");
            }
        }
        else
        {
            addOne(s);
        }
        return s;
    }

    //helper

    private void addOne(Quote s)
    {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(s);
        String[] res = parameterSource.getParameterNames();
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
        }
    }

    public int updateOne(Quote s)
    {
        String update_sql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
        return jdbcTemplate.update(update_sql,makeUpdateValues(s));
    }

    private Object[] makeUpdateValues(Quote s)
    {
        Object[] res = new Object[6];
        res[0] = s.getLastPrice();
        res[1] = s.getBidPrice();
        res[2] = s.getBidSize();
        res[3] = s.getAskPrice();
        res[4] = s.getAskSize();
        res[5] = s.getTicker();
        return res;
    }

    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
        iterable.forEach(this::save);
        return iterable;
    }

    @Override
    public Optional<Quote> findById(String s) {

        Quote t = null;
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
        try{

            t = jdbcTemplate.queryForObject(selectSql, BeanPropertyRowMapper.newInstance(Quote.class),s);
        } catch (EmptyResultDataAccessException ex)
        {
            logger.debug("Can't find trader id:" + s,ex);
        }
        return Optional.ofNullable(t);
    }

    @Override
    public boolean existsById(String s) {
        String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?";
        List<Quote> res = jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class),s);
        return !res.isEmpty();
    }

    @Override
    public Iterable<Quote> findAll() {
        String getAllSql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(getAllSql,BeanPropertyRowMapper.newInstance(Quote.class));
    }


    @Override
    public Iterable<Quote> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
        jdbcTemplate.update(deleteSql,s);
    }

    @Override
    public void delete(Quote quote) {
        deleteById(quote.getTicker());
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        iterable.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        Iterable<Quote> res = findAll();
        deleteAll(res);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
