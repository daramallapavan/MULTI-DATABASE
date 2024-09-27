package com.project.DatasourceProject.repository;

import com.project.DatasourceProject.config.db.DatasourceType;
import com.project.DatasourceProject.confog.route.WithDatabase;
import com.project.DatasourceProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
