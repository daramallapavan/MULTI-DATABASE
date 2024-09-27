package com.project.DatasourceProject.controller;

import com.project.DatasourceProject.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/t")
    public Customer add(@RequestBody Customer customer){

        return customerService.create3(customer);
    }

    @PostMapping("/customera")
    public Customer post(@RequestBody Customer customer){

        return customerService.create(customer);
    }
    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){

        return customerService.getAllDS1();
    }

    @GetMapping("/list")
    public void list(){

         List<Customer> list2=customerService.getAllDS1();
        List<Customer> list3= customerService.getAllDSt();
        List<String> l2=list2.stream().map(Customer::getName).collect(Collectors.toList());
        List<String> l3=list3.stream().map(Customer::getName).collect(Collectors.toList());

        System.out.println(l2);
        System.out.println(l3);

    }
}
