package com.swd.pets;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
@PropertySource(value = {"classpath:dbconfig.properties"})
@EnableTransactionManagement
public class MySQLConfig {


    @Value("${mysql.url}")
    private String url;

    @Value("${mysql.username}")
    private String user;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.driver_class}")
    private String driverName;

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(password);
        setDataSourceProperty(dataSource);

        return dataSource;
    }


    private void setDataSourceProperty(ComboPooledDataSource dataSource) {
        dataSource.setInitialPoolSize(15);
        dataSource.setMaxPoolSize(30);
        dataSource.setMinPoolSize(5);
        dataSource.setMaxIdleTime(25200);
        dataSource.setMaxStatements(0);
        dataSource.setIdleConnectionTestPeriod(30);
        dataSource.setAcquireIncrement(20);
        dataSource.setAcquireRetryAttempts(30);
        dataSource.setPreferredTestQuery("SELECT 1");
        dataSource.setTestConnectionOnCheckout(false);
        dataSource.setBreakAfterAcquireFailure(true);
    }


    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mysqlDataSource());
        return jdbcTemplate;
    }


    @Bean
    @Qualifier(value = "mySqlTransactionManager")
    public DataSourceTransactionManager mySqlTransactionManager() {

        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(mysqlDataSource());

        return dataSourceTransactionManager;
    }


}
