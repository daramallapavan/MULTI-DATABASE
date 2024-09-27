package com.project.DatasourceProject.controller;

import com.project.DatasourceProject.config.db.DatasourceType;
import com.project.DatasourceProject.confog.route.WithDatabase;
import com.project.DatasourceProject.entity.Customer;
import com.project.DatasourceProject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {

        return customerRepository.save(customer);
    }


    @WithDatabase(DatasourceType.SECONDARY)
    public List<Customer> getAllDS1() {
        return customerRepository.findAll();
    }
    @WithDatabase(DatasourceType.TERITIARY)
    public List<Customer> getAllDSt() {
        return customerRepository.findAll();
    }

    @WithDatabase(DatasourceType.TERITIARY)
    public Customer create3(Customer customer) {
        return customerRepository.save(customer);
    }
}
