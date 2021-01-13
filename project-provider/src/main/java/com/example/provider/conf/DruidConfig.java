package com.example.provider.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Druid配置
 *
 * @author wakening
 */
@Configuration
@MapperScan(basePackages = "com.example.repository.dao", sqlSessionTemplateRef = "sqlSessionTemplateDB1")
public class DruidConfig {

    @Primary
    @Bean(name = "dataSourceDB1")
    @ConfigurationProperties(prefix = "druid.db1")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean("sqlSessionFactoryDB1")
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("dataSourceDB1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliases(String.class, Integer.class, Double.class, Float.class,
                Map.class, HashMap.class, LinkedHashMap.class, List.class, ArrayList.class, LinkedList.class);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/*.xml"));
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        if (sqlSessionFactory != null) {
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        }
        return sqlSessionFactory;
    }

    @Primary
    @Bean(name = "transactionManagerDB1")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("dataSourceDB1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "sqlSessionTemplateDB1")
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("sqlSessionFactoryDB1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
