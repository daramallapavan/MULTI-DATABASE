package com.noukri.noukriapk.service;

import com.noukri.noukriapk.config.SwithDataSource;
import com.noukri.noukriapk.entity.Address;
import com.noukri.noukriapk.entity.Student;
import com.noukri.noukriapk.repository.AddressRepo;
import com.noukri.noukriapk.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final StudentRepo studentRepo;

    private final AddressRepo addressRepo;

    @SwithDataSource(value = "student")
    public Student add(Student student){
        return studentRepo.save(student);
    }

    @SwithDataSource(value = "address")
    public Address create(Address address) {
        return addressRepo.save(address);
    }
}
