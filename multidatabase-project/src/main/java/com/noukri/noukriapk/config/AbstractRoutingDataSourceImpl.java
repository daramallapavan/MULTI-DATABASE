package com.noukri.noukriapk.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class AbstractRoutingDataSourceImpl extends AbstractRoutingDataSource {

    private static  final ThreadLocal<String> DATABASE_NAME= new ThreadLocal<>();

    public static void setDatabaseName(String name){
         DATABASE_NAME.set(name);
    }

    public static String getDatabaseName(){
        return DATABASE_NAME.get();
    }

    public static void removeDatabaseName(){
        DATABASE_NAME.remove();
    }

    public  AbstractRoutingDataSourceImpl(
            DataSource defaultTargetDatasource,
            Map<Object,Object> targetDatasource
    ){
        super.setDefaultTargetDataSource(defaultTargetDatasource);
        super.setTargetDataSources(targetDatasource);
        super.afterPropertiesSet();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DATABASE_NAME.get();
    }
}
