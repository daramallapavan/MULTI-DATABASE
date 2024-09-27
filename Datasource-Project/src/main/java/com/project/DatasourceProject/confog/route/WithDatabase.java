package com.project.DatasourceProject.confog.route;

import com.project.DatasourceProject.config.db.DatasourceType;
import jakarta.transaction.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.METHOD})
@Transactional
public @interface WithDatabase {

    DatasourceType value() ;
}
