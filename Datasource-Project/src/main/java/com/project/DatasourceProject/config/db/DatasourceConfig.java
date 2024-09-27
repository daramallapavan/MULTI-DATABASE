package com.project.DatasourceProject.config.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class DatasourceConfig {

    private final Environment environment;

    @Bean
    public DataSource dataSource(){
        Map<Object,Object> datasources=this.buildDatasources();
        RoutingDataSource routingDataSource=new RoutingDataSource();

        routingDataSource.setTargetDataSources(datasources);
        routingDataSource.setDefaultTargetDataSource(datasources.get(DatasourceType.PRIMARY));
        return routingDataSource;
    }

    private Map<Object,Object> buildDatasources(){
        Map<Object,Object> result=new HashMap<>();

        for (DatasourceType sourceType:DatasourceType.values()){
          result.put(sourceType,this.buildDatasource(sourceType));
        }
        return result;
    }

    private DataSource buildDatasource(DatasourceType sourceType) {

        HikariConfig config=new HikariConfig();

        config.setJdbcUrl(environment.getProperty(String.format("spring.datasource.%s.url",sourceType.getName())));
        config.setUsername(environment.getProperty(String.format("spring.datasource.%s.username",sourceType.getName())));
        config.setPassword(environment.getProperty(String.format("spring.datasource.%s.password",sourceType.getName())));
        //  config.setDriverClassName(environment.getProperty(String.format("datasource.%s.driver-class",sourceType.getName())));

        config.setAutoCommit(false);

        return new HikariDataSource(config);
    }
}
