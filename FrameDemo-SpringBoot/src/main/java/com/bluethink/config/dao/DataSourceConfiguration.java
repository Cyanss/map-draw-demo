package com.bluethink.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;

/**
 * @auther BlueThink Cyan
 * @create 2019/3/18
 */
@Data
@Configuration
@Component
@Slf4j
@MapperScan("com.bluethink.dao")
@ConfigurationProperties(prefix = "jdbc")
public class DataSourceConfiguration {
    private String driver;
    private String url;
    private String username;
    private String password;

    @Bean(name = "datasource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        /** 关闭连接后不自动commit */
        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }

}
