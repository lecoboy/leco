package com.leco.mybatis.rw2.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.leco.mybatis.rw2.dao_read"}, sqlSessionFactoryRef = "slave1SqlSessionFactory")
public class MyBatisReadConfig {

    @Bean
    public SqlSessionFactory slave1SqlSessionFactory(@Qualifier("slave1DataSource") DataSource slave1DataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(slave1DataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:dao_read/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
