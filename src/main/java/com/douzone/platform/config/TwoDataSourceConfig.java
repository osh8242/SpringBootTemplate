//package com.douzone.platform.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
///*
// * 데이터베이스 DB2 JDBC 통신
// */
//@Configuration
//@PropertySource({ "classpath:application.properties" })
//public class TwoDataSourceConfig {
// 
//    @Bean(name = "datasource2")
//    @ConfigurationProperties(prefix = "spring.datasource.db2")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//  
//}
