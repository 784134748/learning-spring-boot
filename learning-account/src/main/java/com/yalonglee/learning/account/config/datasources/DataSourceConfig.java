package com.yalonglee.learning.account.config.datasources;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author yalonglee
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "paymentAccountDataSource")
    @ConfigurationProperties(prefix = "payment-account.datasource")
    @Primary
    public DataSource paymentAccountDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "paymentAccountSqlSessionFactory")
    @Primary
    public SqlSessionFactory paymentAccountSqlSessionFactory(@Qualifier("paymentAccountDataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return getSqlSessionFactory(bean, resolver);
    }

    @Bean(name = "customerMadeSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate customerMadeSqlSessionTemplate(@Qualifier("paymentAccountSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }

    private SqlSessionFactory getSqlSessionFactory(SqlSessionFactoryBean bean, ResourcePatternResolver resolver) {
        try {
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            configuration.setUseColumnLabel(true);
            bean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
            bean.setConfiguration(configuration);
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public MyDataSource dataSource(DataSource paymentAccountDataSource) {
        Map<Object, Object> targetDataSources = Maps.newHashMap();
        targetDataSources.put(DataSourceNames.PAYMENT_ACCOUNT, paymentAccountDataSource);
        return new MyDataSource(paymentAccountDataSource, targetDataSources);
    }

}