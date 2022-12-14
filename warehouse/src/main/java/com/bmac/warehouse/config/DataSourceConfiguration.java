//package com.bmac.warehouse.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfiguration {
//
//    private final Environment env;
//
//    @Autowired
//    public DataSourceConfiguration(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public DataSource getDataSource() {
//        return DataSourceBuilder.create()
//                .url(env.getProperty("spring.datasource.url"))
//                .username(env.getProperty("spring.datasource.username"))
//                .password(env.getProperty("spring.datasource.password"))
//                .build();
//    }
//}
