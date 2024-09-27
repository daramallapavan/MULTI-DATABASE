package com.noukri.noukriapk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.noukri.noukriapk",
        entityManagerFactoryRef = "entityManager"
)
public class DynamicDatabaseRouter {
    public static  final String PROPERTY_PREFIX="spring.datasource";
    @Autowired
    private Environment environment;

    @Bean
    @Primary
    @Scope("prototype")
    public AbstractRoutingDataSourceImpl dataSource(){
        Map<Object,Object> targetDatasource= getTargetDataSources();
        return new AbstractRoutingDataSourceImpl(
                (DataSource) targetDatasource.get("default")
                ,targetDatasource
        );
    }

    @Bean(name = "entityManager")
    @Scope("prototype")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dataSource())
                .packages("com.noukri.noukriapk.entity")
                .build();
    }

    private Map<Object, Object> getTargetDataSources() {
        List<String>  databaseNames=environment.getProperty("spring.database-names.list", List.class);
        Map<Object,Object> targetDatasource=new HashMap<>();

        for(String dbName:databaseNames){
            DriverManagerDataSource dataSource=new DriverManagerDataSource();
            dataSource.setUrl(environment.getProperty(PROPERTY_PREFIX+dbName+".url"));
            dataSource.setUsername(environment.getProperty(PROPERTY_PREFIX+dbName+".username"));
            dataSource.setPassword(environment.getProperty(PROPERTY_PREFIX+dbName+".password"));
            targetDatasource.put(dbName,dataSource);
        }

        targetDatasource.put("default",targetDatasource.get(databaseNames.get(0)));
        return targetDatasource;
    }
}
