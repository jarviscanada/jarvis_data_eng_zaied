package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.domain.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"ca.jrvs.apps.trading.dao","ca.jrvs.apps.trading.service"})
public class TestConfig {

    @Bean
    public MarketDataConfig marketDataConfig(){
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken("pk_7d126699746b47089ffc453a938c1eb3");
        return marketDataConfig;
    }

    @Bean
    public DataSource dataSource() {
        String url = "jdbc:postgresql://localhost:5432/jrvstrading_test";
        String user = "postgres";
        String password = "password";
        //Never log your credentials/secrets. Use debugger instead
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(50);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
        return poolingHttpClientConnectionManager;
    }

    @Bean
    public Quote quote(){
        return new Quote();
    }
}
