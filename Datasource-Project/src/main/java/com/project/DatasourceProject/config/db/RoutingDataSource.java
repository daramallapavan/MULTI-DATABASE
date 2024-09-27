package com.project.DatasourceProject.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Set;
import java.util.Stack;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static  final ThreadLocal<Stack<DatasourceType>> ctx=new ThreadLocal<>();

    public static void setCtx(DatasourceType datasourceType){
        getDatabase().push(datasourceType);
    }

    public static void retrieve(){
        Stack<DatasourceType> ctx=getDatabase();
        if(!ctx.isEmpty()){
            ctx.pop();
        }
    }

    private static Stack<DatasourceType> getDatabase(){
        if (ctx.get()==null){
            ctx.set(new Stack<>());
        }
        return ctx.get();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        Stack<DatasourceType> dbs=getDatabase();
        if (dbs.isEmpty()){
            return null;
        }

        DatasourceType sourceType=dbs.peek();
        log.info("chose {} datasource for the next transation",sourceType);
        return sourceType;
    }
}
