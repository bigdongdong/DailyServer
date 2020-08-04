package com.cxd.daily.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;

@Configuration

//配置Mybatis Mapper的扫描路径
@MapperScan(value = "com.cxd.daily.dao")
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    /**
     * C3p0连接池配置
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource() throws PropertyVetoException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        //配置初始化大小、最小、最大
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        //连接泄漏监测
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(30);
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(20000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(20000);
        //防止过期
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

}
