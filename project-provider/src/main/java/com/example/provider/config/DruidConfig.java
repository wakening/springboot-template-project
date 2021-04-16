//package com.example.provider.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * Druid多数据源配置
// *
// * @author wakening
// */
//@Configuration
//@MapperScan(basePackages = "com.example.provider.dao", sqlSessionTemplateRef = "sqlSessionTemplateDB1")
//public class DruidConfig {
//
//    @Primary
//    @Bean(name = "dataSourceDB1")
//    @ConfigurationProperties(prefix = "druid.db1")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//    @Primary
//    @Bean("sqlSessionFactoryDB1")
//    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("dataSourceDB1") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/*.xml"));
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        if (sqlSessionFactory != null) {
//            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
//        }
//        return sqlSessionFactory;
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerDB1")
//    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("dataSourceDB1") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "sqlSessionTemplateDB1")
//    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("sqlSessionFactoryDB1") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}
