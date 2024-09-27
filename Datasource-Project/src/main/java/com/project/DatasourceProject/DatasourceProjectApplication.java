package com.project.DatasourceProject;

import com.project.DatasourceProject.controller.CustomerService;
import com.project.DatasourceProject.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class DatasourceProjectApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(DatasourceProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer1=new Customer(1,"pavan1");
		Customer customer2=new Customer(2,"pavan2");
		Customer customer3=new Customer(3,"pavan3");

		Map<Integer, Customer> map = new HashMap<>();
		map.put(1,customer1);
		map.put(2,customer2);
		map.put(1,customer3);
		//
		// map.putAll(map);

		System.out.println(map);

	}
}
