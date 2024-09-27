package com.noukri.noukriapk.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class DatasourceAspect {


    @Pointcut("@annotation(com.noukri.noukriapk.config.SwitchDataSource)")
    public void annotationPointCut() {
    }


    @Before(value = "annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SwithDataSource annotation = method.getAnnotation(SwithDataSource.class);
        if (annotation != null) {
            AbstractRoutingDataSourceImpl.setDatabaseName(annotation.value());
            log.info("database-------------------------------------------------------");
        }



    }
  @After(value = "annotationPointCut()")
   public void after(){
        if (AbstractRoutingDataSourceImpl.getDatabaseName()!= null){
            AbstractRoutingDataSourceImpl.removeDatabaseName();
        }
   }
}
